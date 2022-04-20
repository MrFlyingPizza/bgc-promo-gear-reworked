export interface CartItem {
    variant: {
        id: number,
        image?: {
            id: number,
            src: string,
            alt: string
        },
        options: {
            valueId: number,
            optionId: number,
            name: string,
            value: string
        }[]
    },
    product: {
        id: number,
        name: string
    },
    userId: number,
    quantity: number
}