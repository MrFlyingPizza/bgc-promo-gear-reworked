import {OptionValue, Product, ProductVariant} from "types/Product";

type OptionPartition = {
    name: string,
    options: OptionValue[]
}

export function optionArrayContains(options: OptionValue[], option: OptionValue) {
    return options.every(existing => existing.valueId == option.valueId);
}

export function mapAllOptionValues(variants: ProductVariant[]) {
    const allOptionValues: OptionValue[] = [];
    variants.map((variant: ProductVariant) => variant.options.forEach((option: OptionValue) =>
        !optionArrayContains(allOptionValues, option) && allOptionValues.push(option)));
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
    const optionRelationMap = new Map<OptionValue, OptionValue[]>(allOptionValues.map(option => [option, []]));
    variants.forEach(variant => {
        variant.options.forEach(variantOption => {
            let relatedValues = optionRelationMap.get(variantOption);
            variant.options.forEach(x => !optionArrayContains(relatedValues, x) && relatedValues.push(x));
        });
    });
    return optionRelationMap;
}

export function findCompatibleOptions(targetOptions: OptionValue[], optionRelations: Map<OptionValue, OptionValue[]>) {
    const compatibleOptions = [...targetOptions];
    optionRelations.forEach((relatedOptions, option) => {
        if (targetOptions.includes(option)) return;
        let compatible = true;
        targetOptions.every(targetOption => {
            return compatible = relatedOptions.includes(targetOption);
        });
        if (compatible) {
            compatibleOptions.push(option);
        }
    });
    return compatibleOptions;
}

export function resolveVariantFromOptions(variants: ProductVariant[], targetOptions: OptionValue[]) {
    const result = variants.filter(variant => targetOptions.every(targetOption => variant.options.includes(targetOption)));
    return result.length > 0 ? result[0] : null;
}