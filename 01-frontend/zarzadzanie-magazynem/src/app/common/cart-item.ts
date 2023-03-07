import { Material } from "./material/material";

export class CartItem {

    id: number;
    quantity: number;
    name: string;
    unitPrice: number;

    constructor(material: Material){
        this.id = material.materialId;
        this.quantity = 1;
        this.name = material.materialName;
        this.unitPrice = material.materialPrice;
    }

}
