import ProductVariantAvailability from "types/ProductVariantAvailability";
import {ProductImage} from "types/ProductImage";
import {OptionValue} from "types/OptionValue";


export interface ProductVariant {
    id: number,
    availability: ProductVariantAvailability,
    image: ProductImage,
    options: OptionValue[]
}