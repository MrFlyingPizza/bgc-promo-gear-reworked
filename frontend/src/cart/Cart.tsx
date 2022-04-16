import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {Box, CircularProgress, Container, Grid} from "@mui/material";
import {grey} from "@mui/material/colors";
import {ICartItem} from "./ICartItem";
import CartContent from "./CartContent";
import CartSummary from "./CartSummary";

function Cart() {

    const url = `${location.protocol}//${location.host}`

    const [items, setItems] = useState<ICartItem[]>(null);

    const [isLoading, setLoading] = useState<Boolean>(true);

    useEffect(()=>{
        axios.get(`${url}/api/users/me/cart_items`).then((response) => {
            setItems(response.data.cartItems);
        }).catch((error) => {
            console.log(error);
        }).finally(() => {
            setLoading(false);
        });
    },[]);

    return (
        <Container maxWidth={"md"} sx={{backgroundColor: grey[50], minHeight: "80vh", mt: 4, mb: 4}}>
            <Box sx={{justifyContent: "center"}}>
                <Grid container spacing={2}>
                    {isLoading && <CircularProgress/>}
                    <Grid item xs={8}>
                        {items && <CartContent items={items}/>}
                    </Grid>
                    <Grid item xs={4}>
                        {items && <CartSummary items={items}/>}
                    </Grid>
                </Grid>
            </Box>
        </Container>
    );
}

export default Cart;
