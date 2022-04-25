import * as React from "react";
import {Badge, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup, Tooltip} from "@mui/material";
import {ChangeEvent, ReactNode, useState} from "react";
import {Image} from "react-bootstrap";
import {blue, grey} from "@mui/material/colors";

function ProductCardOptions(props: {
    initialValue: string,
    values: {
        value: string,
        src: string,
        tooltip: ReactNode
    }[], onChange: (value: string) => void
}) {

    const onChange = props.onChange;
    const [values] = useState(props.values);

    const [selectedValue, setSelectedValue] = useState<string>(props?.initialValue || props.values[0].value);

    const options = values.map(item => {

        const src = item.src || "https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg";

        const icon = (
            <Image style={{height: 24, width: 24, objectFit: "contain"}} roundedCircle src={src}/>
        );

        const checkIcon = (
            <Badge variant={"dot"} color={"primary"}>{icon}</Badge>
        );

        const radio = <FormControlLabel key={item.value} control={<Radio icon={icon} checkedIcon={checkIcon}/>} label={null} value={item.value}/>;

        return item.tooltip && <Tooltip key={item.value} title={item.tooltip}>{radio}</Tooltip> || radio;
    });

    const handleChange = (event: ChangeEvent<HTMLInputElement>, value: string) => {
        setSelectedValue(value);
        onChange(value);
    }

    return (
        <FormControl>
            <FormLabel id={"product-card-variant-button-group"}>Variants</FormLabel>
            <RadioGroup
                row
                aria-labelledby={"product-card-variant-button-group"}
                name={"variant-button-group"}
                value={selectedValue}
                onChange={handleChange}
            >{options}</RadioGroup>
        </FormControl>
    )

}

export default ProductCardOptions;