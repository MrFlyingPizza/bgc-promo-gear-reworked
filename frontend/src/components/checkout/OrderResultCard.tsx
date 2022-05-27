import React from "react";
import Order from "types/Order";
import {Card} from "react-bootstrap";
import OrderStatusLabel from "components/shared/OrderStatusLabel";

type OrderResultCardProps = {
    order: Order
}

const OrderResultCard = ({order: {id, status}}: OrderResultCardProps) => {
    return (
        <Card>
            <Card.Body>
                <Card.Title>
                    Order #{id}
                    <OrderStatusLabel status={status}/>
                </Card.Title>
            </Card.Body>
        </Card>
    )
};

export default OrderResultCard;