import * as React from "react";
import {useState} from "react";

const placeholder = (
    <img className="product-image"
         src={"https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg"}
         alt={"Placeholder image for missing product image."}
    />
);

const ProductCardImage = (props: { image: { alt: string, src: string } }) => {

    const [image] = useState(props.image);

    return (
        image && <img className="product-image" src={image.src} alt={image.alt}/> || placeholder
    )
}

export default ProductCardImage;