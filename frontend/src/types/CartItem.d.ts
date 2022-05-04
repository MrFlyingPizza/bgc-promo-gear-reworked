import ProductVariant from "types/ProductVariant";

export default interface CartItem {
    variant: ProductVariant,
    product: {
        id: number,
        name: string
    },
    userId: number,
    quantity: number
}