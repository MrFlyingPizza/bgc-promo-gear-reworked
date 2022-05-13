import Category from "types/Category";
import {Accordion, AccordionDetails, AccordionSummary, Divider, Grid} from "@mui/material";
import CategorySelectionGroup, {
    CategorySelectionCallback
} from "components/store/category_list/CategorySelectionGroup";
import React from "react";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

type CategorySelectionProps = {
    categories: Category[],
    onChange: CategorySelectionCallback
};

const CategorySelection = ({categories, onChange}: CategorySelectionProps) => {
    return (
        <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon/>}>Categories</AccordionSummary>
            <AccordionDetails>
                <Grid container>{categories.map(category =>
                    <Grid item xs={12} sm={6} md={"auto"} key={category.id}>
                        <Divider/>
                        <CategorySelectionGroup category={category} onChange={onChange}/>
                    </Grid>)}
                </Grid>
            </AccordionDetails>
        </Accordion>
    )
}

export default CategorySelection;