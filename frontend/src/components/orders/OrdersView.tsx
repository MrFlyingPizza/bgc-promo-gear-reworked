import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import React from "react";
import {Container, Grid, LinearProgress} from "@mui/material";
import {useQuery} from "@tanstack/react-query";
import axios from "axios";
import Order from "types/Order";
import OrderCard from "components/orders/OrderCard";

const OrdersView = () => {

    const {
        isLoading,
        isError,
        data: orders
    } = useQuery(["orders"], () => axios.get("/api/orders").then<Order[]>(({data}) => data.orders));

    return (
        <>
            <BGCPromoGearHeader/>
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <Grid spacing={2} container>
                    <Grid item xs={12}>
                        <h3>Your Orders</h3>
                    </Grid>
                    {
                        isLoading && <Grid item xs={12}><LinearProgress/></Grid>
                        || isError && <Grid item xs={12}>Failed to load orders.</Grid>
                        || orders.length < 1 && "No order to show."
                        || orders.map(order => (
                            <Grid item key={order.id} xs={12}>
                                <OrderCard order={order}/>
                            </Grid>
                        ))
                    }
                </Grid>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
};

export default OrdersView;