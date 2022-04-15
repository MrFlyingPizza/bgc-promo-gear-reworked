import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {Container, Grid, Paper} from "@mui/material";
import {grey} from "@mui/material/colors";
import {ICartItem} from "./ICartItem";
import CartContent from "./CartContent";
import CartSummary from "./CartSummary";

function Cart() {

    const url = `${location.protocol}//${location.host}`

    const [items, setItems] = useState<ICartItem[]>(null);

    useEffect(()=>{
        axios.get(`${url}/api/users/me/cart_items`)
            .then((response) => {
                setItems(response.data.cartItems);
            }).catch((error) => {
                console.log(error);
            });
    },[]);

    return (
        <Container maxWidth={"sm"} sx={{backgroundColor: grey[100], minHeight: "500px"}}>
            <Grid container spacing={2}>
                <Grid item xs={8}>
                    <Paper elevation={2}>
                        {items && <CartContent items={items}/>}
                    </Paper>
                </Grid>
                <Grid item xs={4}>
                    <Paper elevation={2}>
                        {items && <CartSummary items={items}/>}
                    </Paper>
                </Grid>
            </Grid>
        </Container>
    );
}

export default Cart;
