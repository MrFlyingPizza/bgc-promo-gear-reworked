import {OptionValue, ProductVariant} from "types/Product";
import React, {useEffect, useRef, useState} from "react";
import {FormControl, FormGroup, FormLabel} from "@mui/material";
import OptionCheckbox from "components/product/option_selection/OptionCheckbox";
import {
    findCompatibleOptions,
    mapAllOptionValues,
    partitionOptions,
    relateOptions, resolveVariantFromOptions
} from "components/product/option_selection/helpers";

type ProductOptionSelectionProps = {
    variants: ProductVariant[],
    onChange: (variant: ProductVariant) => void
}

type OptionValueGroupFragmentProps = {
    name: string,
    options: OptionValue[]
};

const OptionSelection = ({variants, onChange}: ProductOptionSelectionProps) => {

    function initRef() {
        const allOptionValues = mapAllOptionValues(variants);
        return {
            allOptionValues: allOptionValues,
            optionPartitions: partitionOptions(allOptionValues),
            optionRelations: relateOptions(variants, allOptionValues),
        }
    }

    const ref = useRef(initRef());
    const [selectedOptions, setSelectedOptions] = useState<OptionValue[]>([]);
    const [disabledOptions, setDisabledOptions] = useState<OptionValue[]>([]);

    useEffect(() => {
        if (selectedOptions.length > 0) {
            const compatibleOptions = findCompatibleOptions(selectedOptions, ref.current.optionRelations);
            setDisabledOptions(ref.current.allOptionValues.filter(option => !compatibleOptions.includes(option)));
        } else {
            setDisabledOptions([]);
        }
        onChange(resolveVariantFromOptions(variants, selectedOptions));
    }, [selectedOptions]);

    const OptionValueGroupFragment = ({name, options}: OptionValueGroupFragmentProps) => {

        function handleChange(option: OptionValue, checked: boolean) {
            setSelectedOptions(checked ? [...selectedOptions, option]
                : selectedOptions.filter(selectedOption => selectedOption != option));
        }

        return (
            <React.Fragment>
                <FormLabel>{name}</FormLabel>
                <FormGroup row>{options.map(option =>
                    <OptionCheckbox option={option}
                                    disabled={disabledOptions.includes(option)}
                                    checked={selectedOptions.includes(option)}
                                    onChange={handleChange}
                    />)}
                </FormGroup>
            </React.Fragment>
        )
    };

    return (
        <FormControl>{ref.current.optionPartitions.map(partition =>
            <OptionValueGroupFragment key={partition.name} name={partition.name} options={partition.options}/>)}
        </FormControl>
    )
}

export default OptionSelection;