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

type SelectedCategory = {
    id: number,
    name: string,

    parent?: {
        id: number,
        name: string
    }
}

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

    const [selectedCategory, setSelectedCategory] = useState<SelectedCategory>(null);


    const loadingCards = isLoadingProducts && Array.from({length: 4}, (v, i) => (
        <Grid item>
            <LoadingCard key={i}/>
        </Grid>
    ));

    return (
        <>
            <BGCPromoGearHeader/>
            <Container fluid className={"mt-5 mb-5 min-vh-100"}>
                <Card className={"shadow-sm"}>
                    <Card.Body>
                        <Row>
                            <Col md={2}>
                                <Row>
                                    <h4>Categories</h4>
                                </Row>
                                <Row>{
                                    isLoadingCategories && <div><CircularProgress/></div>
                                    || isErrorCategories && "Failed to load categories."
                                    || <CategorySelection categories={categories} onChange={null}/>}
                                </Row>
                            </Col>
                            <Col md={10}>
                                <Row>
                                    <Container fluid className={"border-bottom border-2 mb-3"}>
                                        <h3>Promotional Gear</h3>
                                    </Container>
                                </Row>
                                <Row>
                                    <Grid justifyContent={"center"} container spacing={{xs: 2, md: 3}}
                                          columns={{xs: 4, sm: 8, md: 12}}>
                                        {isLoadingProducts && loadingCards}
                                        {isErrorProducts && <span>Failed to load products.</span>}
                                        {products?.length > 0 && products.map(item => (
                                                <Grid item key={item.id}>
                                                    <ProductCard key={item.id} product={item}/>
                                                </Grid>))
                                            || !isLoadingProducts && <span>No items found.</span>}
                                    </Grid>
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
