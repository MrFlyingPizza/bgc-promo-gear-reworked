import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import CartSummary from "./CartSummary";
import CartItem from "types/CartItem";
import {useQuery} from "react-query";
import CartContent from "components/checkout/CartContent";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import {Button, Container, Grid, Grow, Stack} from "@mui/material";
import OrderForm from "components/checkout/OrderForm";
import ProductVariantAvailability from "types/ProductVariantAvailability";

enum CartErrorCode {
    // start from 1 because 0 is falsy
    LOADING = 1,
    ERROR,
    EMPTY,
    UNAVAILABLE_ITEM,
    NONE
}

function Checkout() {

    const {isLoading, isError, data: items, refetch} = useQuery("cart_items", () =>
        axios.get("/api/users/me/cart_items").then<CartItem[]>(response => response.data.cartItems));

    const [isCompleting, setIsCompleting] = useState(false);
    const [errorCode, setErrorCode] = useState<CartErrorCode>();

    useEffect(() => {
        const errorCode =
            isLoading && CartErrorCode.LOADING ||
            isError && CartErrorCode.ERROR ||
            items?.length == 0 && CartErrorCode.EMPTY ||
            items?.some(({variant: {availability}}) =>
                availability == ProductVariantAvailability.UNAVAILABLE
            ) && CartErrorCode.UNAVAILABLE_ITEM ||
            CartErrorCode.NONE
        ;
        setErrorCode(errorCode);
    }, [isLoading, isError, items])

    useEffect(() => {
        errorCode && setIsCompleting(false);
    }, [errorCode])

    function toggle() {
        setIsCompleting(!isCompleting);
    }

    return (
        <>
            <BGCPromoGearHeader/>
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <Grid container spacing={2}>
                    {
                        !isCompleting &&
                        <Grow in appear>
                            <Grid item xs={12} sm={8}>
                                <CartContent isError={isError} isLoading={isLoading} items={items || []}
                                             onRemove={() => refetch()} onUpdate={() => refetch()}/>
                            </Grid>
                        </Grow>
                    }
                    <Grid item xs={12} sm>
                        <Stack spacing={2}>
                            <CartSummary isError={isError} isLoading={isLoading} items={items || []}/>
                            <Button disabled={errorCode != CartErrorCode.NONE} onClick={toggle}
                                    variant={isCompleting ? "outlined" : "contained"}>
                                {isCompleting ? "Back" : "Continue"}
                            </Button>
                            {
                                errorCode == CartErrorCode.LOADING && "Loading items, please wait." ||
                                errorCode == CartErrorCode.ERROR && "Failed to load items, cannot continue." ||
                                errorCode == CartErrorCode.EMPTY && "Add items to cart to continue." ||
                                errorCode == CartErrorCode.UNAVAILABLE_ITEM && "Please remove unavailable items."
                            }
                        </Stack>
                    </Grid>
                    {
                        isCompleting &&
                        <Grow in appear>
                            <Grid item xs={12} sm={8}>
                                <OrderForm/>
                            </Grid>
                        </Grow>
                    }
                </Grid>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Checkout;
