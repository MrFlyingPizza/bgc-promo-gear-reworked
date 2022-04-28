import {Checkbox} from "@mui/material";
import React, {ChangeEvent} from "react";
import {useState} from "react";
import {OptionValue} from "types/Product";

export type OptionCheckboxControl = {
    enable: () => void,
    disable: () => void
}

export type OptionCheckboxProps = {
    option: OptionValue,
    onChange: (option: OptionValue, checked: boolean) => void,
    delegation: (option: OptionValue, control: OptionCheckboxControl) => void
}

const OptionCheckbox = ({option, onChange, delegation}: OptionCheckboxProps) => {

    const [disabled, setDisabled] = useState(false);
    const [checked, setChecked] = useState(false);

    delegation(option, {
        enable: () => setDisabled(false),
        disable: () => setDisabled(true)
    });

    function handleChange(event: ChangeEvent<HTMLInputElement>) {
        const checked = event.target.checked;
        setChecked(checked);
        onChange(option, checked);
    }

    return (
        <Checkbox checked={checked} disabled={disabled} onChange={handleChange}/>
    )
};

export default OptionCheckbox;