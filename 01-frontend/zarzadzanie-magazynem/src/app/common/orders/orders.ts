import { OrderItems } from "../order-items/order-items";
import { OrderStatus } from "../order-status/order-status";
import { User } from "../user/user";


export class Orders{
    ordersId: number;
    orderNumber: string;
    orderDate: Date;
    deliveryDate: Date;
    comment: string;
    orderTotal: number;
    user: User;
    status: OrderStatus;
    orderItems: OrderItems[];
  
    constructor(
      ordersId: number,
      orderNumber: string,
      orderDate: Date,
      deliveryDate: Date,
      comment: string,
      orderTotal: number,
      user: User,
      status: OrderStatus,
      orderItems: OrderItems[]
    ) {
      this.ordersId = ordersId;
      this.orderNumber = orderNumber;
      this.orderDate = orderDate;
      this.deliveryDate = deliveryDate;
      this.comment = comment;
      this.orderTotal = orderTotal;
      this.user = user;
      this.status = status;
      this.orderItems = orderItems;
    }
}