import ProductVariantAvailability from "types/ProductVariantAvailability";
import {Chip} from "@mui/material";
import * as React from "react";

export type AvailabilityLabelProps = {
    availability: ProductVariantAvailability,
    otherText?: string
};

type Colors = "info" | "warning" | "error" | "default" | "primary" | "secondary" | "success";

const AvailabilityLabel = ({availability, otherText}: AvailabilityLabelProps) => {

    const AvailabilityChip = ({label, color}: { label: string, color: Colors }) => {
        return (
            <Chip label={label} variant={"filled"} size={"small"} color={color}/>
        )
    }

    return (
        availability == ProductVariantAvailability.AVAILABLE
        && <AvailabilityChip label={"Available"} color={"success"}/>
        || availability == ProductVariantAvailability.WAIT_LIST
        && <AvailabilityChip label={"Wait-List"} color={"warning"}/>
        || availability == ProductVariantAvailability.UNAVAILABLE
        && <AvailabilityChip label={"Unavailable"} color={"error"}/>
        || <AvailabilityChip label={otherText || "No Availability Status"} color={"info"}/>
    )
}

export default AvailabilityLabel;