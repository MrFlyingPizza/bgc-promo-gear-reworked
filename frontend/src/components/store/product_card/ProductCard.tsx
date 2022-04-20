import * as React from "react";
import {useState} from "react";
import ProductCardImage from "./ProductCardImage";
import ProductVariantAvailability from "types/ProductVariantAvailability";
import {Chip} from "@mui/material";
import {Product} from "types/Product";

function ProductCard(props: { item: Product }) {

    const [product] = useState(props.item);
    const [shownVariant] = useState(product.variants[0]); // published products always have at least 1 variant

    const StockLabel = (props: { availability: ProductVariantAvailability }) => {

        const [availability] = useState(props.availability);

        return (
            availability == ProductVariantAvailability.AVAILABLE
            && <Chip label={"Available"} variant={"outlined"} size={"small"} color={"info"}/>
            || availability == ProductVariantAvailability.WAIT_LIST
            && <Chip label={"Wait-List"} variant={"outlined"} size={"small"} color={"warning"}/>
            || availability == ProductVariantAvailability.UNAVAILABLE
            && <Chip label={"Unavailable"} variant={"outlined"} size={"small"} color={"error"}/>
        )
    }

    return (
        <a href={"/store/product/?id=" + product.id} style={{textDecoration: 'none', color: 'black'}}>
            <div className="product-container">
                <div className="image_container">
                    <ProductCardImage image={product.images.length > 0 && product.images.sort((a, b) => {
                        return a.position < b.position ? 0 : 1
                    })[0]}
                    />
                    <StockLabel availability={shownVariant.availability}/>
                    <div className="description-container">
                        <span className={"description"}>{product.description}</span>
                    </div>
                </div>
                <div className="product_name_container">
                    <span className="product_name">
                        {product.name}
                    </span>
                </div>
            </div>
        </a>
    )
}

export default ProductCard;