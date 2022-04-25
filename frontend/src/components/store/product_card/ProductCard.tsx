import * as React from "react";
import {useState} from "react";
import {Chip, List, ListItem, ListItemText} from "@mui/material";
import {Product} from "types/Product";
import {Card} from "react-bootstrap";
import ProductVariantAvailability from "types/ProductVariantAvailability";
import ProductCardImage from "components/store/product_card/ProductCardImage";
import ProductCardOptions from "components/store/product_card/ProductCardOptions";

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

    // <a href={"/store/product/?id=" + product.id} style={{textDecoration: 'none', color: 'black'}}>
    //     <ProductCardImage image={shownVariant.image}/>
    // </a>

    const cartOptions = product.variants.map(variant => {
        const tooltip = (
            variant.options.length > 0 &&
            <List>
                {variant.options.map(option => (
                    <ListItem key={option.optionId}>
                        <Chip color={"primary"} label={option.value}/>
                    </ListItem>
                ))}
            </List>
        );
        return {value: variant.id.toString(), src: variant.image?.src, tooltip: tooltip};
    });

    return (
        <Card style={{width: "300px"}}>
            <AvailabilityLabel availability={shownVariant.availability}/>
            <ProductCardImage image={shownVariant.image}/>
            <Card.Body>
                <Card.Title>{product.name}</Card.Title>
                <ProductCardOptions initialValue={shownVariant.id.toString()} values={cartOptions}
                                    onChange={value => setShownVariant(variantMap.get(value))}/>
                <Card.Text>{product.description}</Card.Text>
            </Card.Body>
        </Card>
    )
}

export default ProductCard;