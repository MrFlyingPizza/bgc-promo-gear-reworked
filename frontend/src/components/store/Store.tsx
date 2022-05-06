import * as React from "react";
import axios from "axios";
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Alert,
    AlertColor,
    CircularProgress,
    Skeleton
} from "@mui/material";

import Product from "types/Product";
import Category from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import {useQueries, useQuery, UseQueryResult} from "react-query";
import {Badge, Card, Col, Container, Row} from "react-bootstrap";
import {Accordion as BSAccordion} from "react-bootstrap";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import CategorySelection from "components/store/category_list/CategorySelection";
import {useState} from "react";

type StoreAlertProps = {
    id: number,
    severity: AlertColor,
    content: React.ReactNode,
    onClose: () => void
};

type ProductGroup = {
    categoryId: number
    products: Product[]
}

function Store() {

    const [selectedCategoryIds, setSelectedCategoryIds] = useState<Set<number>>(new Set());

    function handleCategoryChange(previousCategoryIds: number[], updatedCategoryIds: number[]) {
        previousCategoryIds.forEach(id => selectedCategoryIds.delete(id));
        updatedCategoryIds.forEach(id => selectedCategoryIds.add(id));
        setSelectedCategoryIds(new Set(selectedCategoryIds));
    }

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
    } = useQuery("categories", () => axios.get("/api/categories")
        .then<Category[]>(response => response.data.categories.filter(({parent}: Category) => !parent))
    );

    function getCategoryById(categoryId: number) {
        if (categoryId == null) return null;
        return categories.find(({id}) => id == categoryId);
    }

    //region Product Fetching
    const productQueryResults: UseQueryResult<ProductGroup>[] = useQueries(Array.from(selectedCategoryIds).map(categoryId => ({
        queryKey: ["products", categoryId],
        queryFn: () => {
            return axios.get("/api/products", {params: {"category.id": categoryId}})
                .then<ProductGroup>(response => ({categoryId: categoryId, products: response.data.products}));
        }
    })));

    //endregion

    function resultComparator({data: a}: UseQueryResult<ProductGroup>, {data: b}: UseQueryResult<ProductGroup>) {
        if (!a) return 1;
        if (!b) return -1;
        if (a.products.length == b.products.length) return 0;
        if (b.products.length > a.products.length) return 1;
        else return -1;
    }

    return (
        <>
            <BGCPromoGearHeader/>
            <Container fluid={"xl"} className={"mt-5 mb-5 min-vh-100 justify-content-center"}>
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
                                                || <CategorySelection categories={categories}
                                                                      onChange={handleCategoryChange}/>}
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
                                        <Row>
                                            <BSAccordion flush alwaysOpen
                                                         defaultActiveKey={productQueryResults.filter(({data}) => data)
                                                             .map(({data}) => data.categoryId.toString())}
                                            >{productQueryResults.sort(resultComparator).map((
                                                {data: group, isLoading, isError}, index
                                            ) => (
                                                group &&
                                                <BSAccordion.Item key={group.categoryId} eventKey={index.toString()}>
                                                    <BSAccordion.Header>{
                                                        group.categoryId == 0 && "All Products"
                                                        || (
                                                            <>
                                                                <Badge>{group.products?.length}</Badge>
                                                                {getCategoryById(group.categoryId)?.name}
                                                            </>
                                                        ) || <Skeleton variant={"text"} width={50}/>}
                                                    </BSAccordion.Header>
                                                    <BSAccordion.Body>
                                                        <Container>
                                                            <Row>{
                                                                isLoading && Array.from({length: 4}, (v, i) =>
                                                                    <Col key={i} xs>
                                                                        <LoadingCard key={i}/>
                                                                    </Col>
                                                                ) || isError &&
                                                                <Alert severity={"error"}>
                                                                    Failed to load products.
                                                                </Alert>
                                                                || group.products?.length > 0 && group.products.map(item => (
                                                                    <Col key={item.id} xs>
                                                                        <ProductCard key={item.id} product={item}/>
                                                                    </Col>
                                                                )) ||
                                                                <span>No items found.</span>}
                                                            </Row>
                                                        </Container>
                                                    </BSAccordion.Body>
                                                </BSAccordion.Item>
                                            ))}
                                            </BSAccordion>
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
