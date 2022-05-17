import React from "react";
import Order from "types/Order";
import {Card} from "react-bootstrap";

type OrderCardProps = {
    order: Order
}

const OrderCard = ({order}: OrderCardProps) => {
    return (
        <Card>
            <Card.Body>
                <Card.Title>Order #{order.id}</Card.Title>
                <Card.Subtitle>{`Date created: ${order.createdDate}`}</Card.Subtitle>
                <Card.Subtitle>{`Submitter: ${order.submitter}`}</Card.Subtitle>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas quis mattis nisi, id dignissim neque. Morbi justo neque, rhoncus a elit et, posuere malesuada metus. In finibus est tristique risus rhoncus hendrerit. Donec vestibulum id elit non lobortis. Vestibulum at ipsum id sapien pellentesque tincidunt. Morbi tincidunt, felis quis hendrerit pretium, orci ante tincidunt ipsum, nec tempor sapien felis vestibulum turpis. Praesent nibh urna, pharetra id pharetra ac, fermentum in nisi. Nulla rhoncus vitae ante ut facilisis. Etiam suscipit molestie leo, quis porta ligula lobortis ac. Curabitur posuere venenatis neque vitae tempus. Nunc sed ipsum eu dolor bibendum egestas. Integer vulputate sem vel mauris semper pretium. Integer pharetra, tellus eu iaculis efficitur, ex neque gravida mi, vel pellentesque nulla velit faucibus felis. Sed sed eros ac risus tempus pellentesque vel a enim. Donec pretium, felis a sagittis sodales, est quam consectetur risus, non fringilla ante sem non libero.
            </Card.Body>
        </Card>
    );
};

export default OrderCard;