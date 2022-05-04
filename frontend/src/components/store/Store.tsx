import * as React from "react";
import {useState} from "react";
import axios from "axios";
import {CircularProgress, Grid} from "@mui/material";

import Product from "types/Product";
import Category from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import {useQuery} from "react-query";
import {Card, Col, Container, Row} from "react-bootstrap";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import CategorySelection from "components/store/category_list/CategorySelection";

function Store() {

    const {
        isLoading: isLoadingProducts,
        isError: isErrorProducts,
        data: products
    } = useQuery("products", () => axios.get("/api/products").then<Product[]>(response => response.data.products));

    const {
        isLoading: isLoadingCategories,
        isError: isErrorCategories,
        data: categories
    } = useQuery("categories", () => axios.get("/api/categories").then<Category[]>(response => response.data.categories));

    return (
        <>
            <BGCPromoGearHeader/>
            <Container fluid className={"mt-5 mb-5 min-vh-100 justify-content-center"}>
                <Card className={"shadow-sm"}>
                    <Card.Body>
                        <Row>
                            <Col>
                                <Row>
                                    <h3>Promotional Gear</h3>
                                </Row>
                                <Row>
                                    <Col>
                                        <Row>
                                            <h6 className={"mb-3"}>Categories</h6>
                                        </Row>
                                        <Row>{
                                            isLoadingCategories && <div><CircularProgress/></div>
                                            || isErrorCategories && "Failed to load categories."
                                            || <CategorySelection categories={categories} onChange={null}/>}
                                        </Row>
                                    </Col>
                                    <Col xs={12} sm={8} lg={10}>
                                        <Row>{isLoadingProducts &&
                                        Array.from({length: 4}, (v, i) => (
                                            <Col md={"auto"} key={i}>
                                                <LoadingCard key={i}/>
                                            </Col>
                                        ))
                                        || isErrorProducts && <span>Failed to load products.</span>
                                        || products?.length > 0 && products.map(item => (
                                            <Col md={"auto"} key={item.id}>
                                                <ProductCard key={item.id} product={item}/>
                                            </Col>))
                                        || !isLoadingProducts && <span>No items found.</span>}
                                        </Row>
                                    </Col>
                                </Row>
                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Store;
