import React from "react";
import {Card} from "react-bootstrap";
import {OrderItem} from "types/Order";
import {Chip, Grid, Tooltip} from "@mui/material";
import placeholderSrc from "components/shared/PlaceholderImage";

type OrderCardContentItemProps = {
    orderItem: OrderItem
};

const OrderCardContentItem = ({orderItem: {variant, product, quantity}}: OrderCardContentItemProps) => {
    return (
        <Card>
            <Card.Body>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={4}>
                        {
                            variant.image &&
                            <Card.Img src={variant.image.src} alt={variant.image.alt}/>
                            || <Card.Img src={placeholderSrc()} alt={"Placeholder image."}/>
                        }
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <Card.Title>
                            {product.name}
                            <b className={"font-monospace"}> × {quantity}</b>
                        </Card.Title>{variant.options.map(({name, value, valueId}) => (
                        <Tooltip key={valueId} title={name}>
                            <Chip label={value}/>
                        </Tooltip>))}
                    </Grid>
                </Grid>
            </Card.Body>
        </Card>
    )
};

export default OrderCardContentItem;