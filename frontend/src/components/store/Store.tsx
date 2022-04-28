import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {CircularProgress} from "@mui/material";

import {Product} from "types/Product";
import {Category} from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import StoreContainer from "components/shared/StoreContainer";

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
                <div className="d-flex justify-content-around flex-wrap align-items-start">
                    {
                        isLoadingProducts && Array.from({length: 3}, (v, i) => <LoadingCard key={i}/>)
                        || products?.length > 0 && products.map(item => <ProductCard key={item.id} product={item}/>)
                        || <span>No items found.</span>
                    }
                </div>
            </div>
        </StoreContainer>
    );
}

export default Store;
