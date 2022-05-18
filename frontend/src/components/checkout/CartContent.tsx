import CartItem from "types/CartItem";
import {Card, Stack} from "react-bootstrap";
import React from "react";
import CartContentItem from "components/checkout/CartContentItem";
import {LinearProgress} from "@mui/material";

type CartContentProps = {
    isLoading: boolean,
    isError: boolean,
    onRemove: () => void,
    onUpdate: () => void,
    items: CartItem[]
}

const CartContent = ({items, isLoading, isError, onRemove, onUpdate}: CartContentProps) => {

    return (
        <Card className={"shadow-sm"}>
            <Card.Header>Shopping Cart</Card.Header>{

            isLoading && <LinearProgress/>
            ||
            <Card.Body>{
                isError && "Failed to load items."
                || items.length == 0 && "No items in cart."
                ||
                <Stack gap={2}>{items.map(item =>
                    <CartContentItem key={item.variant.id} initialItem={item} onRemove={onRemove}
                                     onUpdate={onUpdate}/>)}
                </Stack>}
            </Card.Body>}
        </Card>
    )
}

export default CartContent;