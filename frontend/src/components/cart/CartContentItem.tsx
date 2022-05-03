import {CartItem} from "types/CartItem";
import {useMutation} from "react-query";
import axios from "axios";
import {useState} from "react";
import {Card, Col, Container, Image, Row, Stack} from "react-bootstrap";
import React from "react";
import {Button, Chip, List, TextField, Tooltip} from "@mui/material";
import AvailabilityBadge from "components/shared/AvailabilityBadge";
import placeholderSrc from "components/shared/PlaceholderImage";

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

    return (
        <Card>
            <Card.Body>
                <Row className={"border-left-primary"}>
                    <Col md={3}>
                        <Row>
                            <Container fluid>
                                <AvailabilityBadge availability={availability}>{image &&
                                <Image className={"w-100 h-auto img-fluid"} src={image.src}
                                       alt={image.alt}/>
                                || <Image className={"w-100 h-auto img-fluid"} src={placeholderSrc()}
                                          alt={"placeholder image"}/>}
                                </AvailabilityBadge>
                            </Container>
                        </Row>
                    </Col>
                    <Col md={6}>
                        <Row className={"mt-2"}>
                            <h4>{name}</h4>
                        </Row>{
                        options.length > 0 &&
                        <Row>
                            <Stack className={"flex-wrap"} direction={"horizontal"} gap={1}>{options.map(option =>
                                <Tooltip title={option.name}>
                                    <Chip variant={"filled"} color={"primary"} label={option.value}/>
                                </Tooltip>)}
                            </Stack>
                        </Row>}
                    </Col>
                    <Col md={3}>
                        <Row className={"mt-2"}>
                            <Col sm={6} md={12}>
                                <TextField defaultValue={quantity} label="Quantity" type="number" variant="standard"/>
                            </Col>
                            <Col sm={6} md={12}>
                                <Button>Remove</Button>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Card.Body>
        </Card>
    )

}

export default CartContentItem;