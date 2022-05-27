import Order from "types/Order";
import {ListGroup} from "react-bootstrap";
import React from "react";
import {format} from "date-fns";

type OrderCardDetailsProps = {
    order: Order
}

const OrderCardDetails = ({order}: OrderCardDetailsProps) => {
    return (
        <ListGroup>
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
                <span>{order.location?.name}</span>
            </ListGroup.Item>
            <ListGroup.Item>
                <h6>Created Date</h6>
                <span>{order.createdDate && format(new Date(order.createdDate), "MMM d, yyyy")}</span>
            </ListGroup.Item>
            <ListGroup.Item>
                <h6>Completed Date</h6>
                <span>{order.completedDate && format(new Date(order.completedDate), "MMM d, yyyy")}</span>
            </ListGroup.Item>
            <ListGroup.Item>
                <h6>Comments</h6>
                <span>{order.submitterComments}</span>
            </ListGroup.Item>
        </ListGroup>
    )
};

export default OrderCardDetails;