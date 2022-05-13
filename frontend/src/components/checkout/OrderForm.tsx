import React, {useState} from "react";
import {Card, ListGroup} from "react-bootstrap";
import {
    Box,
    FormControl, Grow,
    InputLabel,
    LinearProgress,
    MenuItem,
    Select,
    SelectChangeEvent,
    TextField
} from "@mui/material";
import {useQuery} from "react-query";
import axios from "axios";
import OfficeLocation from "types/OfficeLocation";
import OrderType from "types/OrderType";

type OrderFormProps = {};

const OrderForm = () => {

    const {isLoading, isError, data: officeLocations} = useQuery("locations", () =>
        axios.get("/api/office_locations").then<OfficeLocation[]>(({data}) => data.officeLocations));

    const [selectedLocationIndex, setSelectedLocationIndex] = useState("0");
    const [selectedOrderType, setSelectedOrderType] = useState("");

    function handleLocationChange(event: SelectChangeEvent) {
        setSelectedLocationIndex(event.target.value);
    }

    function handleOrderTypeChange(event: SelectChangeEvent) {
        setSelectedOrderType(event.target.value);
    }

    return (
        <Card>
            <Card.Header>Order Form</Card.Header>
            <ListGroup variant={"flush"}>
                <ListGroup.Item>
                    <h6>Which office location would you like to receive this order at?</h6>{
                    isLoading && <LinearProgress/>
                    || isError && <span>Could not load options.</span>
                    ||
                    <FormControl sx={{minWidth: 300}}>
                        <InputLabel id={"office-location-select-label"}>Office Location</InputLabel>
                        <Select autoWidth labelId={"office-location-select-label"} value={selectedLocationIndex}
                                label={"Office Location"} onChange={handleLocationChange}>
                            <MenuItem value={""}>None</MenuItem>{officeLocations.map(({name}, index) => (
                            <MenuItem key={index} value={index}>{name}</MenuItem>))}
                        </Select>
                    </FormControl>}
                </ListGroup.Item>
                <ListGroup.Item>
                    <h6>What type of order would you like to submit?</h6>
                    <FormControl sx={{minWidth: 300}}>
                        <InputLabel id={"order-type-select-label"}>Order Type</InputLabel>
                        <Select autoWidth labelId={"order-type-select-label"} value={selectedOrderType}
                                label={"Order Type"} onChange={handleOrderTypeChange}>
                            <MenuItem value={""}>None</MenuItem>
                            <MenuItem value={OrderType.REGULAR}>Regular</MenuItem>
                            <MenuItem value={OrderType.CLIENT}>Client</MenuItem>
                            <MenuItem value={OrderType.EVENT}>Event</MenuItem>
                        </Select>
                    </FormControl>
                </ListGroup.Item>{(selectedOrderType == OrderType.CLIENT || selectedOrderType == OrderType.EVENT) && (
                    <Grow in appear>
                        <ListGroup.Item>
                            <h6>Please provide {selectedOrderType} order extra information.</h6>
                            <Box component={"form"}>
                                <TextField/>
                                <TextField/>
                            </Box>
                        </ListGroup.Item>
                    </Grow>)}
            </ListGroup>
        </Card>
    );
}

export default OrderForm;