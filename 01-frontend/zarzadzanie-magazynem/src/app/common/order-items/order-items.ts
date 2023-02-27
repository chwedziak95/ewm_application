import { Material } from "../material/material";
import { Orders } from "../orders/orders";

export class OrderItems {
    orderItemId: number;
    orders: Orders;
    materialId: Material;
    quantity: number;
  
    constructor(
      orderItemId: number,
      orders: Orders,
      materialId: Material,
      quantity: number
    ) {
      this.orderItemId = orderItemId;
      this.orders = orders;
      this.materialId = materialId;
      this.quantity = quantity;
    }
  }