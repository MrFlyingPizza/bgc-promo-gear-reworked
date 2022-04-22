import ProductVariantAvailability from "types/ProductVariantAvailability";

export interface ProductVariant {
    id: number,
        imageId: number,
        availability: ProductVariantAvailability,
        image: {
        id: number,
            alt: string,
            src: string
    }
    options: {
        valueId: number,
            optionId: number,
            name: string,
            value: string
    }[]
}

export interface Product {
    id: number,
    name: string,
    brand: string,
    description: string,
    isWaitListEnabled: boolean,
    isBigItem: boolean,
    variants: ProductVariant[],

    category: {
        id: number,
        name: string,
        parent: {
            id: number,
            name: string
        }
    }
}