import * as React from "react";
import {useState} from "react";
import {Chip} from "@mui/material";
import {Product} from "types/Product";
import {Card} from "react-bootstrap";
import ProductVariantAvailability from "types/ProductVariantAvailability";
import ProductCardImage from "components/store/product_card/ProductCardImage";
import VariantSelection from "components/store/product_card/VariantSelection";
import AvailabilityLabel from "components/shared/AvailabilityLabel";

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

    const href = `/store/product/${product.id}`;

    return (
        <Card style={{width: "300px"}}>
            <AvailabilityLabel availability={shownVariant.availability} otherText={"Availability Unknown"}/>
            <ProductCardImage image={shownVariant.image} href={href}/>
            <Card.Body>
                <Card.Title className={"text-center"}>
                    <a href={href}>{product.name}</a>
                </Card.Title>
                <VariantSelection initialVariant={shownVariant} variants={product.variants} onChange={value => setShownVariant(value)}/>
                <Card.Text style={{maxHeight: "4.5em"}} className={"line-clamp text-center"}>{product.description}</Card.Text>
            </Card.Body>
        </Card>
    )
}

export default ProductCard;