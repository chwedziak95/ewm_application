import { InternalOrderItem } from "../internal-order-item/internal-order-item";
import { OrderStatus } from "../order-status/order-status";
import { User } from "../user/user";

export class InternalOrder {

    internalOrderId: number;
    pickUpLocation: string;
    orderDate: Date;
    pickDate: Date;
    status: OrderStatus;
    user: User;
    orderItems: InternalOrderItem[];

    constructor(){}
}
