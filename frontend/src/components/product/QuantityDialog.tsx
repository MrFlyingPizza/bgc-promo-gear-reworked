import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@mui/material";
import React, {useState} from "react";

type QuantityDialogProps = {
    open: boolean,
    onClose: () => void,
    onConfirm: (quantity: number) => void
};

const QuantityDialog = ({open, onClose, onConfirm}: QuantityDialogProps) => {

    const [quantity, setQuantity] = useState(1);

    function handleConfirmClick() {
        onConfirm(quantity);
        onClose();
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>How many would you like?</DialogTitle>
            <DialogContent>
                <TextField label={"Quantity"} type={"number"} InputLabelProps={{shrink: true}} variant={"standard"}
                           error={quantity < 1} helperText={quantity < 1 && "Must be at least 1."}
                           InputProps={{inputProps: {min: 1}}}
                           value={quantity} onChange={(e) => setQuantity(parseInt(e.target.value))}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Close</Button>
                <Button variant={"contained"} onClick={handleConfirmClick} disabled={quantity < 1}>Confirm</Button>
            </DialogActions>
        </Dialog>
    )
};

export default QuantityDialog;