export interface ISelectedCategoryItem {
    id: number,
    name: string,

    parent?: {
        id: number,
        name: string
    }
}