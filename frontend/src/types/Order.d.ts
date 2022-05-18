import OrderStatus from "types/OrderStatus";
import OrderType from "types/OrderType";
import Product from "types/Product";
import ProductVariant from "types/ProductVariant";

export interface OrderOfficeLocation {
    id: number,
    name: string
}

type OrderItemProduct = Pick<Product, "id" | "name" | "category">
type OrderItemVariant = Omit<ProductVariant, "availability">

export interface OrderItem {
    product: OrderItemProduct,
    variant: OrderItemVariant,
    quantity: number
}

export default interface Order {
    id: number,
    submitter: string,
    fulfiller: string,
    recipient: string,
    status: OrderStatus,
    type: OrderType,
    submitterComments: string,
    fulfillerComments: string,
    officeLocation: OrderOfficeLocation,
    createdDate: Date,
    completedDate: Date,
    items: OrderItem[]
}