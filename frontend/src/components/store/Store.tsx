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
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <Card className={"shadow-sm"}>
                    <Card.Body>
                        <Container>
                            <Row>
                                <Col sm={2}>
                                    <Container className={"bg-light"}>
                                        <span style={{fontWeight: 'bold'}}>Categories</span>
                                        <div id="categories">
                                            <div className="category-group">
                                            <span className="category_name"
                                                  onClick={() => setSelectedCategory(null)}>All</span>
                                            </div>
                                            {categories && categories.map((category) => (
                                                <div key={category.id} className="category-group"><span
                                                    className="category_name">{category.name}</span>
                                                    {category.subcategories && category.subcategories.map((subcategory) => (
                                                        <div key={subcategory.id}
                                                             className="d-flex flex-column subcategory-group">
                                                            <a className="no-style-link"
                                                               onClick={() => setSelectedCategory(subcategory)}>
                                                                <span>{subcategory.name}</span>
                                                            </a>
                                                        </div>
                                                    ))}
                                                    <div className="d-flex flex-column subcategory-group">
                                                        <a className="no-style-link"
                                                           onClick={() => setSelectedCategory(category)}>
                                                            <span>Other</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            ))}
                                        </div>
                                        {isLoadingCategories && <CircularProgress/>}
                                        {isErrorCategories && <span>Failed to load categories.</span>}
                                    </Container>
                                </Col>
                                <Col sm={10}>
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
                        </Container>
                    </Card.Body>
                </Card>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Store;
