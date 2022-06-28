import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import React from "react";
import {Container, Grid, LinearProgress} from "@mui/material";
import {useQuery} from "react-query";
import axios from "axios";
import Order from "types/Order";
import OrderCard from "components/orders/OrderCard";

const AdminOrders = () => {

    const {
        isLoading,
        isError,
        data: orders
    } = useQuery("orders", () => axios.get("/api/orders").then<Order[]>(({data}) => data.orders));

    return (
        <>


        </>
    );
};

export default AdminOrders;