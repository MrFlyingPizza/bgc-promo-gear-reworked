import * as React from "react";
import CartItem from "types/CartItem";
import {Badge, Card, ListGroup, ListGroupItem} from "react-bootstrap";
import {Button, Chip, Fade, LinearProgress, Tooltip} from "@mui/material";

type CartSummaryProps = {
    items: CartItem[],
    isLoading: boolean,
    isError: boolean
}

function CartSummary({items, isLoading, isError}: CartSummaryProps) {

    return (
        <Card className={"shadow-sm"}>
            <Card.Header>Summary</Card.Header>{
            isLoading && <LinearProgress/>
            || isError && <Card.Body>Failed to load items.</Card.Body>
            || items.length == 0 && <Card.Body>No items in cart.</Card.Body>
            ||
            <ListGroup variant={"flush"} as={"ul"}>{items.map(({quantity, variant: {id, options}, product: {name}}: CartItem) => (
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
                <ListGroupItem>
                    <Button variant={"contained"}>Continue to Order</Button>
                </ListGroupItem>
            </ListGroup>
        }
        </Card>
    );
}

export default CartSummary;