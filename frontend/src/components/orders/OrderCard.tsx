import React from "react";
import Order from "types/Order";
import {Card, ListGroup} from "react-bootstrap";
import {Divider} from "@mui/material";

type OrderCardProps = {
    order: Order
}

const OrderCard = ({order}: OrderCardProps) => {
    return (
        <Card>
            <Card.Header>
                Order #{order.id}
            </Card.Header>
            <Card.Body>
                <ListGroup horizontal>
                    <ListGroup.Item>
                        <h6>Submitter</h6>
                        <span>{order.submitter}</span>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <h6>Fulfiller</h6>
                        <span>{order.fulfiller}</span>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <h6>Office Location</h6>
                        <span>{order.officeLocation?.name}</span>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <h6>Created Date</h6>
                        <span>{order.createdDate?.toDateString()}</span>
                    </ListGroup.Item>
                </ListGroup>
                <Divider/>
                {order.submitterComments}
            </Card.Body>
        </Card>
    );
};

export default OrderCard;