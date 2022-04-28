import ProductVariantAvailability from "types/ProductVariantAvailability";

export interface ProductImage {
    id: number,
    alt: string,
    src: string
}

export interface Option {
    id: number,
    name: string
}

export interface OptionValue {
    valueId: number,
    optionId: number,
    name: string,
    value: string
}

export interface ProductVariant {
    id: number,
    imageId: number,
    availability: ProductVariantAvailability,
    image: ProductImage,
    options: OptionValue[]
}

export interface Category {
    id: number,
    name: string,
    parent: Category
}

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