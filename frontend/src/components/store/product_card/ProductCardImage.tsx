import * as React from "react";
import {Card} from "react-bootstrap";

const placeholder = {
    src: "https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg",
    alt: "Placeholder image for missing product image."
};

const ProductCardImage = ({image, href}: {href: string, image: { alt: string, src: string } }) => {

    const imageData = image && {src: image.src, alt: image.alt} || placeholder;

    return <a href={href}><Card.Img style={{height: 300, objectFit: "cover"}} src={imageData.src} alt={imageData.alt}/></a>;
}

export default ProductCardImage;