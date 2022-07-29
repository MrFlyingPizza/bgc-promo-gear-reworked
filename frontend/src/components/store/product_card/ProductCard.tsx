import * as React from "react";
import {useState} from "react";
import Product from "types/Product";
import ProductVariantAvailability from "types/ProductVariantAvailability";
import VariantSelection from "components/store/product_card/VariantSelection";
import AvailabilityLabel from "components/shared/AvailabilityLabel";
import {Card, CardActionArea, CardContent, CardMedia, Fade, Skeleton, Stack} from "@mui/material";
import placeholderSrc from "components/shared/PlaceholderImage";

export const LoadingCard = () => {
    return (
        <Fade in appear>
            <Stack spacing={2}>
                <Skeleton variant={"rectangular"} height={300}/>
                <Skeleton variant={"text"}/>
                <Stack spacing={2} direction={"row"}>
                    <Skeleton variant={"circular"} width={24} height={24}/>
                    <Skeleton variant={"circular"} width={24} height={24}/>
                    <Skeleton variant={"circular"} width={24} height={24}/>
                </Stack>
                <Skeleton variant={"text"}/>
                <Skeleton variant={"text"}/>
                <Skeleton variant={"text"} width={"60%"}/>
            </Stack>
        </Fade>
    )
}

function ProductCard({product}: { product: Product }) {

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
    new Map(product.variants.map(variant => {
        return [variant.id.toString(), variant];
    }));
    const href = `/store/product/${product.id}`;

    return (
        <Card sx={{height: '100%'}}>
            <CardActionArea onClick={() => location.href = href}>
                <CardMedia component={"img"} height={300} image={shownVariant.image?.src || placeholderSrc()}/>
            </CardActionArea>
            <CardContent >
                <h5>{product.name}</h5>
                <AvailabilityLabel availability={shownVariant.availability} otherText={"Availability Unknown"}/>
                <VariantSelection initialVariant={shownVariant} variants={product.variants}
                                  onChange={value => setShownVariant(value)}/>
                <p style={{maxHeight: "4.5em"}} className={"line-clamp text-center"}>{product.description}</p>
            </CardContent>
        </Card>
    )
}

export default ProductCard;