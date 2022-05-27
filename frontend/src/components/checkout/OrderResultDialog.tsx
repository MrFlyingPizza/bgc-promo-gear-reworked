import Order from "types/Order";
import {Dialog, DialogTitle, Grid, Modal} from "@mui/material";
import React from "react";
import OrderResultCard from "components/checkout/OrderResultCard";

type OrderResultModalProps = {
    orders: Order[],
    open: boolean,
    onClose: () => void;
}

const OrderResultDialog = ({orders, open, onClose}: OrderResultModalProps) => {
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Order Submission</DialogTitle>
            <Grid container spacing={2}>{orders && orders.map(order => (
                <Grid key={order.id} item>
                    <OrderResultCard order={order}/>
                </Grid>))}
            </Grid>
        </Dialog>
    );
};

export default OrderResultDialog;