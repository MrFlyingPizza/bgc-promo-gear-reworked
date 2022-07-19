import React, {useState} from "react";
import Order from "types/Order";
import {Card} from "react-bootstrap";
import {Button, Grid, ListItemText, Collapse} from "@mui/material";
import OrderCardDetails from "components/orders/OrderCardDetails";
import OrderCardContent from "components/orders/OrderCardContent";
import OrderStatusLabel from "components/shared/OrderStatusLabel";
import {format} from "date-fns";

type OrderCardProps = {
    order: Order
}

const OrderCard = ({order}: OrderCardProps) => {

    const [isOpen, setOpen] = useState(true);

    function toggleOpen() {
        setOpen(!isOpen)
    }

    return (
        <Card>
            <Card.Header>
                <Grid container spacing={2} justifyContent={"space-evenly"}>
                    <Grid item>
                        <ListItemText primary={"Order #"} secondary={order.id}/>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Status"}/>
                        <div><OrderStatusLabel status={order.status}/></div>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Placed On"}
                                      secondary={order.createdDate ? format(new Date(order.createdDate), "MMM d, yyyy") : "Unknown"}/>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Submitter"} secondary={order.submitter ?? "Unknown"}/>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Fulfiller"} secondary={order.fulfiller ?? "Unknown"}/>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Office"} secondary={order.location?.name ?? "Unknown"}/>
                    </Grid>
                    <Grid item>
                        <ListItemText primary={"Completed On"}
                                      secondary={order.completedDate ? format(new Date(order.completedDate), "MMM d, yyyy") : "Unknown"}/>
                    </Grid>
                    <Grid item xs={"auto"}>
                        <Button variant={"text"} onClick={toggleOpen}>{isOpen ? "Hide" : "Show"}</Button>
                    </Grid>
                </Grid>
            </Card.Header>
            <Collapse in={isOpen}>
                <Card.Body>
                    <Grid container spacing={4}>
                        <Grid item xs={12} md={4}>
                            <OrderCardDetails order={order}/>
                        </Grid>
                        <Grid item xs={12} md={8}>
                            <OrderCardContent order={order}/>
                        </Grid>
                    </Grid>
                </Card.Body>
            </Collapse>
        </Card>
    );
};

export default OrderCard;