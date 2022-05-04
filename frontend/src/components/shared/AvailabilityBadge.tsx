import ProductVariantAvailability from "types/ProductVariantAvailability";
import {Badge} from "@mui/material";
import * as React from "react";

export type AvailabilityLabelProps = {
    children: React.ReactNode
    availability: ProductVariantAvailability,
    otherText?: string
};

const AvailabilityBadge = ({children, availability, otherText}: AvailabilityLabelProps) => {

    return (
        availability == ProductVariantAvailability.AVAILABLE
        && <Badge badgeContent={"Available"} color={"success"}>{children}</Badge>
        || availability == ProductVariantAvailability.WAIT_LIST
        && <Badge badgeContent={"Wait-List"} color={"warning"}>{children}</Badge>
        || availability == ProductVariantAvailability.UNAVAILABLE
        && <Badge badgeContent={"Unavailable"} color={"error"}>{children}</Badge>
        || <Badge badgeContent={otherText || "No Availability Status"} color={"info"}>{children}</Badge>
    )
}

export default AvailabilityBadge;