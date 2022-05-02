import React, {useState} from "react";
import {Checkbox, Collapse, List, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import {ExpandLess, ExpandMore} from "@mui/icons-material";

export type CategoryProps = {
    id: number,
    name: string,
    subcategories?: CategoryProps[]
};

export type CategoryListProps = {
    categories: CategoryProps[]
};

type CategoryListItemGroupProps = {
    category: CategoryProps,
    onSelect: (ids: number[]) => void
};

const CategoryListItemGroup = ({category, onSelect}: CategoryListItemGroupProps) => {

    const subcategories = [...category.subcategories].push({id: category.id, name: "Other"})

    const [open, setOpen] = useState(false);
    const [selected, setSelected] = useState<number[]>([]);

    function isSelected(id: number) {
        return selected.indexOf(id) != -1;
    }

    function addSelected(id: number) {
        const newSelected = [...selected, id]
        setSelected(newSelected);
        return newSelected;
    }

    function removeSelected(id: number) {
        const newSelected = selected.filter(currentSelected => currentSelected != id);
        setSelected(newSelected);
        return newSelected;
    }

    function handleItemClick(id: number) {
        const newSelected = isSelected(id) ? removeSelected(id) : addSelected(id);
        onSelect(newSelected);
    }

    return (
        <>
            <ListItemButton onClick={() => setOpen(!open)}>
                <ListItemIcon>
                    <Checkbox
                        indeterminate={selected.length != category.subcategories?.length && selected.length == 0}
                        checked={selected.length == category.subcategories?.length || undefined}/>
                </ListItemIcon>
                <ListItemText primary={category.name}/>
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open}>
                <List>{category.subcategories.map(subcategory => (
                    <ListItemButton onClick={() => handleItemClick(subcategory.id)}>
                        <ListItemIcon>
                            <Checkbox edge={"start"} disableRipple checked={isSelected(subcategory.id)}/>
                        </ListItemIcon>
                        <ListItemText primary={subcategory.name}/>
                    </ListItemButton>))}
                </List>
            </Collapse>
        </>
    )

}

const CategoryList = ({categories}: CategoryListProps) => {

    function handleSelect(selected: number[]) {
        console.log(selected);
    }

    return (
        <List>{categories.map(category => category.subcategories?.length > 0 &&
            <CategoryListItemGroup category={category} onSelect={handleSelect}/>
        ) || (
            <ListItemButton className={"pl-3"}/>
        )}
        </List>
    );

};

export default CategoryList;