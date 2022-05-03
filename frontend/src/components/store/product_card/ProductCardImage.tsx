import * as React from "react";
import {Card} from "react-bootstrap";
import placeholderSrc from "components/shared/PlaceholderImage";

const ProductCardImage = ({image, href}: {href: string, image: { alt: string, src: string } }) => {

    const imageData = image && {src: image.src, alt: image.alt} || {src: placeholderSrc(), alt: "placeholder image"};

    return (
        <a href={href}>
            <Card.Img style={{height: 300, objectFit: "cover"}} src={imageData.src} alt={imageData.alt}/>
        </a>
    );
}

export default ProductCardImage;