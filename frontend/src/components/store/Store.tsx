import * as React from "react";
import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {CircularProgress, Skeleton, Stack, Typography} from "@mui/material";

import {Product} from "types/Product";
import {Category} from "types/Category";
import ProductCard from "./product_card/ProductCard";
import StoreContainer from "components/shared/StoreContainer";
import {Container} from "react-bootstrap";

type SelectedCategory = {
    id: number,
    name: string,

    parent?: {
        id: number,
        name: string
    }
}

function Store() {

    const url = `${location.protocol}//${location.host}`

    const [isLoadingProducts, setIsLoadingProducts] = useState(true);
    const [isLoadingCategories, setIsLoadingCategories] = useState(true);
    const [products, setProducts] = useState<Product[]>(null);
    const [categories, setCategories] = useState<Category[]>(null);
    const [selectedCategory, setSelectedCategory] = useState<SelectedCategory>(null);

    useEffect(() => {
        setProducts([]);
        setIsLoadingProducts(true);
        let params = {
            "isPublished": true,
            "category.id": selectedCategory?.id
        };
        axios.get(`${url}/api/products`, {params: params}).then((response) => {
            setProducts(response.data.products);
        }).catch((error) => {
            console.error(error);
        }).finally(() => {
            setIsLoadingProducts(false);
        });
    }, [selectedCategory]);

    useEffect(() => {
        setIsLoadingCategories(true);
        axios.get(`${url}/api/categories`).then((response) => {
            setCategories(response.data.categories.filter((category: { parent: Category }) => category.parent == null));
        }).catch((error) => {
            console.error(error);
        }).finally(() => {
            setIsLoadingCategories(false);
        });
    }, []);

    const Header = (props: { title: string }) => {
        return (
            <Typography align={"center"} variant={"h6"}>{props.title}</Typography>
        )
    }

    const LoadingCard = () => {
        return (
            <Container style={{width: 300, height: 500}}>
                <Stack spacing={2}>
                    <Skeleton variant={"rectangular"} width={300} height={300}/>
                    <Container className={"d-flex flex-wrap justify-content-center"}>
                        <Stack spacing={2} direction={"row"}>
                            <Skeleton variant={"circular"} width={24} height={24}/>
                            <Skeleton variant={"circular"} width={24} height={24}/>
                            <Skeleton variant={"circular"} width={24} height={24}/>
                        </Stack>
                    </Container>
                    <Skeleton variant={"text"}/>
                    <Skeleton variant={"text"}/>
                    <Skeleton variant={"text"} width={"60%"}/>
                </Stack>
            </Container>
        )
    }

    return (
        <StoreContainer>
            <div className="d-flex" id="top-container">
                <div id="empty-space"/>
                <div>
                    <div id="breadcrumb-container">
                        <ol className="breadcrumb">
                            <li className="breadcrumb-item" onClick={() => setSelectedCategory(null)}>
                                <a className="no-style-link">
                                    <span>Promotional Gear</span>
                                </a>
                            </li>
                            {
                                selectedCategory && (
                                    <li className="breadcrumb-item">
                                        <a className="no-style-link">
                                            <span>{selectedCategory.name}</span>
                                        </a>
                                    </li>
                                ) || (
                                    <li className="breadcrumb-item">
                                        <a className="no-style-link">
                                            <span>All</span>
                                        </a>
                                    </li>
                                )
                            }
                        </ol>
                    </div>
                </div>
            </div>
            <div className="d-flex" id="bottom-container">
                <div id="category-container">
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
                </div>
                <div className="gallery-container">
                    {
                        products?.length > 0 && products.map((item) => (<ProductCard key={item.id} product={item}/>))
                        || (!isLoadingProducts && <span>No items found.</span>)
                    }
                    {isLoadingProducts && Array.from({length: 3}, (v, i) => <LoadingCard key={i}/>)}
                </div>
            </div>
        </StoreContainer>
    );
}

export default Store;
