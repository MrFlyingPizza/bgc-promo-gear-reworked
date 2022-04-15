import {ICartItem} from "./ICartItem";
import {Container} from "@mui/material";
import {grey} from "@mui/material/colors";
import * as React from "react";

function CartSummary(props: {items: ICartItem[]}) {
    return (
        <Container sx={{backgroundColor: grey[200], minHeight: "100px"}}>

        </Container>
    );
}

export default CartSummary;