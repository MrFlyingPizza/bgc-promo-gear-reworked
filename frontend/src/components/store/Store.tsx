import * as React from "react";
import {useState} from "react";
import axios from "axios";
import {CircularProgress} from "@mui/material";

import {Product} from "types/Product";
import {Category} from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import {useQuery} from "react-query";
import {Breadcrumb, Card, Col, Container, Row} from "react-bootstrap";

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
    } = useQuery("products", () => axios.get("/api/products").then<Product[]>(response => response.data.products), {retry: false});

    const {
        isLoading: isLoadingCategories,
        isError: isErrorCategories,
        data: categories
    } = useQuery("categories", () => axios.get("/api/categories").then<Category[]>(response => response.data.categories), {retry: false});

    const [selectedCategory, setSelectedCategory] = useState<SelectedCategory>(null);

    return (
        <Container fluid={"md"} className={"mt-5 mb-5 min-vh-100"}>
            <Card>
                <Card.Body>
                    <Container>
                        <Row>
                            <Col sm={3}>
                                <Container className={"border-right border-3"}>
                                    <span style={{fontWeight: 'bold'}}>Categories</span>
                                    <div id="categories">
                                        <div className="category-group">
                                            <span className="category_name" onClick={() => setSelectedCategory(null)}>All</span>
                                        </div>
                                        {categories && categories.map((category) => (
                                            <div key={category.id} className="category-group"><span
                                                className="category_name">{category.name}</span>
                                                {category.subcategories && category.subcategories.map((subcategory) => (
                                                    <div key={subcategory.id} className="d-flex flex-column subcategory-group">
                                                        <a className="no-style-link" onClick={() => setSelectedCategory(subcategory)}>
                                                            <span>{subcategory.name}</span>
                                                        </a>
                                                    </div>
                                                ))}
                                                <div className="d-flex flex-column subcategory-group">
                                                    <a className="no-style-link" onClick={() => setSelectedCategory(category)}>
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
                            <Col sm={9}>
                                <Row>
                                    <Breadcrumb>
                                        <Breadcrumb.Item>
                                            <a className={"text-decoration-none"}>Promotional Gear</a>
                                        </Breadcrumb.Item>
                                        <Breadcrumb.Item>{
                                            selectedCategory &&
                                            <a className={"text-decoration-none"}>{selectedCategory.name}</a>
                                        }
                                        </Breadcrumb.Item>
                                    </Breadcrumb>
                                </Row>
                                <Row>
                                    {
                                        isLoadingProducts && Array.from({length: 3}, (v, i) => <LoadingCard key={i}/>)
                                        || isErrorProducts && <span>Failed to load products.</span>
                                        || products?.length > 0 && products.map(item => <ProductCard key={item.id} product={item}/>)
                                        || <span>No items found.</span>
                                    }
                                </Row>
                            </Col>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>
        </Container>
    );
}

export default Store;
