import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {Product, ProductImage, ProductVariant} from "types/Product";
import {Alert, AlertColor, Button, CircularProgress, Snackbar} from "@mui/material";
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

type AlertContent = {
    severity: AlertColor,
    message: string,
}

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

function ProductView(props: { productId: number }) {

    const NO_SELECTION_TEXT = "Make a selection to see availability.";

    const [isLoading, setIsLoading] = useState(false);
    const [product, setProduct] = useState<Product>();
    const [currentVariant, setCurrentVariant] = useState<ProductVariant>();
    const [images, setImages] = useState<ProductImage[]>([]);
    const [dialogOpen, setDialogOpen] = useState(false);
    const [alert, setAlert] = useState<AlertContent>();
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    const selectionPropertyValues = useRef<SelectionPropertyValues>();

    useEffect(() => {
        setIsLoading(true);
        axios.get<Product>(`/api/products/${props.productId}`).then(response => {
            const product = response.data;
            selectionPropertyValues.current = makeSelectionPropertyValues(product);
            setProduct(product);
            product.variants.length == 1 && setCurrentVariant(product.variants[0]);
            setImages(product.variants.filter(variant => variant.image && true).map(variant => variant.image));
        }).catch(reason => {
            console.error(reason);
        }).finally(() => {
            setIsLoading(false);
        });
    }, []);

    //region Carousel
    const indexedImageIds = useRef([]);
    const [activeIndex, setActiveIndex] = useState(0);

    const carousel = (
        <Carousel activeIndex={activeIndex} variant={"dark"} interval={null}
                  onSelect={index => setActiveIndex(index)}
        >{images.map((image) => {
            indexedImageIds.current.push(image.id);
            return (
                <Carousel.Item key={image.id}>
                    <img className={"d-block w-100 img-fluid"} key={image.id} src={image.src} alt={image.alt}/>
                </Carousel.Item>
            );
        })}
        </Carousel>
    );

    useEffect(() => {
        const activeIndex = currentVariant?.image && indexedImageIds.current.findIndex((imageId) => imageId == currentVariant.image.id);
        activeIndex > -1 && setActiveIndex(activeIndex);
    }, [currentVariant]);
    //endregion

    //region Snackbar Controls
    function openSnackbar(alert: AlertContent) {
        setAlert(alert);
        setSnackbarOpen(true);
    }

    function closeSnackbar() {
        setSnackbarOpen(false);
    }
    //endregion

    function handleSelectionChange(options: number[]) {
        const variant = resolveVariantFromOptions(product.variants, options);
        setCurrentVariant(variant);
    }

    function handleQuantityConfirm(quantity: number) {
        if (!currentVariant || !product) return;
        let alert: AlertContent = {
            severity: "success",
            message: `Added ${quantity} × ${product.name} to cart. MOCK`
        };
        // axios.post("/api/users/me/cart_items", {variantId: currentVariant.id, quantity: quantity}).then(() => {
        //
        // }).catch(() => {
        //
        // });
        openSnackbar(alert);
    }

    return (
        <Container fluid={"md"} className={"mt-5 mb-5 min-vh-100"}>
            <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={closeSnackbar}>
                {alert && <Alert severity={alert.severity} onClose={closeSnackbar}>{alert.message}</Alert>}
            </Snackbar>
            <QuantityDialog open={dialogOpen} onClose={() => setDialogOpen(false)} onConfirm={handleQuantityConfirm}/>
            <Card className={"shadow-sm p-3"}>
                {isLoading && <CircularProgress/>}
                <Row>{(images && images.length > 0) &&
                    <Col sm>
                        {carousel}
                    </Col>}
                    <Col sm>{
                        product &&
                        <>
                            <Container className={"border-bottom border-2"}>
                                <h2>{product?.name}&nbsp;<Badge pill bg={"dark"}>{product?.brand}</Badge></h2>
                            </Container>{
                            selectionPropertyValues &&
                            <Container className={"mt-3"}>
                                <Accordion defaultActiveKey={"0"} flush>
                                    <Accordion.Item eventKey={"0"}>
                                        <Accordion.Header><h6>Options</h6></Accordion.Header>
                                        <Accordion.Body>
                                            <AvailabilityLabel availability={currentVariant?.availability}
                                                               otherText={NO_SELECTION_TEXT}/>{
                                            product.variants.length > 1 &&
                                            <ProductOptionSelection
                                                onChange={handleSelectionChange}
                                                groups={selectionPropertyValues.current.optionGroups}
                                                options={selectionPropertyValues.current.options}
                                                relation={selectionPropertyValues.current.relation}
                                            />}
                                            <Button disabled={!currentVariant}
                                                    startIcon={<AddShoppingCartSharpIcon/>}
                                                    onClick={() => setDialogOpen(true)}
                                            >
                                                Add to cart
                                            </Button>
                                        </Accordion.Body>
                                    </Accordion.Item>
                                    <Accordion.Item eventKey={"1"}>
                                        <Accordion.Header><h6>Description</h6></Accordion.Header>
                                        <Accordion.Body>{product?.description}</Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </Container>}
                        </> || (!isLoading && <span>This product could not be found.</span>)}
                    </Col>
                </Row>
            </Card>
        </Container>
    )
}

export default ProductView;