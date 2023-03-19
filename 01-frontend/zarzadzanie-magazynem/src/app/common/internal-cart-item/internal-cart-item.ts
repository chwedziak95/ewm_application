import { Material } from "../material/material";

export class InternalCartItem {
  id: number;
  quantity: number;
  name: string;
  availableQuantity: number;

  constructor(material: Material) {
    this.id = material.materialId;
    this.quantity = 1;
    this.name = material.materialName;
    this.availableQuantity = material.materialQuantity;
  }
}
