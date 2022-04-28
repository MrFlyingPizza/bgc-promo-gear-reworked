import {useEffect, useState} from "react";
import axios from "axios";
import {Product, ProductImage, ProductVariant} from "types/Product";
import {CircularProgress} from "@mui/material";
import React from "react";
import {Badge, Carousel, Col, Container, Row} from "react-bootstrap";
import StoreContainer from "components/shared/StoreContainer";
import ProductOptionSelection from "components/product/option_selection/OptionSelection";

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

    const ProductImageCarousel = ({images}: { images: ProductImage[] }) => {

        const carouselItems = images.map(image => {
            return (
                <Carousel.Item key={image.id}>
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
            <Row>{(images && images.length > 0) &&
                <Col md>
                    <ProductImageCarousel images={images}/>
                </Col>}
                <Col md>
                    <Container>
                        <h3>{product?.name}&nbsp;<Badge pill bg={"dark"}>{product?.brand}</Badge></h3>
                    </Container>{product &&
                    <ProductOptionSelection variants={product.variants} onResolve={(variant: ProductVariant) => console.log(variant)}/>}
                    <p>{product?.description}</p>
                </Col>
            </Row>
        </StoreContainer>
    )
}

export default ProductView;