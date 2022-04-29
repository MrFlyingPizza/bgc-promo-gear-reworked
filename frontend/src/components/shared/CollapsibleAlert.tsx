import {Alert, AlertColor, Grow, IconButton} from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';
import React, {useState} from "react";

type CollapsibleAlertProps = {
    id: number,
    message: string,
    severity: AlertColor
    onClose: (index: number) => void
}

const CollapsibleAlert = ({id, message, severity, onClose}: CollapsibleAlertProps) => {

    const [open, setOpen] = useState(true);

    function handleClose() {
        setOpen(false);
        onClose(id);
    }

    return (
        <Grow in={open} appear={true}>
            <Alert
                severity={severity}
                action={
                    <IconButton aria-label="close" color="inherit" size="small" onClick={handleClose}>
                        <CloseIcon fontSize="inherit" />
                    </IconButton>
                }
                sx={{ mb: 2 }}
            >
                {message}
            </Alert>
        </Grow>
    );
}

export default CollapsibleAlert;
