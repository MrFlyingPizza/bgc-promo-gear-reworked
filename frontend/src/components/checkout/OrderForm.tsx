import React, {ChangeEvent, useState} from "react";
import {Card, ListGroup} from "react-bootstrap";
import {
    Box, Button, Collapse,
    FormControl, Grid, Grow,
    InputLabel,
    LinearProgress,
    MenuItem,
    Select,
    SelectChangeEvent, Stack,
    TextField
} from "@mui/material";
import {useQuery} from "react-query";
import axios from "axios";
import OfficeLocation from "types/OfficeLocation";
import OrderType from "types/OrderType";
import {DatePicker} from '@mui/x-date-pickers/DatePicker';

type OrderFormProps = {};

const OrderForm = () => {

    const {isLoading, isError, data: officeLocations} = useQuery("locations", () =>
        axios.get("/api/office_locations").then<OfficeLocation[]>(({data}) => data.officeLocations));

    const [selectedLocationIndex, setSelectedLocationIndex] = useState("");
    const [selectedOrderType, setSelectedOrderType] = useState("");
    const [selectedRequiredDate, setSelectedRequiredDate] = useState(Date.now());
    const [recipientInfo, setRecipientInfo] = useState("");

    function handleLocationChange({target: {value}}: SelectChangeEvent) {
        setSelectedLocationIndex(value);
    }

    function handleOrderTypeChange({target: {value}}: SelectChangeEvent) {
        setSelectedOrderType(value);
    }

    function handleRecipientInfoChange({target: {value}}: ChangeEvent<HTMLInputElement>) {
        setRecipientInfo(value);
    }

    const canContinue = selectedOrderType
        && selectedLocationIndex
        && (selectedOrderType == OrderType.REGULAR || selectedRequiredDate && recipientInfo);

    return (
        <Card>
            <Card.Header>Order Form</Card.Header>
            <ListGroup variant={"flush"}>
                <ListGroup.Item>
                    <h6>Which office location would you like to receive this order at?</h6>{
                    isLoading && <LinearProgress/>
                    || isError && <span>Could not load options.</span>
                    ||
                    <FormControl fullWidth>
                        <InputLabel id={"office-location-select-label"}>Office Location *</InputLabel>
                        <Select labelId={"office-location-select-label"} value={selectedLocationIndex}
                                label={"Office Location"} onChange={handleLocationChange}>
                            <MenuItem value={""}>None</MenuItem>{officeLocations.map(({name}, index) => (
                            <MenuItem key={index} value={index}>{name}</MenuItem>))}
                        </Select>
                    </FormControl>}
                </ListGroup.Item>
                <ListGroup.Item>
                    <h6>What type of order would you like to submit?</h6>
                    <FormControl fullWidth>
                        <InputLabel id={"order-type-select-label"}>Order Type *</InputLabel>
                        <Select labelId={"order-type-select-label"} value={selectedOrderType}
                                label={"Order Type"} onChange={handleOrderTypeChange}>
                            <MenuItem value={""}>None</MenuItem>
                            <MenuItem value={OrderType.REGULAR}>Regular</MenuItem>
                            <MenuItem value={OrderType.CLIENT}>Client</MenuItem>
                            <MenuItem value={OrderType.EVENT}>Event</MenuItem>
                        </Select>
                    </FormControl>
                    <Collapse in={selectedOrderType == OrderType.CLIENT || selectedOrderType == OrderType.EVENT}>
                        <h6>Please provide additional client/event information.</h6>
                        <Box component={"form"}>
                            <Grid container spacing={2}>
                                <Grid item xs={"auto"}>
                                    <TextField label={"Recipient Info *"} onChange={handleRecipientInfoChange}/>
                                </Grid>
                                <Grid item xs={"auto"}>
                                    <DatePicker
                                        label="Date Required By *"
                                        value={selectedRequiredDate}
                                        minDate={Date.now()}
                                        onChange={(newValue) => {
                                            setSelectedRequiredDate(newValue);
                                        }}
                                        renderInput={(params) => <TextField {...params} />}
                                    />
                                </Grid>
                            </Grid>
                        </Box>
                    </Collapse>
                </ListGroup.Item>
                <ListGroup.Item>
                    <h6>Order Comments</h6>
                    <TextField fullWidth label={"Comments"} multiline rows={4}
                               placeholder={"Comments about your order."}/>
                </ListGroup.Item>
                <ListGroup.Item>
                    <Button disabled={!canContinue} variant={"contained"}>Continue</Button>
                </ListGroup.Item>
            </ListGroup>
        </Card>
    );
}

export default OrderForm;