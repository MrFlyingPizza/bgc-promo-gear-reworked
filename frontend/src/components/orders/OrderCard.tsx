import React from "react";
import Order from "types/Order";
import {Accordion, Card} from "react-bootstrap";
import {Grid} from "@mui/material";
import OrderCardDetails from "components/orders/OrderCardDetails";
import OrderCardContent from "components/orders/OrderCardContent";
import OrderStatusLabel from "components/shared/OrderStatusLabel";

type OrderCardProps = {
    order: Order
}

const OrderCard = ({order}: OrderCardProps) => {
    return (
        <Card>
            <Card.Header>
                Order #{order.id}
                <span className={"ms-2"}>
                    <OrderStatusLabel status={order.status}/>
                </span>
            </Card.Header>
            <Card.Body>
                <Grid container spacing={2}>
                    <Grid item xs={12} md={4}>
                        <Accordion defaultActiveKey={"0"}>
                            <Accordion.Item eventKey={"0"}>
                                <Accordion.Header>Order Details</Accordion.Header>
                                <Accordion.Body>
                                    <OrderCardDetails order={order}/>
                                </Accordion.Body>
                            </Accordion.Item>
                        </Accordion>
                    </Grid>
                    <Grid item xs={12} md={8}>
                        <Accordion defaultActiveKey={"0"}>
                            <Accordion.Item eventKey={"0"}>
                                <Accordion.Header>Order Items</Accordion.Header>
                                <Accordion.Body>
                                    <OrderCardContent order={order}/>
                                </Accordion.Body>
                            </Accordion.Item>
                        </Accordion>
                    </Grid>
                </Grid>
            </Card.Body>
        </Card>
    );
};

export default OrderCard;