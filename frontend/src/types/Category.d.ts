export default interface Category {
    id: number,
    name: string,
    parent: Category

    subcategories?: {
        id: number,
        name: string
    }[]
}