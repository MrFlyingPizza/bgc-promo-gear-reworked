import * as React from "react";
import axios from "axios";
import {Accordion, AccordionDetails, AccordionSummary, Alert, AlertColor, CircularProgress} from "@mui/material";

import Product from "types/Product";
import Category from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import {useQuery} from "react-query";
import {Card, Col, Container, Row} from "react-bootstrap";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import CategorySelection from "components/store/category_list/CategorySelection";
import {useEffect, useState} from "react";

type StoreAlertProps = {
    id: number,
    severity: AlertColor,
    content: React.ReactNode,
    onClose: () => void
};

function Store() {

    const [selectedCategoryIds, setSelectedCategoryIds] = useState([]);

    //region Alert Control
    const [alerts, setAlerts] = useState<StoreAlertProps[]>([]);

    function indexOfAlert(alertId: number) {
        return alerts.findIndex(({id}) => id == alertId);
    }

    function removeAlert(alertId: number) {
        setAlerts(alerts.filter(({id}) => id != alertId));
    }

    function addAlert(alert: StoreAlertProps) {
        setAlerts([...alerts.filter(({id}) => id != alert.id), alert]);
    }

    //endregion

    const {
        isLoading: isLoadingCategories,
        isError: isErrorCategories,
        data: categories
    } = useQuery("categories", () => axios.get("/api/categories").then<Category[]>(response => response.data.categories));

    //region Product Fetching
    // const [isLoadingProducts, setIsLoadingProducts] = useState(true);
    // const [products, setProducts] = useState<Product[]>([]);
    //
    // async function fetchProductsByCategoryId(categoryId: number) {
    //     axios.get("/api/products/", {params: {"category.id": categoryId}}).then<Product>(({data}) => {
    //         setProducts([...products, data]);
    //         return data;
    //     }).catch(() => addAlert({
    //         id: categoryId,
    //         severity: "error",
    //         content: "",
    //         onClose: () => removeAlert(categoryId)
    //     }));
    // }
    //
    // useEffect(() => {
    //     selectedCategoryIds.forEach(selectedId => {
    //         const params = {"category.id": selectedId};
    //         axios.get("/api/products/", {params: params})
    //     })
    // }, [selectedCategoryIds]);
    //endregion

    const {
        isLoading: isLoadingProducts,
        isError: isErrorProducts,
        data: products
    } = useQuery("products", () => axios.get("/api/products").then<Product[]>(response => response.data.products));

    const cardSize = {xs: 12, sm: 6, md: 12, lg: 6, xl: 4, xxl: 3};

    return (
        <>
            <BGCPromoGearHeader/>
            <Container fluid className={"mt-5 mb-5 min-vh-100 justify-content-center"}>
                <Row>
                    <Col>
                        <Row>
                            <h3>Promotional Gear</h3>
                        </Row>
                        <Row>
                            <Col sm={12} md={5} lg={4} xxl={3}>
                                <Card className={"shadow-sm"}>
                                    <Card.Body>
                                        <Card.Title>Filters</Card.Title>
                                        <Accordion>
                                            <AccordionSummary>Categories</AccordionSummary>
                                            <AccordionDetails>{
                                                isLoadingCategories && <div><CircularProgress/></div>
                                                || isErrorCategories && "Failed to load categories."
                                                || <CategorySelection categories={categories} onChange={console.log}/>}
                                            </AccordionDetails>
                                        </Accordion>
                                    </Card.Body>
                                </Card>
                            </Col>
                            <Col>
                                <Card className={"shadow-sm"}>
                                    <Card.Body>
                                        <Row>{alerts.map(({id, severity, onClose, content}) =>
                                            <Alert key={id} severity={severity} onClose={() => onClose()}>
                                                {content}
                                            </Alert>)}
                                        </Row>
                                        <Row>{isLoadingProducts &&
                                            Array.from({length: 4}, (v, i) => (
                                                <Col key={i} xs={12} {...cardSize}>
                                                    <Row>
                                                        <LoadingCard key={i}/>
                                                    </Row>
                                                </Col>
                                            ))
                                            || products?.length > 0 && products.map(item => (
                                                <Col key={item.id} {...cardSize}>
                                                    <ProductCard key={item.id} product={item}/>
                                                </Col>))
                                            || <span>No items found.</span>}
                                        </Row>
                                    </Card.Body>
                                </Card>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Store;
