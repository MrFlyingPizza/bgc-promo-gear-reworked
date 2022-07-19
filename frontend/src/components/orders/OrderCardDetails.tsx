import Order from "types/Order";
import {ListGroup} from "react-bootstrap";
import React from "react";

type OrderCardDetailsProps = {
    order: Order
}

const OrderCardDetails = ({order}: OrderCardDetailsProps) => {
    return (
        <ListGroup>
            <ListGroup.Item>
                <h6>Comments</h6>
                <span>{order.submitterComments}</span>
            </ListGroup.Item>
        </ListGroup>
    )
};

export default OrderCardDetails;