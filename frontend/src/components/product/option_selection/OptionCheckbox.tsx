import {Checkbox, Chip, FormControlLabel} from "@mui/material";
import React from "react";

export type OptionCheckboxProps = {
    value: number,
    label: string,
    checked: boolean,
    disabled: boolean,
    onChange: (value: number, checked: boolean) => void
}

const OptionCheckbox = ({value, label, checked, disabled, onChange}: OptionCheckboxProps) => (
    <FormControlLabel
        control={<Checkbox checked={checked} disabled={disabled} onChange={(e) => onChange(value, e.target.checked)}/>}
        label={<span className={"user-select-none"}>{label}</span>}/>
);

export default OptionCheckbox;