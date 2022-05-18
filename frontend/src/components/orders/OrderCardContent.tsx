import Order from "types/Order";
import React from "react";
import OrderCardContentItem from "components/orders/OrderCardContentItem";

type OrderCardContentProps = {
    order: Order
}

const OrderCardContent = ({order: {items}}: OrderCardContentProps) => {
    return (
        <>
            {items.map(item => <OrderCardContentItem key={item.variant.id} orderItem={item}/>)}
        </>
    )
};

export default OrderCardContent;