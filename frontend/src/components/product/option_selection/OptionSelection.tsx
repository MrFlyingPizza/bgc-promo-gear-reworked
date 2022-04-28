import React, {useEffect, useState} from "react";
import {FormControl, FormGroup, FormLabel} from "@mui/material";
import OptionCheckbox from "components/product/option_selection/OptionCheckbox";
import {
    findCompatibleOptions
} from "components/product/option_selection/helpers";

export type SimpleOptionValue = {
    id: number,
    value: string
}

export type OptionValueGroup = {
    name: string,
    optionIds: number[]
};

export type ProductOptionSelectionProps = {
    options: SimpleOptionValue[],
    groups: OptionValueGroup[],
    relation: Map<number, number[]>,
    onChange: (selectedIds: number[]) => void
}

const OptionSelection = ({options, groups, relation, onChange}: ProductOptionSelectionProps) => {

    const [selectedOptionIds, setSelectedOptions] = useState<number[]>([]);
    const [disabledOptions, setDisabledOptions] = useState<number[]>([]);

    useEffect(() => {
        if (selectedOptionIds.length > 0) {
            const compatibleOptionIds = findCompatibleOptions(selectedOptionIds, relation);
            setDisabledOptions(options.filter(option => !compatibleOptionIds.includes(option.id))
                .map((option) => option.id));
        } else {
            setDisabledOptions([]);
        }
        onChange(selectedOptionIds);
    }, [selectedOptionIds]);

    const OptionValueGroupFragment = ({name, optionIds}: OptionValueGroup) => {

        function handleChange(optionId: number, checked: boolean) {
            const newSelectedOptions = checked ? [...selectedOptionIds, optionId]
                : selectedOptionIds.filter(selectedOptionId => selectedOptionId != optionId);
            setSelectedOptions(newSelectedOptions);
        }

        return (
            <React.Fragment>
                <FormLabel>{name}</FormLabel>
                <FormGroup row>{optionIds.map(optionId =>
                    <OptionCheckbox value={optionId}
                                    label={options.find(option => option.id == optionId).value}
                                    disabled={disabledOptions.find(disabledId => disabledId == optionId) && true}
                                    checked={selectedOptionIds.find(checkedId => checkedId == optionId) && true}
                                    onChange={handleChange}
                    />)}
                </FormGroup>
            </React.Fragment>
        )
    };

    return (
        <FormControl>{groups.map(group =>
            <OptionValueGroupFragment key={group.name} name={group.name} optionIds={group.optionIds}/>)}
        </FormControl>
    )
}

export default OptionSelection;