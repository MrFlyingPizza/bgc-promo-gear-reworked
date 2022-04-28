import {Checkbox, FormControlLabel} from "@mui/material";
import React from "react";
import {OptionValue} from "types/Product";

export type OptionCheckboxProps = {
    option: OptionValue,
    checked: boolean,
    disabled: boolean,
    onChange: (option: OptionValue, checked: boolean) => void
}

const OptionCheckbox = ({option, checked, disabled, onChange}: OptionCheckboxProps) => (
    <FormControlLabel
        control={<Checkbox checked={checked} disabled={disabled} onChange={(e) => onChange(option, e.target.checked)}/>}
        label={<span className={"user-select-none"}>{option.value}</span>}/>
);

export default OptionCheckbox;