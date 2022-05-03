import {Chip, List, ListItem, Radio, Tooltip} from "@mui/material";
import * as React from "react";
import {Image} from "react-bootstrap";
import CheckIcon from '@mui/icons-material/Check';
import {ProductVariant} from "types/ProductVariant";
import placeholderSrc from "components/shared/PlaceholderImage";

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

    const src = variant.image?.src || placeholderSrc();
    const icon = <Image style={{height: 24, width: 24, objectFit: "cover"}} roundedCircle src={src}/>;
    const checkedIcon = (
        <>
            {icon}
            <CheckIcon sx={{
                color: "black",
                height: 32,
                width: 32,
                position: "absolute",
                objectFit: "cover",
                backgroundColor: "rgba(255, 255, 255, 0.7)"
            }}/>
        </>
    );
    const radio = <Radio checked={checked} key={variant.id} icon={icon} checkedIcon={checkedIcon}
                         onClick={() => onClick(variant)}/>;
    return tooltip && <Tooltip key={variant.id} title={tooltip}>{radio}</Tooltip> || radio;
}

export default VariantOptionRadio;