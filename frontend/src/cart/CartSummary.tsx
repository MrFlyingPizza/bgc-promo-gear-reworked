import {ICartItem} from "./ICartItem";
import {Button, Container, List, ListItem, ListItemText, Paper} from "@mui/material";
import * as React from "react";
import {useState} from "react";

function CartSummary(props: {items: ICartItem[]}) {

    const [items, setItems] = useState<ICartItem[]>(props.items);

    const summaryItems = items.map(item => {
        return (<ListItemText key={item.variant.id} primary={item.product.name} secondary={item.quantity}/>);
    });

    return (
        <Paper elevation={2}>
            <Container sx={{minHeight: "100px"}}>
                <List>{summaryItems}
                    <ListItem>
                        <Button variant={"contained"}>Continue</Button>
                    </ListItem>
                </List>
            </Container>
        </Paper>
    );
}

export default CartSummary;