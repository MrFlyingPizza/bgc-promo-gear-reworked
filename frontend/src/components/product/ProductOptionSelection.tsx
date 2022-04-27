import {OptionValue, ProductVariant} from "types/Product";
import {ChangeEvent, useEffect, useRef, useState} from "react";
import {Checkbox, FormControl, FormControlLabel, FormGroup, FormLabel} from "@mui/material";
import React from "react";

type ProductOptionSelectionProps = {
    variants: ProductVariant[],
    onResolve: (variant: ProductVariant) => void
}

type OptionPartition = {
    name: string,
    options: OptionValue[]
}

type OptionCheckBoxControl = {
    enable: () => void,
    disable: () => void
};

type OptionCheckBoxDelegation = (option: OptionValue, control: OptionCheckBoxControl) => void;

type OptionCheckBoxChangeHandler = (option: OptionValue, checked: boolean) => void;

type OptionValueGroupFragmentProps = {
    name: string,
    options: OptionValue[],
    onChange: OptionCheckBoxChangeHandler,
    controlRegistry: Map<OptionValue, OptionCheckBoxControl>
};

type OptionCheckBoxProps = {
    option: OptionValue,
    onChange: OptionCheckBoxChangeHandler,
    delegation: OptionCheckBoxDelegation
};

const ProductOptionSelection = ({variants}: ProductOptionSelectionProps) => {

    function makeRef() {

        function mapAllOptionValues() {
            const allOptionValues: OptionValue[] = [];
            variants.map(variant => variant.options.forEach(option => allOptionValues.push(option)));
            return allOptionValues;
        }

        function partitionOptions(allOptionValues: OptionValue[]) {
            const optionPartitionMap = new Map<string, OptionValue[]>();
            allOptionValues.forEach(value => {
                let values = optionPartitionMap.get(value.name);
                if (!values) {
                    values = [];
                    optionPartitionMap.set(value.name, values);
                }
                values.push(value);
            });
            const optionPartitionList: OptionPartition[] = [];
            optionPartitionMap.forEach((values, name) => optionPartitionList.push({name, options: values}));
            return optionPartitionList;
        }

        function relateOptions(allOptionValues: OptionValue[]) {
            const optionRelationMap = new Map<OptionValue, Set<OptionValue>>();
            allOptionValues.forEach(option => optionRelationMap.set(option, new Set<OptionValue>()));

            variants.forEach(variant => {
                variant.options.forEach(value => {
                    let valueSet = optionRelationMap.get(value);
                    variant.options.forEach(x => valueSet.add(x));
                });
            });
            return optionRelationMap;
        }

        const allOptionValues = mapAllOptionValues();

        return {
            allOptionValues: allOptionValues,
            optionPartitions: partitionOptions(allOptionValues),
            optionRelations: relateOptions(allOptionValues),
            controlRegistry: new Map<OptionValue, OptionCheckBoxControl>(),
            optionSelection: new Map(allOptionValues.map(option => [option, false]))
        }
    }

    const ref = useRef(makeRef());

    function disableIncompatibleOptions() {
        const selectedOptions = Array.from(ref.current.optionSelection).filter(entry => entry[1]).map(entry => entry[0]);
        let relatedOptions: Set<OptionValue> = new Set();
            ref.current.optionRelations.forEach((relatedOptions, option) => {
                let containsAll = true;
                selectedOptions.every(relatedOption => {return containsAll = relatedOptions.has(relatedOption)});
                !containsAll && relatedOptions.add(option)
            });

        let disabledOptions = ref.current.allOptionValues.filter(option => !relatedOptions.has(option));
        ref.current.controlRegistry.forEach((control, option) => {
            if (disabledOptions.includes(option)) {
                control.disable();
            } else {
                control.enable();
            }
        });
    }

    function handleChange(option: OptionValue, checked: boolean) {
        disableIncompatibleOptions();
    }

    const OptionCheckBox = ({option, onChange, delegation}: OptionCheckBoxProps) => {

        const [disabled, setDisabled] = useState(false);
        const [checked, setChecked] = useState(false);

        useEffect(() => {
            ref.current.optionSelection.set(option, checked);
        }, [checked]);

        delegation(option, {
            enable: () => setDisabled(false),
            disable: () => {
                setChecked(false);
                setDisabled(true);
            }
        });

        function handleChange(event: ChangeEvent<HTMLInputElement>) {
            const checked = event.target.checked;
            setChecked(checked);
            onChange(option, checked);
        }

        return (
            <FormControlLabel label={option.value} checked={checked} disabled={disabled} control={
                <Checkbox onChange={(event) => handleChange(event)}/>}
            />
        )
    }

    const OptionValueGroupFragment = ({name, options, onChange, controlRegistry}: OptionValueGroupFragmentProps) => {
        return (
            <React.Fragment>
                <FormLabel>{name}</FormLabel>
                <FormGroup row>{options.map(option =>
                    <OptionCheckBox key={option.valueId}
                                    option={option}
                                    onChange={(option, checked) => onChange(option, checked)}
                                    delegation={(option, control) =>
                                        controlRegistry.set(option, control)}
                    />)}
                </FormGroup>
            </React.Fragment>
        )
    };

    return (
        <FormControl>{ref.current.optionPartitions.map(partition =>
            <OptionValueGroupFragment key={partition.name}
                                      name={partition.name}
                                      options={partition.options}
                                      onChange={(option, checked) => handleChange(option, checked)}
                                      controlRegistry={ref.current.controlRegistry}
            />)}
        </FormControl>
    )
}

export default ProductOptionSelection;