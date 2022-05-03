import {Alert, AlertColor, Snackbar} from "@mui/material";
import React, {ReactNode} from "react";

export type AlertContent = {
    severity: AlertColor,
    message: ReactNode,
    autoHideDuration: number
}

type AlertSnackbarProps = {
    open: boolean,
    alert: AlertContent,
    onClose: () => void
}

const AlertSnackbar = ({open, onClose, alert}: AlertSnackbarProps) => {

    return (
        <Snackbar open={open} autoHideDuration={alert?.autoHideDuration} onClose={onClose}>
            {alert && <Alert severity={alert.severity} onClose={onClose}>{alert.message}</Alert>}
        </Snackbar>
    );
};

export default AlertSnackbar;