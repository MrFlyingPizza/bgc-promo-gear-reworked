import React, {useState} from "react";
import {Card, ListGroup} from "react-bootstrap";
import {FormControl, InputLabel, LinearProgress, MenuItem, Select, SelectChangeEvent} from "@mui/material";
import {useQuery} from "react-query";
import axios from "axios";
import OfficeLocation from "types/OfficeLocation";

type OrderFormProps = {};

const OrderForm = () => {

    const {isLoading, isError, data: officeLocations} = useQuery("locations", () =>
        axios.get("/api/office_locations").then<OfficeLocation[]>(({data}) => data.officeLocations));

    const [selectedValue, setSelectedValue] = useState("");

    function handleChange(event: SelectChangeEvent) {
        setSelectedValue(event.target.value);
    }

    return (
        <Card>
            <Card.Header>Order Form</Card.Header>
            <ListGroup variant={"flush"}>
                <ListGroup.Item>
                    <h6>Which office location will you receive this order at?</h6>{
                    isLoading && <LinearProgress/>
                    || isError && <span>Could not load options.</span>
                    ||
                    <FormControl>
                        <InputLabel id={"office-location-select-label"}>Office Location</InputLabel>
                        <Select labelId={"office-location-select-label"} value={selectedValue}
                                onChange={handleChange}>{officeLocations.map(({name}, index) => (
                            <MenuItem key={index} value={index}>{name}</MenuItem>))}
                        </Select>
                    </FormControl>}
                </ListGroup.Item>
            </ListGroup>
        </Card>
    );
}

export default OrderForm;