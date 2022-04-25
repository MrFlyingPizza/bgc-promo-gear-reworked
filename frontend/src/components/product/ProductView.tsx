import {useEffect, useState} from "react";
import axios from "axios";
import {Product, ProductImage} from "types/Product";
import {CircularProgress} from "@mui/material";
import React from "react";
import {Carousel, Col, Container, Row} from "react-bootstrap";
import StoreContainer from "components/shared/StoreContainer";

function ProductView(props: { productId: number }) {

    const [isLoading, setIsLoading] = useState(false);
    const [product, setProduct] = useState<Product>();
    const [images, setImages] = useState<ProductImage[]>([]);

    useEffect(() => {
        setIsLoading(true);
        axios.get<Product>(`${location.origin}/api/products/${props.productId}`).then(response => {
            const product = response.data;
            setProduct(product);
            setImages(product.variants.filter(variant => variant.image && true).map(variant => variant.image));
        }).catch(reason => {
            console.log(reason);
        }).finally(() => {
            setIsLoading(false);
        });
    }, []);

    const ProductImageCarousel = ({images}:{images: ProductImage[]}) => {

        const carouselItems = images.map(image => {
            return (
                <Carousel.Item>
                    <img className={"d-block w-100"} key={image.id} src={image.src} alt={image.alt}/>
                </Carousel.Item>
            )
        });

        return (
            <Carousel variant={"dark"} interval={null}>{carouselItems}</Carousel>
        )
    }

    return (
        <StoreContainer>
            {isLoading && <CircularProgress/>}
            <Row>
                {
                    (images && images.length > 0) &&
                    <Col>
                        <ProductImageCarousel images={images}/>
                    </Col>
                }
                <Col>
                    <h3>{product?.name}</h3>
                    <h4>{product?.brand}</h4>
                    <p>{product?.description}</p>
                </Col>
            </Row>
        </StoreContainer>
    )
}

export default ProductView;