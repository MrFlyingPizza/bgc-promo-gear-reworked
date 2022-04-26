import {OptionValue, ProductVariant} from "types/Product";

type ProductOptionSelectionProps = {
    variants: ProductVariant[],
    onResolve: (variant: ProductVariant) => void
}

const ProductOptionSelection = ({variants}: ProductOptionSelectionProps) => {

    const valueSelectionMap = new Map<OptionValue, Set<OptionValue>>();

    variants.forEach(variant => {
        variant.options.forEach(value => {
            let valueSet = valueSelectionMap.get(value);
            // todo implement
        });
    });

}

export default ProductOptionSelection;