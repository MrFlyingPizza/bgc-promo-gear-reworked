import Category from "types/Category";
import {List} from "@mui/material";
import CategorySelectionGroup from "components/store/category_list/CategorySelectionGroup";
import React from "react";

type CategorySelectionProps = {
    categories: Category[],
    onChange: (selectedIds: number[]) => void
};

const CategorySelection = ({categories, onChange}: CategorySelectionProps) => {
    return (
        <List>{categories.map(category =>
            <CategorySelectionGroup category={category} onSelect={console.log}/>)}
        </List>
    )
}

export default CategorySelection;