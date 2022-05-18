import {Chip} from "@mui/material";
import * as React from "react";
import OrderStatus from "types/OrderStatus";

export type OrderStatusLabelProps = {
    status: OrderStatus
    otherText?: string
};

type Colors = "info" | "warning" | "error" | "default" | "primary" | "secondary" | "success";

const OrderStatusLabel = ({status, otherText}: OrderStatusLabelProps) => {

    const OrderStatusChip = ({label, color}: { label: string, color?: Colors }) => {
        return (
            <Chip label={label} variant={"filled"} size={"small"} color={color}/>
        )
    }

    return (
        status == OrderStatus.WAIT_LIST && <OrderStatusChip label={"Wait-Listed"} color={"warning"}/>
        || status == OrderStatus.SUBMITTED && <OrderStatusChip label={"Submitted"} color={"success"}/>
        || status == OrderStatus.PROCESSING && <OrderStatusChip label={"In Progress"} color={"info"}/>
        || status == OrderStatus.CANCELLED && <OrderStatusChip label={"Cancelled"} color={"error"}/>
        || status == OrderStatus.COMPLETED && <OrderStatusChip label={"Completed"}/>
        || <OrderStatusChip label={otherText || "No Status"} color={"info"}/>
    )
}

export default OrderStatusLabel;