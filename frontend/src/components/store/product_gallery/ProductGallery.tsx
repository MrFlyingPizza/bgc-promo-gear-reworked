import Category from "types/Category";
import {Accordion} from "react-bootstrap";
import React from "react";

type ProductGalleryProps = {
    categories: Category[],
    selectedCategoryIds: number[]
}

const ProductGallery = ({categories, selectedCategoryIds}: ProductGalleryProps) => {

    return (
        <Accordion flush alwaysOpen defaultActiveKey={[]}>{

        }
        </Accordion>
    )
}