import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {Box, CircularProgress, Container, Grid, LinearProgress, Typography} from "@mui/material";
import {grey} from "@mui/material/colors";

import {IProductItem} from "./IProductItem";
import {ICategoryItem} from "./ICategoryItem";

function Store() {

    const url = `${location.protocol}//${location.host}`

    const [items, setItems] = useState<IProductItem[]>(null);
    const [categories, setCategories] = useState<ICategoryItem[]>(null);
    const [itemStatus, setItemStatus] = useState<{[id : number] : {isWaitlisted : boolean, isOutOfStock : boolean}}>({});

    //TODO: Switch back to general product API (temporary measure to get needed attributes)
    useEffect(()=>{
        axios.get(`${url}/api/secured/products?isPublished=true`).then((response) => {
            setItems(response.data.products);
        }).catch((error) => {
            console.log(error);
        }).finally(() => {
        });

        axios.get(`${url}/api/categories`).then((response) => {
            let tempCategories = response.data.categories;
            console.log(response.data.categories);
            for(let key in tempCategories){
                if(tempCategories[key].parent != null){
                    delete tempCategories[key];
                }
            }
            console.log(tempCategories);
            setCategories(tempCategories);
        }).catch((error) => {
            console.log(error);
        }).finally(() => {
        });
    },[]);

/*
    useEffect(()=>{
        for (let itemKey in items){
            let variants = items[itemKey].variants;
            let isOutOfStock = false;
            let isWaitlisted = false;
            let isWaitlistEnabled = items[itemKey].variants;

            for(let key in variants){
                let waitListThreshold;
                let apparentQuantity;
                axios.get(`${url}/api/secured/inventory_levels/global/${variants[key].id}`).then((response) => {
                    console.log(response.data)
                    waitListThreshold = response.data.variant.waitListThreshold;
                    apparentQuantity = response.data.apparentQuantity;
                    if(apparentQuantity <= waitListThreshold && isWaitlistEnabled){
                        isWaitlisted = true;
                    } else if (apparentQuantity <= waitListThreshold) {
                        isOutOfStock = true;
                    }
                }).catch((error) => {
                    console.log(error);
                }).finally(() => {
                    let tempItemStatus = itemStatus;
                    tempItemStatus[items[itemKey].id] = {isWaitlisted, isOutOfStock};
                    console.log(tempItemStatus);
                    setItemStatus(tempItemStatus);
                });
            }
        }
    },[items]); */


    const Header = (props: {title: string}) => {
        return (
            <Typography align={"center"} variant={"h6"}>{props.title}</Typography>
        )
    }

    const StockLabel = (props : {product : IProductItem}) => {
        /*
        let productStatus = itemStatus[props.product.id];
        console.log("Product Status:");
        console.log(productStatus);
        if(productStatus){
            console.log(props.product.id);
            console.log(productStatus.isOutOfStock);
            console.log(productStatus.isWaitlisted);
        } */

        if(false) {
            return (
                <div className="out-of-stock-label">Out of Stock</div>
            )
        } else {
            return;
        }


    }

    //Returns main image of product, otherwise shows default image
    const Image = (props: {product : IProductItem}) => {
        console.log(props.product);
        let images = props.product.images;
        if(images.length){
            let mainImage = images[0];
            //assigns main image as image with smallest position
            for(let key in images){
                if(images[key].position <= mainImage.position){
                    mainImage = images[key];
                }
            }
            return(
                <img className="product-image"
                 src={mainImage.src}/>
            );
        } else {
            return (
                <img className="product-image"
                     src={"https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg"}/>
            );
        }
    }

    return (
        <div className="d-flex flex-column" id="store-container">
                    <div className="d-flex" id="top-container">
                        <div id="empty-space"></div>
                        <div>
                            <div id="breadcrumb-container">
                                <ol className="breadcrumb">
                                    <li className="breadcrumb-item"><a className="no-style-link"
                                                                       href="#"><span>Promotional Gear</span></a></li>
                                    <li className="breadcrumb-item"><a className="no-style-link" href="#"><span>All</span></a>
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <div className="d-flex" id="bottom-container">
                        <div id="category-container"><span style={{fontWeight: 'bold'}}>Categories</span>
                            <div id="categories">
                                { categories && categories.map((category) => (
                                    <div className="category-group"><a className="no-style-link" href="#"><span
                                        className="category_name">{category.name}</span></a>
                                        { category.subcategories && category.subcategories.map((subcategory) => (
                                        <div className="d-flex flex-column subcategory-group"><a className="no-style-link"
                                                                                                 href="#"><span>{subcategory.name}</span></a>
                                        </div>
                                        ))}
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className="gallery-container">
                            { items && items.map((item) => (
                                <a href={"/store/product/?id=" + item.id} style={{textDecoration: 'none', color: 'black'}}>
                                    <div className="product-container">
                                        <div className="image_container">
                                            <Image product={item}/>
                                            <StockLabel product={item} />
                                            <div className="description-container">
                                                <span className={"description"}>
                                                {item.description}
                                            </span>

                                            </div>
                                        </div>
                                        <div className="product_name_container">
                                        <span className="product_name">
                                            {item.name}
                                        </span>
                                        </div>
                                    </div>
                                </a>
                            ))
                            }
                        </div>
                    </div>
                </div>
    );
}

export default Store;
