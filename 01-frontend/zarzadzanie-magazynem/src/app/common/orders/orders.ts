import { OrderItems } from "../order-items/order-items";
import { OrderStatus } from "../order-status/order-status";
import { User } from "../user/user";

export class Orders {
  ordersId: number;
  orderNumber: string;
  orderDate: Date;
  deliveryDate: Date;
  comment: string;
  orderTotal: number;
  user: User;
  status: OrderStatus;
  orderItems: OrderItems[];

  constructor() {}

  getOrderDateString(): string {
    const options: Intl.DateTimeFormatOptions = { year: "numeric", month: "2-digit", day: "2-digit" };
    return this.orderDate.toLocaleDateString("en-US", options);
  }
  
}
