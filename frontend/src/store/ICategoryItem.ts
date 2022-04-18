export interface ICategoryItem {
    id: number,
    name: string,

    subcategories: {
        id: number,
        name: string
    }[]
}