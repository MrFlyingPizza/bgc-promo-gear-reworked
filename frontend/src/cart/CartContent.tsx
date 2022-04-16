import {ICartItem} from "./ICartItem";
import {
    Box,
    Button,
    Container,
    Grid,
    Input,
    List,
    ListItem,
    ListItemText,
    Paper,
    Stack,
    Typography
} from "@mui/material";
import * as React from "react";
import {useState} from "react";

function CartContent(props: {items: ICartItem[]}) {

    const [items, setItems] = useState(props.items);

    const cartItems = items.map(item => {
        return (
            <Paper elevation={2} key={item.variant.id}>
                <Container>
                    <Grid container spacing={2}>
                            <Grid item xs={4}> {
                                item.variant.image ?
                                <Box component={"img"}
                                     alt={item.variant.image.alt}
                                     src={item.variant.image.src}
                                     maxHeight={"100px"}
                                     maxWidth={"100px"}
                                />
                                    : <Box component={"img"}
                                           alt={"sad cat"}
                                           src={"https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg"}
                                           maxHeight={"100px"}
                                           maxWidth={"100px"}
                                    />
                            }
                            </Grid>
                            <Grid item xs={4}>
                                <List >
                                    {
                                        item.variant.options.map(option => {
                                            return (
                                                <ListItemText key={option.optionId}
                                                              primary={<Typography >{option.name}</Typography>}
                                                              secondary={<Typography>{option.value}</Typography>}
                                                />
                                            )
                                        })
                                    }
                                </List>
                            </Grid>
                            <Grid item xs={4}>
                                <List>
                                    <ListItem>
                                        <Input value={item.quantity}/>
                                    </ListItem>
                                    <ListItem>
                                        <Button>
                                            Remove
                                        </Button>
                                    </ListItem>
                                </List>
                            </Grid>
                        </Grid>
                </Container>
            </Paper>
        );
    });

    return (
        <Stack spacing={2}>
            {cartItems}
        </Stack>
    )
}

export default CartContent;