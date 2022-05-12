import * as React from "react";
import axios from "axios";
import CartSummary from "./CartSummary";
import CartItem from "types/CartItem";
import {useQuery} from "react-query";
import CartContent from "components/cart/CartContent";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import {Container, Grid} from "@mui/material";

function Cart() {

    const {isLoading, isError, data: items, refetch} = useQuery("cart_items", () =>
        axios.get("/api/users/me/cart_items").then<CartItem[]>(response => response.data.cartItems));

    return (
        <>
            <BGCPromoGearHeader/>
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={8}>
                        <CartContent isError={isError} isLoading={isLoading} items={items || []}
                                     onRemove={() => refetch()} onUpdate={() => refetch()}/>
                    </Grid>
                    <Grid item xs={12} sm>
                        <CartSummary isError={isError} isLoading={isLoading} items={items || []}/>
                    </Grid>
                </Grid>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Cart;
