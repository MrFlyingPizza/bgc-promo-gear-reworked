import {CartItem} from "types/CartItem";
import {useMutation} from "react-query";
import axios from "axios";
import {useState} from "react";
import {Card, Col, Image, Row} from "react-bootstrap";
import React from "react";
import {Chip, List, TextField, Tooltip} from "@mui/material";
import AvailabilityBadge from "components/shared/AvailabilityBadge";

type CartContentItemProps = {
    initialItem: CartItem
}

const CartContentItem = ({
                             initialItem:
                                 {
                                     variant: {id, image, availability, options},
                                     product: {name},
                                     quantity: initialQuantity
                                 }
                         }: CartContentItemProps) => {

    const [quantity, setQuantity] = useState(initialQuantity);

    const {mutate} = useMutation(updateCartItem => axios.put(`/api/users/me/cart_items/${id}`)
        .then<CartItem>(response => response.data), {
        onSuccess: (data) => {
            setQuantity(data.quantity);
        }
    });

    const imageSize = {width: 300, height: 300};

    return (
        <Card>
            <Card.Body>
                <Row className={"border-left-primary "}>
                    <Col>
                        <AvailabilityBadge availability={availability}>{image &&
                            <Image fluid {...imageSize} src={image.src} alt={image.alt}/>
                            || <Image fluid {...imageSize} src={"https://pbs.twimg.com/media/EROmBrLXUAEf8_c.jpg"}
                                      alt={"placeholder image"}/>}
                        </AvailabilityBadge>
                    </Col>
                    <Col>
                        <Row>
                            <h4>{name}</h4>
                        </Row>{
                        options.length > 0 &&
                        <Row>
                            <List>{options.map(option =>
                                <Tooltip title={option.name}>
                                    <Chip variant={"filled"} color={"primary"} label={option.value}/>
                                </Tooltip>)}
                            </List>
                        </Row>}
                    </Col>
                    <Col sm={2}>
                        <TextField defaultValue={quantity} label="Quantity" type="number"
                                   InputLabelProps={{shrink: true,}}
                                   variant="standard"/>
                    </Col>
                </Row>
            </Card.Body>
        </Card>
    )

}

export default CartContentItem;