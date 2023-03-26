import { InternalOrder } from "../internal-order/internal-order";
import { Material } from "../material/material";

export class InternalOrderItem {

    orderItemId: number;
    internalOrder: InternalOrder;
    materialId: Material;
    quantity: number;

    constructor() { }

}
