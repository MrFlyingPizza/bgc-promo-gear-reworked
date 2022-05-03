import {CartItem} from "types/CartItem";
import {Card, Stack} from "react-bootstrap";
import React from "react";
import CartContentItem from "components/cart/CartContentItem";
import {LinearProgress} from "@mui/material";

type CartContentProps = {
    isLoading: boolean,
    isError: boolean,
    items: CartItem[]
}

const CartContent = ({items, isLoading, isError}: CartContentProps) => {
    return (
        <Card className={"shadow-sm"}>
            <Card.Header>Shopping Cart</Card.Header>
            <Card.Body>
                {isLoading && <LinearProgress/> || items.length == 0 && "No items in cart."}
                {isError && "Failed to load items."}
                <Stack gap={2}>{items.map(item =>
                    <CartContentItem initialItem={item}/>)}
                </Stack>
            </Card.Body>
        </Card>
    )
}

export default CartContent;