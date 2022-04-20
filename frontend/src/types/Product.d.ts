import ProductVariantAvailability from "./ProductVariantAvailability";

export interface Product {
    id: number,
    name: string,
    brand: string,
    description: string,
    isWaitlistEnabled: boolean,
    isBigItem: boolean,

    images: {
        alt: string,
        src: string,
        position: number,
    }[]

    variants: {
        id: number,
        imageId: number,
        availability: ProductVariantAvailability,
        options: {
            valueId: number,
            optionId: number,
            name: string,
            value: string
        }[]
    }[]

    category: {
        id: number,
        name: string,
        parent: {
            id: number,
            name: string
        }
    }
}