import Category from "types/Category";
import {Divider, List} from "@mui/material";
import CategorySelectionGroup, {
    CategorySelectionCallback
} from "components/store/category_list/CategorySelectionGroup";
import React from "react";

type CategorySelectionProps = {
    categories: Category[],
    onChange: CategorySelectionCallback
};

const CategorySelection = ({categories, onChange}: CategorySelectionProps) => {
    return (
        <List>{categories.map(category =>
            <React.Fragment key={category.id}>
                <Divider/>
                <CategorySelectionGroup category={category} onChange={onChange}/>
            </React.Fragment>)}
        </List>
    )
}

export default CategorySelection;