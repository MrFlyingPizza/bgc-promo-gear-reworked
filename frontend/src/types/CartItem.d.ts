import {ProductVariant} from "types/ProductVariant";

export interface CartItem {
    variant: ProductVariant,
    product: {
        id: number,
        name: string
    },
    userId: number,
    quantity: number
}