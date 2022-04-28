import {OptionValue, ProductVariant} from "types/Product";
import React, {useRef} from "react";
import {FormControl, FormGroup, FormLabel} from "@mui/material";
import OptionCheckbox, {OptionCheckboxControl} from "components/product/option_selection/OptionCheckbox";
import {
    findCompatibleOptions,
    mapAllOptionValues,
    partitionOptions,
    relateOptions
} from "components/product/option_selection/helpers";
import ProductVariantAvailability from "types/ProductVariantAvailability";

type ProductOptionSelectionProps = {
    variants: ProductVariant[],
    onResolve: (variant: ProductVariant) => void
}

type OptionValueGroupFragmentProps = {
    name: string,
    options: OptionValue[]
};

const OptionSelection = ({variants}: ProductOptionSelectionProps) => {

    function initRef() {
        const allOptionValues = mapAllOptionValues(variants);
        const selectedOptions: OptionValue[] = [];
        return {
            allOptionValues: allOptionValues,
            optionPartitions: partitionOptions(allOptionValues),
            optionRelations: relateOptions(variants, allOptionValues),
            controlRegistry: new Map<OptionValue, OptionCheckboxControl>(),
            selectedOptions: selectedOptions
        }
    }

    const ref = useRef(initRef());

    function processCompatibleOptions() {
        if (ref.current.selectedOptions.length > 0) {
            const compatibleOptions = findCompatibleOptions(ref.current.selectedOptions, ref.current.optionRelations);
            ref.current.controlRegistry.forEach((control, option) => {
                if (compatibleOptions.includes(option)) {
                    control.enable();
                } else {
                    control.disable();
                }
            })
        } else {
            ref.current.controlRegistry.forEach(control => control.enable());
        }

        variants.filter(variant => variant.availability == ProductVariantAvailability.AVAILABLE)
    }

    const OptionValueGroupFragment = ({name, options}: OptionValueGroupFragmentProps) => {

        function handleChange(option: OptionValue, checked: boolean) {
            if (checked) {
                ref.current.selectedOptions = [...ref.current.selectedOptions, option];
            } else {
                ref.current.selectedOptions = ref.current.selectedOptions.filter(selectedOption => selectedOption != option);
            }
            processCompatibleOptions();
        }

        function registerControl(option: OptionValue, control: OptionCheckboxControl) {
            ref.current.controlRegistry.set(option, control);
        }

        return (
            <React.Fragment>
                <FormLabel>{name}</FormLabel>
                <FormGroup row>{options.map(option =>
                    <OptionCheckbox key={option.valueId} option={option} onChange={handleChange}
                                    delegation={registerControl}/>)}
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