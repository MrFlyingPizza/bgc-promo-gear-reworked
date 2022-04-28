import {OptionValue, ProductVariant} from "types/Product";
import {OptionCheckboxControl} from "components/product/option_selection/OptionCheckbox";

type OptionPartition = {
    name: string,
    options: OptionValue[]
}

export function mapAllOptionValues(variants: ProductVariant[]) {
    const allOptionValues: OptionValue[] = [];
    variants.map((variant: ProductVariant) => variant.options.forEach((option: OptionValue) => allOptionValues.push(option)));
    return allOptionValues;
}

export function partitionOptions(allOptionValues: OptionValue[]) {
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

export function relateOptions(variants: ProductVariant[], allOptionValues: OptionValue[]) {
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

export function findCompatibleOptions(targetOptions: OptionValue[], optionRelations: Map<OptionValue, Set<OptionValue>>) {
    const compatibleOptions = [...targetOptions];
    optionRelations.forEach((relatedOptions, option) => {
        if (targetOptions.includes(option)) return;
        let compatible = true;
        targetOptions.every(targetOption => {
            return compatible = relatedOptions.has(targetOption);
        });
        if (compatible) {
            compatibleOptions.push(option);
        }
    });
    return compatibleOptions;
}