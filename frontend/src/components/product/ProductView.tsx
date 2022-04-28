import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {Product, ProductImage, ProductVariant} from "types/Product";
import {CircularProgress} from "@mui/material";
import React from "react";
import {Badge, Carousel, Col, Container, Row} from "react-bootstrap";
import StoreContainer from "components/shared/StoreContainer";
import ProductOptionSelection, {
    OptionValueGroup,
    SimpleOptionValue
} from "components/product/option_selection/OptionSelection";
import AvailabilityLabel from "components/shared/AvailabilityLabel";
import {
    collectAndSimplifyUniqueOptions,
    groupOptions, relateOptions,
    resolveVariantFromOptions
} from "components/product/option_selection/helpers";

function ProductView(props: { productId: number }) {

    const [isLoading, setIsLoading] = useState(false);
    const [product, setProduct] = useState<Product>();
    const [currentVariant, setCurrentVariant] = useState<ProductVariant>(null);
    const [images, setImages] = useState<ProductImage[]>([]);

    function makeSelectionPropertyValues(product: Product) {
        const allOptionValues = product.variants.map(variant => variant.options).reduce((prev, curr) => prev.concat(curr), []);
        return {
            optionGroups: groupOptions(product.options, allOptionValues),
            options: collectAndSimplifyUniqueOptions(allOptionValues),
            relation: relateOptions(product.variants, allOptionValues)
        }
    }

    type SelectionPropertyValues = {
        optionGroups: OptionValueGroup[],
        options: SimpleOptionValue[],
        relation: Map<number, number[]>
    }

    const selectionPropertyValues = useRef<SelectionPropertyValues>();

    useEffect(() => {
        setIsLoading(true);
        axios.get<Product>(`/api/products/${props.productId}`).then(response => {
            const product = response.data;
            selectionPropertyValues.current = makeSelectionPropertyValues(product);
            setProduct(product);
            setImages(product.variants.filter(variant => variant.image && true).map(variant => variant.image));
        }).catch(reason => {
            console.error(reason);
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

    const NO_SELECTION_TEXT = "Make a selection to see availability.";

    return (
        <StoreContainer>
            {isLoading && <CircularProgress/>}
            <Row>{(images && images.length > 0) &&
            <Col md>
                <ProductImageCarousel images={images}/>
            </Col>}
                <Col md>{product &&
                <>
                    <Container>
                        <h3>{product?.name}&nbsp;<Badge pill bg={"dark"}>{product?.brand}</Badge></h3>
                    </Container>{selectionPropertyValues &&
                <ProductOptionSelection
                    onChange={(options) => setCurrentVariant(resolveVariantFromOptions(product.variants, options))}
                    groups={selectionPropertyValues.current.optionGroups}
                    options={selectionPropertyValues.current.options}
                    relation={selectionPropertyValues.current.relation}
                />}
                    <AvailabilityLabel availability={currentVariant?.availability} otherText={NO_SELECTION_TEXT}/>
                    <p>{product?.description}</p>
                </> || (!isLoading && <span>This product could not be found.</span>)}
                </Col>
            </Row>
        </StoreContainer>
    )
}

export default ProductView;