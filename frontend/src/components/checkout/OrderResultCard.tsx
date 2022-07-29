import React from "react";
import Order from "types/Order";
import {Card} from "react-bootstrap";
import OrderStatusLabel from "components/shared/OrderStatusLabel";
import OrderCardContentItem from "components/orders/OrderCardContentItem";

type OrderResultCardProps = {
    order: Order
}

const OrderResultCard = ({order: {id, status, items}}: OrderResultCardProps) => {
    return (
        <Card>
            <Card.Body>
                <Card.Title>
                    Order #{id} &nbsp;
                    <OrderStatusLabel status={status}/>
                </Card.Title>
                {items.map(item => <OrderCardContentItem key={item.variant.id} orderItem={item}/>)}
            </Card.Body>
        </Card>
    )
};

export default OrderResultCard;