import CartItem from "types/CartItem";
import {useMutation} from "react-query";
import axios from "axios";
import {useRef, useState} from "react";
import {Card, Col, Container, Image, Row, Stack} from "react-bootstrap";
import React from "react";
import {Alert, Button, Chip, CircularProgress, Fade, TextField, Tooltip} from "@mui/material";
import AvailabilityBadge from "components/shared/AvailabilityBadge";
import placeholderSrc from "components/shared/PlaceholderImage";
import CartItemUpdate from "types/CartItemUpdate";

type CartContentItemProps = {
    initialItem: CartItem,
    onRemove: () => void,
    onUpdate: () => void
}

const CartContentItem = (
    {
        initialItem:
            {
                variant: {id, image, availability, options},
                product: {name},
                quantity: initialQuantity
            },
        onRemove,
        onUpdate
    }: CartContentItemProps
) => {

    const [quantity, setQuantity] = useState(initialQuantity);
    const acceptedQuantity = useRef(initialQuantity);

    const {
        mutate: updateItem,
        isLoading: isUpdating,
        isError: isUpdateError
    } = useMutation((cartItemUpdate: CartItemUpdate) =>
        axios.put(`/api/users/me/cart_items/${id}`, cartItemUpdate).then<CartItem>(response => response.data), {
        onSuccess: (data) => {
            acceptedQuantity.current = data.quantity;
            onUpdate();
        },
        onError: () => {
            setQuantity(acceptedQuantity.current);
        }
    });

    const {mutate: deleteItem, isLoading: isDeleting, isError: isDeleteError} = useMutation(() =>
        axios.delete(`/api/users/me/cart_items/${id}`), {
        onSuccess: () => onRemove()
    })

    return (
        <Fade in={true} appear={true}>
            <Card>{(isUpdating || isDeleting) &&
            <div className={"position-absolute top-50 start-50 translate-middle"}>
                <CircularProgress/>
            </div>}
                <Card.Body>{
                    <Row>
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
                                <h5>{name}</h5>
                            </Row>{
                            options.length > 0 &&
                            <Row>
                                <Stack className={"flex-wrap"} direction={"horizontal"} gap={1}>{options.map(option =>
                                    <Tooltip key={option.valueId} title={option.name}>
                                        <Chip variant={"filled"} color={"primary"} label={option.value}/>
                                    </Tooltip>)}
                                </Stack>
                            </Row>}
                        </Col>
                        <Col md={3}>
                            <Row className={"mt-2"}>
                                <Col sm={6} md={12}>
                                    <TextField disabled={isDeleting || isUpdating} label="Quantity" value={quantity}
                                               type="number" variant="standard" error={quantity < 1}
                                               helperText={quantity < 1 && "Must be at least 1."} inputProps={{min: 1}}
                                               onChange={event => setQuantity(parseInt(event.target.value))}
                                               onBlur={() => quantity > 0 && updateItem({quantity: quantity})}/>
                                </Col>
                                <Col sm={6} md={12}>
                                    <Button disabled={isDeleting || isUpdating}
                                            onClick={() => deleteItem()}>Remove</Button>
                                </Col>
                            </Row>
                        </Col>
                    </Row>}
                    {isUpdateError && <Alert severity={"error"}>Failed to change quantity!</Alert>}
                    {isDeleteError && <Alert severity={"error"}>Failed to delete cart item!</Alert>}
                </Card.Body>
            </Card>
        </Fade>
    )

}

export default CartContentItem;