import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import React from "react";
import {Container, Grid, LinearProgress} from "@mui/material";
import {useQuery} from "react-query";
import axios from "axios";
import Order from "types/Order";
import OrderCard from "components/orders/OrderCard";

const OrdersView = () => {

    const {
        isLoading,
        isError,
        data: orders
    } = useQuery("orders", () => axios.get("/api/orders").then<Order[]>(({data}) => data.orders));

    return (
        <>
            <BGCPromoGearHeader/>
            <Container>
                <Grid container className={"mt-5 mb-5 min-vh-100"}>{
                    isLoading && <Grid item xs={12}><LinearProgress/></Grid>
                    || isError && <Grid item xs={12}>Failed to load orders.</Grid>
                    || orders.length < 1 && "Could not find any order."
                    || orders.map(order => (
                        <Grid item xs={12}>
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