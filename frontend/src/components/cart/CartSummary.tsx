import * as React from "react";
import {CartItem} from "types/CartItem";
import {Badge, Card, ListGroup} from "react-bootstrap";
import {Chip, LinearProgress, Tooltip} from "@mui/material";

type CartSummaryProps = {
    items: CartItem[],
    isLoading: boolean,
    isError: boolean
}

function CartSummary({items, isLoading, isError}: CartSummaryProps) {

    return (
        <Card className={"shadow-sm"}>
            <Card.Header>Summary</Card.Header>
            <Card.Body>
                {isLoading && <LinearProgress/> || items.length == 0 && "No items in cart."}
                {isError && "Failed to load items."}
                <ListGroup as={"ul"}>
                    {items.map(({quantity, variant: {options}, product: {name}}: CartItem) => (
                        <ListGroup.Item>
                            <div className={"d-block position-absolute top-0 end-0"}>
                                <Badge pill>{quantity}</Badge>
                            </div>
                            <h6>{name}</h6>
                            {options.map(({name, value}) =>
                                <Tooltip title={name}>
                                    <Chip size={"small"} label={value}/>
                                </Tooltip>
                            )}
                        </ListGroup.Item>
                    ))}
                </ListGroup>
            </Card.Body>
        </Card>
    );
}

export default CartSummary;