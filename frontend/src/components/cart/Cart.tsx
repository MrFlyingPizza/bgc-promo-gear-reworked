import * as React from "react";
import axios from "axios";
import CartSummary from "./CartSummary";
import {CartItem} from "types/CartItem";
import {useQuery} from "react-query";
import {Col, Container, Row} from "react-bootstrap";
import CartContent from "components/cart/CartContent";

function Cart() {

    const {isLoading, isError, data: items} = useQuery("cart_items", () => axios.get("/api/users/me/cart_items")
        .then<CartItem[]>(response => response.data.cartItems));

    return (
        <Container className={"mt-5 mb-5 min-vh-100"}>
            <Row>
                <Col md={8}>
                    <CartContent isError={isError} isLoading={isLoading} items={items || []}/>
                </Col>
                <Col md={4}>
                    <CartSummary isError={isError} isLoading={isLoading} items={items || []}/>
                </Col>
            </Row>
        </Container>
    );
}

export default Cart;
