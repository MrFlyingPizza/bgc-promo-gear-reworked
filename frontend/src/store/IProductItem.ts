export interface IProductItem {
    id: number,
    name: string,
    brand: string,
    description: string,
    isWaitlistEnabled: boolean,
    isBigItem: boolean,

    images: {
       src: string,
       position: number,
    }[]

    variants: {
        id: number,
        imageId: number,
        options: {
            valueId: number,
            optionId: number,
            name: string,
            value: string
        }[]
    }[]

    category : {
        id: number,
        name: string,
        parent:{
            id: number,
            name: string
        }
    }
}