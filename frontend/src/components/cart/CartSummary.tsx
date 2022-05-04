import * as React from "react";
import CartItem from "types/CartItem";
import {Badge, Card, ListGroup} from "react-bootstrap";
import {Chip, Fade, LinearProgress, Tooltip} from "@mui/material";

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
                {
                    isLoading && <LinearProgress/>
                    || isError && "Failed to load items."
                    || items.length == 0 && "No items in cart."
                }
                <ListGroup as={"ul"}>{items.map(({quantity, variant: {id, options}, product: {name}}: CartItem) => (
                    <Fade key={id} in={true} appear={true}>
                        <ListGroup.Item key={id}>
                            <div className={"d-block position-absolute top-0 end-0"}>
                                <Badge pill>{quantity}</Badge>
                            </div>
                            <h6>{name}</h6>{options.map(({name, value}) =>
                            <Tooltip key={name} title={name}>
                                <Chip size={"small"} label={value}/>
                            </Tooltip>)}
                        </ListGroup.Item>
                    </Fade>))}
                </ListGroup>
            </Card.Body>
        </Card>
    );
}

export default CartSummary;