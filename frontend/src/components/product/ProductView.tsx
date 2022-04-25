import {useEffect, useState} from "react";
import axios from "axios";
import {Product, ProductImage} from "types/Product";
import {LinearProgress} from "@mui/material";
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

        const ProductImageCarouselItem = (props: { image: ProductImage }) => {
            return (
                <Carousel.Item>
                    <img key={props.image.id} src={props.image.src} alt={props.image.alt}/>
                </Carousel.Item>
            );
        }

        return (
            <Carousel style={{height: "50vh"}}>
                {
                    images.map(image => <ProductImageCarouselItem key={image.id} image={image}/>)
                }
            </Carousel>
        )
    }

    return (
        product &&
        <StoreContainer>
            {isLoading && <LinearProgress/>}
            <Row>
                {
                    (images && images.length > 0) &&
                    <Col>
                        <ProductImageCarousel images={images}/>
                    </Col>
                }
                <Col>
                    <h3>{product.name}</h3>
                    <h4>{product.brand}</h4>
                    <p>{product.description}</p>
                </Col>
            </Row>
        </StoreContainer>
    )
}

export default ProductView;