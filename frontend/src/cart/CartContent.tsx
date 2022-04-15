import {ICartItem} from "./ICartItem";
import {Button, Grid, Input, List, ListItem, Paper, Stack, styled} from "@mui/material";
import * as React from "react";
import {useState} from "react";

function CartContent(props: {items: ICartItem[]}) {

    const [items, setItems] = useState(props.items);

    const cartItems = items.map(item => {
        return (
            <Paper elevation={2} key={item.variant.id}>
                <Grid container spacing={2}>
                    <Grid item xs={4}> {
                        item.variant.image ?
                        <img alt={item.variant.image.alt} src={item.variant.image.src}/>
                            : <img alt={"sad cat"} src={"https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg"}/>
                    }
                    </Grid>
                    <Grid item xs={4}>
                        <List>
                            {
                                item.variant.options.map(option => {
                                    return (
                                        <ListItem>
                                            {`${option.name}: ${option.value}`}
                                        </ListItem>
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
            </Paper>
        );
    });

    return (
        <Stack>
            {cartItems}
        </Stack>
    )
}

export default CartContent;