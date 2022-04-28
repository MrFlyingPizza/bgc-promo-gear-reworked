import * as React from "react";
import {useState} from "react";
import {Chip, List, ListItem, ListItemText} from "@mui/material";
import {Product} from "types/Product";
import {Card} from "react-bootstrap";
import ProductVariantAvailability from "types/ProductVariantAvailability";
import ProductCardImage from "components/store/product_card/ProductCardImage";
import VariantSelection from "components/store/product_card/VariantSelection";

function ProductCard(props: { product: Product }) {

    const [product] = useState(props.product);

    const [shownVariant, setShownVariant] = useState(() => {
        const rank = (availability: ProductVariantAvailability): number =>
            availability == ProductVariantAvailability.AVAILABLE && 2
            || availability == ProductVariantAvailability.WAIT_LIST && 1
            || availability == ProductVariantAvailability.UNAVAILABLE && 0;
        let initialVariant = product.variants[0];
        product.variants.forEach(variant => {
            if (rank(variant.availability) > rank(initialVariant.availability)) {
                initialVariant = variant;
            }
        });
        return initialVariant;
    }); // published products always have at least 1 variant

    const variantMap = new Map(product.variants.map(variant => {
        return [variant.id.toString(), variant];
    }));

    const AvailabilityLabel = (props: { availability: ProductVariantAvailability }) => {

        const [availability] = useState(props.availability);

        const AvailabilityChip = (props: { label: string, color: "info" | "warning" | "error" }) => {
            return (
                <Chip className={"availability-label"} label={props.label} variant={"filled"} size={"small"}
                      color={props.color}/>
            )
        }

        return (
            availability == ProductVariantAvailability.AVAILABLE
            && <AvailabilityChip label={"Available"} color={"info"}/>
            || availability == ProductVariantAvailability.WAIT_LIST
            && <AvailabilityChip label={"Wait-List"} color={"warning"}/>
            || availability == ProductVariantAvailability.UNAVAILABLE
            && <AvailabilityChip label={"Unavailable"} color={"error"}/>
        )
    }

    const href = `/store/product/${product.id}`;

    return (
        <Card style={{width: "300px"}}>
            <AvailabilityLabel availability={shownVariant.availability}/>
            <ProductCardImage image={shownVariant.image} href={href}/>
            <Card.Body>
                <Card.Title className={"text-center"}>
                    <a href={href}>{product.name}</a>
                </Card.Title>
                <VariantSelection initialVariant={shownVariant} variants={product.variants} onChange={value => setShownVariant(value)}/>
                <Card.Text style={{maxHeight: "4em"}} className={"line-clamp text-center"}>{product.description}</Card.Text>
            </Card.Body>
        </Card>
    )
}

export default ProductCard;