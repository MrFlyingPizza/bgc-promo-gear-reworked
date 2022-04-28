import {ProductVariant} from "types/Product";
import {Chip, List, ListItem, Radio, Tooltip} from "@mui/material";
import * as React from "react";
import {Image} from "react-bootstrap";
import CheckIcon from "@mui/icons-material/Check";

export type VariantOptionRadioProps = {
    variant: ProductVariant,
    checked: boolean,
    onClick: (variant: ProductVariant) => void
}

const VariantOptionRadio = ({variant, checked, onClick}: VariantOptionRadioProps) => {

    const tooltip = (
        variant.options.length > 0 &&
        <List>{variant.options.map(option => (
            <ListItem key={option.optionId}>
                <Chip color={"primary"} label={option.value}/>
            </ListItem>))}
        </List>
    );

    const src = variant.image?.src || "https://i.pinimg.com/originals/3e/84/09/3e8409dcdd012b4bcda84a710f2d1052.jpg";
    const icon = <Image style={{height: 24, width: 24, objectFit: "cover"}} roundedCircle src={src}/>;
    const checkedIcon = <CheckIcon style={{height: 24, width: 24}}/>;
    const radio = <Radio checked={checked} key={variant.id} icon={icon} checkedIcon={checkedIcon} onClick={() => onClick(variant)}/>;
    return tooltip && <Tooltip key={variant.id} title={tooltip}>{radio}</Tooltip> || radio;
}

export default VariantOptionRadio;