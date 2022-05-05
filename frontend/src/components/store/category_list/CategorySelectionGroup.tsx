import {Checkbox, Collapse, List, ListItem, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import React, {useEffect, useRef} from "react";
import {useState} from "react";
import Category from "types/Category";
import {ExpandLess, ExpandMore} from "@mui/icons-material";

export type CategorySelectionCallback = (previousSelectedIds: number[], selectedIds: number[]) => void;

type CategorySelectionGroupProps = {
    category: Category,
    onChange: CategorySelectionCallback
};

const CategorySelectionGroup = (
    {
        category: {id: parentId, name: parentName, subcategories}, onChange
    }: CategorySelectionGroupProps
) => {

    const categories = [...subcategories, {id: parentId, name: subcategories.length > 0 ? "Other" : "All"}];

    const [selectedIds, setSelectedIds] = useState<number[]>([]);
    const previousSelectedIds = useRef<number[]>();
    const [open, setOpen] = useState(false);

    useEffect(() => {
        previousSelectedIds.current && onChange(previousSelectedIds.current, selectedIds);
        previousSelectedIds.current = selectedIds;
    }, [selectedIds]);

    //region Selection Control
    function isSelected(id: number) {
        return selectedIds.indexOf(id) > -1;
    }

    function select(id: number) {
        isSelected(id) || setSelectedIds([...selectedIds, id]);
    }

    function deselect(id: number) {
        isSelected(id) && setSelectedIds(selectedIds.filter(selectedId => selectedId != id));
    }

    function selectAll() {
        setSelectedIds(categories.map(({id}) => id));
    }

    function deselectAll() {
        setSelectedIds([]);
    }

    function isAllSelected() {
        return categories.length == selectedIds.length;
    }

    function isIndeterminate() {
        return !isAllSelected() && selectedIds.length > 0;
    }
    //endregion

    function toggle() {
        setOpen(!open);
    }

    return (
        <>
            <ListItem disableGutters disablePadding>
                <Collapse orientation={"horizontal"} in={open} timeout={"auto"} unmountOnExit>
                    <ListItemIcon>
                        <Checkbox checked={isAllSelected()}
                                  indeterminate={isIndeterminate()}
                                  onChange={event => event.target.checked ? selectAll() : deselectAll()}/>
                    </ListItemIcon>
                </Collapse>
                <ListItemButton selected={selectedIds.length > 0} onClick={toggle}>
                    <ListItemText primary={<h6>{parentName}</h6>}/>{open ? <ExpandLess/> : <ExpandMore/>}
                </ListItemButton>
            </ListItem>
            <Collapse in={open} timeout={"auto"} unmountOnExit>
                <List disablePadding>{categories.map(({id, name}) => (
                    <ListItemButton key={id} disableGutters sx={{pl: 4}}
                                    onClick={() => isSelected(id) ? deselect(id) : select(id)}>
                        <ListItemIcon>
                            <Checkbox checked={isSelected(id)}/>
                        </ListItemIcon>
                        <ListItemText primary={name}/>
                    </ListItemButton>))}
                </List>
            </Collapse>
        </>
    );
}

export default CategorySelectionGroup;