import { Category } from "../category/category";
import { Vendor } from "../vendor/vendor";

export class Material {
  materialId: number;
  materialNumber: string;
  materialManufacturer: string;
  materialName: string;
  materialPrice: number;
  materialQuantity: number;
  unitOfMeasure: string;
  materialEAN: string;
  materialCreated: Date;
  materialSafetyStock: number;
  materialDescription: string;
  materialCategory: Category;
  materialVendor: Vendor;
  materialStatus: boolean;

  constructor() { }

  static fromJSON(json: any): Material {
    const material = new Material();
    Object.assign(material, json);
    return material;
  }
}