import {Category} from "types/Category";
import {ProductVariant} from "types/ProductVariant";
import {Option} from "types/Option";

export interface Product {
    id: number,
    name: string,
    brand: string,
    description: string,
    isWaitListEnabled: boolean,
    isBigItem: boolean,
    options: Option[],
    variants: ProductVariant[],
    category: Category
}