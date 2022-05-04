import Category from "types/Category";
import {Divider, List} from "@mui/material";
import CategorySelectionGroup from "components/store/category_list/CategorySelectionGroup";
import React from "react";

type CategorySelectionProps = {
    categories: Category[],
    onChange: (selectedIds: number[]) => void
};

const CategorySelection = ({categories, onChange}: CategorySelectionProps) => {
    return (
        <List>{categories.map(category =>
            <React.Fragment key={category.id}>
                <Divider/>
                <CategorySelectionGroup category={category} onSelect={onChange}/>
            </React.Fragment>)}
        </List>
    )
}

export default CategorySelection;