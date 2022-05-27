import React, {ChangeEvent, useCallback, useState} from "react";
import {Card, ListGroup} from "react-bootstrap";
import {
    Box, Button, CircularProgress, Collapse, Dialog, DialogTitle,
    FormControl, Grid, InputLabel,
    LinearProgress, MenuItem,
    Select,
    SelectChangeEvent, TextField
} from "@mui/material";
import {useMutation, useQuery} from "react-query";
import axios from "axios";
import OfficeLocation from "types/OfficeLocation";
import OrderType from "types/OrderType";
import {DatePicker} from '@mui/x-date-pickers/DatePicker';
import Order from "types/Order";
import orderType from "types/OrderType";
import OrderResultDialog from "components/checkout/OrderResultDialog";

interface OrderCreate {
    comments: string,
    locationId: number,
    type: OrderType,
    extraInfo: {
        recipientInfo: string,
        requiredDate: Date
    }
}

const OrderForm = () => {

    const [selectedLocationIndex, setSelectedLocationIndex] = useState("");
    const [selectedOrderType, setSelectedOrderType] = useState("");
    const [selectedRequiredDate, setSelectedRequiredDate] = useState(Date.now());
    const [recipientInfo, setRecipientInfo] = useState("");
    const [comments, setComments] = useState<string>("");

    const handleOrderSubmit = useCallback(() => {
        const submission: OrderCreate = {
            locationId: officeLocations[parseInt(selectedLocationIndex)].id,
            type: selectedOrderType ? OrderType[selectedOrderType as keyof typeof OrderType] : null,
            comments: comments,
            extraInfo: selectedOrderType == OrderType.CLIENT || selectedOrderType == OrderType.EVENT ? {
                recipientInfo: recipientInfo,
                requiredDate: new Date(selectedRequiredDate)
            } : null
        }
        postOrderCreate(submission);
    }, [selectedLocationIndex, orderType, recipientInfo, selectedRequiredDate, selectedOrderType]);

    const {
        isLoading: isOfficeLocationsLoading,
        isError: isOfficeLocationsError,
        data: officeLocations
    } = useQuery("locations", () =>
        axios.get("/api/office_locations").then<OfficeLocation[]>(({data}) => data.officeLocations));

    // todo make use of submission error
    const {
        isLoading: isSubmissionLoading,
        isError: isSubmissionError,
        mutate: postOrderCreate
    } = useMutation((orderCreate: OrderCreate) => {
        return axios.post("/api/orders", orderCreate).then<Order[]>(({data}) => data.orders);
    }, {
        onSuccess: data => {
            setOrderResult(data);
            closeDialog();
        },
        onError: error => console.error(error)
    });

    function handleLocationChange({target: {value}}: SelectChangeEvent) {
        setSelectedLocationIndex(value);
    }

    function handleOrderTypeChange({target: {value}}: SelectChangeEvent) {
        setSelectedOrderType(value);
    }

    function handleRecipientInfoChange({target: {value}}: ChangeEvent<HTMLInputElement>) {
        setRecipientInfo(value);
    }

    function handleCommentsChange({target: {value}}: ChangeEvent<HTMLInputElement>) {
        setComments(value);
    }

    const canContinue = selectedOrderType
        && selectedLocationIndex !== ""
        && (selectedOrderType == OrderType.REGULAR || selectedRequiredDate && recipientInfo);

    //region Agreement Dialog Controls
    interface OrderFormAgreements {
        personal: boolean,
        bigItem: boolean
    }

    const [isAgreementOpen, setIsAgreementOpen] = useState(false);
    const [agreements, setAgreements] = useState<OrderFormAgreements>({bigItem: false, personal: false});

    function openDialog() {
        setIsAgreementOpen(true);
    }

    function closeDialog() {
        setIsAgreementOpen(false);
    }

    const insufficientAgreement = Object.entries(agreements).some(([, isAgreed]) => !isAgreed);

    //endregion

    //region Order Result Modal Controls
    const [orderResult, setOrderResult] = useState<Order[]>();

    function clearOrderResult() {
        setOrderResult(null);
    }

    //endregion

    return (
        <>
            <OrderResultDialog orders={orderResult} open={!!orderResult} onClose={clearOrderResult}/>
            <Dialog open={isAgreementOpen} onClose={closeDialog}>
                <DialogTitle>Order Agreement</DialogTitle>
                <ListGroup>
                    <ListGroup.Item>
                        <h6>Do you agree that this item is for yourself only?</h6>
                        <Button variant={agreements.personal ? "outlined" : "contained"} onClick={() => setAgreements({
                            ...agreements,
                            personal: !agreements.personal
                        })}>Agree</Button>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <h6>Are you aware that you can only request 1 big item every 12 months?</h6>
                        <Button variant={agreements.bigItem ? "outlined" : "contained"} onClick={() => setAgreements({
                            ...agreements,
                            bigItem: !agreements.bigItem
                        })}>Agree</Button>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <Button disabled={insufficientAgreement || isSubmissionLoading} variant={"contained"}
                                onClick={handleOrderSubmit}>
                            Submit Order
                            {
                                isSubmissionLoading && <CircularProgress/>
                            }
                        </Button>
                    </ListGroup.Item>
                </ListGroup>
            </Dialog>
            <Card>
                <Card.Header>Order Form</Card.Header>
                <ListGroup variant={"flush"}>
                    <ListGroup.Item>
                        <h6>Which office location would you like to receive this order at?</h6>{
                        isOfficeLocationsLoading && <LinearProgress/>
                        || isOfficeLocationsError && <span>Could not load options.</span>
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
                                <MenuItem value={OrderType.REGULAR}>Personal</MenuItem>
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
                                   placeholder={"Comments about your order."} onChange={handleCommentsChange}/>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <Button disabled={!canContinue} variant={"contained"} onClick={openDialog}>Continue</Button>
                    </ListGroup.Item>
                </ListGroup>
            </Card>
        </>
    );
}

export default OrderForm;