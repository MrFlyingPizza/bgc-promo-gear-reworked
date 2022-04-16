import * as React from "react";
import {useEffect, useState} from "react";
import axios from "axios";
import {Box, CircularProgress, Container, Grid, LinearProgress, Typography} from "@mui/material";
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
    
    const Header = (props: {title: string}) => {
        return (
            <Typography align={"center"} variant={"h6"}>{props.title}</Typography>
        )
    }

    return (
        <Container disableGutters maxWidth={"md"} sx={{backgroundColor: "white", minHeight: "80vh", mt: 4, mb: 4}}>
            {isLoading && <LinearProgress/>}
            <Box sx={{justifyContent: "center"}}>
                <Grid container spacing={2}>
                    <Grid item xs={8}>
                        <Header title={"Items"}/>
                        <Container>
                            {items && <CartContent items={items}/>}
                        </Container>
                    </Grid>
                    <Grid item xs={4}>
                        <Header title={"Summary"}/>
                        <Container>
                            {items && <CartSummary items={items}/>}
                        </Container>
                    </Grid>
                </Grid>
            </Box>
        </Container>
    );
}

export default Cart;
