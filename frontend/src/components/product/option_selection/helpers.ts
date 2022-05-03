import {OptionValueGroup, SimpleOptionValue} from "components/product/option_selection/OptionSelection";
import {OptionValue} from "types/OptionValue";
import {ProductVariant} from "types/ProductVariant";
import {Option} from "types/Option";

export function collectAndSimplifyUniqueOptions(options: OptionValue[]): SimpleOptionValue[] {
    const simpleOptionValues: SimpleOptionValue[] = [];
    options.forEach(option =>
        !simpleOptionValues.find((simpleOptionValue => simpleOptionValue.id == option.valueId))
        && simpleOptionValues.push({id: option.valueId, value: option.value})
    );
    return simpleOptionValues;
}

export function groupOptions(options: Option[], allOptionValues: OptionValue[]): OptionValueGroup[] {
    const optionPartitionList: OptionValueGroup[] = [];
    if (allOptionValues.length == 0 || options.length == 0) return optionPartitionList;

    const optionPartitionMap = new Map<number, Set<number>>(options.map(option => [option.id, new Set()]));

    allOptionValues.forEach(value => optionPartitionMap.get(value.optionId).add(value.valueId));
    return Array.from(optionPartitionMap).map(value => {
        return {
            name: options.find(option => option.id == value[0]).name,
            optionIds: Array.from(value[1])
        }
    });
}

export function relateOptions(variants: ProductVariant[], allOptionValues: OptionValue[]): Map<number, number[]> {
    if (allOptionValues.length == 0) {
        return new Map();
    }
    const optionRelationMap = new Map<number, Set<number>>(allOptionValues.map(option => [option.valueId, new Set()]));
    variants.forEach(variant => {
        variant.options.forEach(variantOption => {
            const relatedIds = optionRelationMap.get(variantOption.valueId);
            variant.options.map(option => option.valueId).forEach((id) => relatedIds.add(id));
        });
    });
    return new Map(Array.from(optionRelationMap).map(value => [value[0], Array.from(value[1])]));
}

export function findCompatibleOptions(sourceIds: number[], relations: Map<number, number[]>) {
    const compatibleOptions = [...sourceIds];
    relations.forEach((related, sourceId) => !sourceIds.includes(sourceId)
        && sourceIds.every((sourceId) => related.includes(sourceId)) && compatibleOptions.push(sourceId));
    return compatibleOptions;
}

export function resolveVariantFromOptions(variants: ProductVariant[], targetOptionIds: number[]) {
    if (targetOptionIds.length == 0) return null;
    return variants.find(variant => {
        return targetOptionIds.every(targetOptionId => {
            return variant.options.length == targetOptionIds.length && variant.options.find(variantOption => variantOption.valueId == targetOptionId);
        });
    });
}