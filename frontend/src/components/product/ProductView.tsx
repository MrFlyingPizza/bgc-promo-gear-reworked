import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {Button, CircularProgress} from "@mui/material";
import React from "react";
import {Accordion, Badge, Card, Carousel, Col, Container, Row} from "react-bootstrap";
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
import AddShoppingCartSharpIcon from '@mui/icons-material/AddShoppingCartSharp';
import QuantityDialog from "components/product/QuantityDialog";
import {useQuery} from "@tanstack/react-query";
import Product from "types/Product";
import ProductImage from "types/ProductImage";
import ProductVariant from "types/ProductVariant";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import AlertSnackbar, {AlertContent} from "components/shared/AlertSnackbar";

type SelectionPropertyValues = {
    optionGroups: OptionValueGroup[],
    options: SimpleOptionValue[],
    relation: Map<number, number[]>
}

function makeSelectionPropertyValues(product: Product) {
    const allOptionValues = product.variants.map(variant => variant.options).reduce((prev, curr) => prev.concat(curr), []);
    return {
        optionGroups: groupOptions(product.options, allOptionValues),
        options: collectAndSimplifyUniqueOptions(allOptionValues),
        relation: relateOptions(product.variants, allOptionValues)
    }
}

function ProductView({productId}: { productId: number }) {

    const NO_SELECTION_TEXT = "Make a selection to see availability.";

    const selectionPropertyValues = useRef<SelectionPropertyValues>();

    function fetchProduct() {
        return axios.get<Product>(`/api/products/${productId}`).then(({data: product}) => {
            selectionPropertyValues.current = makeSelectionPropertyValues(product);
            product.variants.length == 1 && setCurrentVariant(product.variants[0]);
            const newImageMap = new Map(images);
            product.variants.forEach(({image}) => {
                if (image) {
                    newImageMap.set(image.id, image);
                }
            })
            setImages(newImageMap);
            return product;
        });
    }

    const {isLoading, isError, data: product} = useQuery<Product>(['product'], fetchProduct, {retry: false});

    //region Snackbar Control
    const [alert, setAlert] = useState<AlertContent>();
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    function openSnackbar(alert: AlertContent) {
        setAlert(alert);
        setSnackbarOpen(true);
    }

    function closeSnackbar() {
        setSnackbarOpen(false);
    }

    //endregion


    //region Quantity Dialog Control
    const [dialogOpen, setDialogOpen] = useState(false);

    function handleQuantityConfirm(quantity: number) {
        if (!currentVariant || !product) return;
        openSnackbar({
            severity: "info",
            message: <span>Adding <b className={"font-monospace"}>{quantity} × {product.name}</b> to cart…</span>,
            autoHideDuration: null
        })
        axios.post("/api/users/me/cart_items", {variantId: currentVariant.id, quantity: quantity}).then(() => {
            openSnackbar({
                severity: "success",
                message: <span>Added <b className={"font-monospace"}>{quantity} × {product.name}</b> to cart.</span>,
                autoHideDuration: 3000
            });
        }).catch(() => {
            openSnackbar({severity: "error", message: "Could not add product to cart!", autoHideDuration: 3000})
        });
    }

    //endregion

    //region Variant Selection Control
    const [currentVariant, setCurrentVariant] = useState<ProductVariant>();

    function handleSelectionChange(options: number[]) {
        const variant = resolveVariantFromOptions(product.variants, options);
        setCurrentVariant(variant);
    }

    //endregion

    //region Carousel Control
    const [images, setImages] = useState<Map<number, ProductImage>>(new Map());
    const indexedImageIds = useRef([]);
    const [activeIndex, setActiveIndex] = useState(0);

    const carousel = (
        <Carousel activeIndex={activeIndex} variant={"dark"} interval={null}
                  onSelect={index => setActiveIndex(index)}>{Array.from(images).map(([id, image]) => {
            indexedImageIds.current.push(id);
            return (
                <Carousel.Item key={image.id}>
                    <img className={"d-block w-100 img-fluid"} key={image.id} src={image.src} alt={image.alt}/>
                </Carousel.Item>
            );
        })}
        </Carousel>
    );

    useEffect(() => {
        const activeIndex = currentVariant?.image &&
            indexedImageIds.current.findIndex((imageId) => imageId == currentVariant.image.id);
        activeIndex > -1 && setActiveIndex(activeIndex);
    }, [currentVariant]);
    //endregion

    return (
        <>
            <BGCPromoGearHeader/>
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <AlertSnackbar open={snackbarOpen} alert={alert} onClose={closeSnackbar}/>
                <QuantityDialog open={dialogOpen} onClose={() => setDialogOpen(false)}
                                onConfirm={handleQuantityConfirm}/>
                <Card className={"shadow-sm"}>
                    <Card.Body>
                        {isLoading && <CircularProgress/>}
                        {isError && <span>This product could not be found.</span>}
                        <Row>{
                            (images && images.size > 0) &&
                            <Col md>
                                {carousel}
                            </Col>}
                            <Col md>{
                                product &&
                                <>
                                    <Container className={"border-bottom border-2"}>
                                        <h2>{product?.name}&nbsp;<Badge pill bg={"dark"}>{product?.brand}</Badge></h2>
                                    </Container>
                                    <Container className={"mt-3"}>
                                        <Accordion defaultActiveKey={"0"} flush>{
                                            selectionPropertyValues &&
                                            <Accordion.Item eventKey={"0"}>
                                                <Accordion.Header><h6>Options</h6></Accordion.Header>
                                                <Accordion.Body>{
                                                    product.variants.length > 1 &&
                                                    <ProductOptionSelection
                                                        onChange={handleSelectionChange}
                                                        groups={selectionPropertyValues.current.optionGroups}
                                                        options={selectionPropertyValues.current.options}
                                                        relation={selectionPropertyValues.current.relation}
                                                    />}
                                                    <Button disabled={!currentVariant}
                                                            startIcon={<AddShoppingCartSharpIcon/>}
                                                            onClick={() => setDialogOpen(true)}>
                                                        Add to cart
                                                    </Button>
                                                    <AvailabilityLabel availability={currentVariant?.availability}
                                                                       otherText={NO_SELECTION_TEXT}/>
                                                </Accordion.Body>
                                            </Accordion.Item>}
                                            <Accordion.Item eventKey={"1"}>
                                                <Accordion.Header><h6>Description</h6></Accordion.Header>
                                                <Accordion.Body>{product?.description}</Accordion.Body>
                                            </Accordion.Item>
                                        </Accordion>
                                    </Container>
                                </>}
                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
            </Container>
            <BGCPromoGearFooter/>
        </>
    )
}

export default ProductView;