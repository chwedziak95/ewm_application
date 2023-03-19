import { Material } from "../material/material";
import { Orders } from "../orders/orders";

export class OrderItems {
  orderItemId: number;
  orders: Orders;
  material: Material;
  qty: number;

  constructor(){}
}
