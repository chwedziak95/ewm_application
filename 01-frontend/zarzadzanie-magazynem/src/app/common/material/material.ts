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
  
    constructor(materialId: number, materialNumber: string, materialManufacturer: string,
                materialName: string, materialPrice: number, materialQuantity: number,
                unitOfMeasure: string, materialEAN: string, materialCreated: Date,
                materialSafetyStock: number, materialDescription: string, materialCategory: Category,
                materialVendor: Vendor, materialStatus: boolean) {
      this.materialId = materialId;
      this.materialNumber = materialNumber;
      this.materialManufacturer = materialManufacturer;
      this.materialName = materialName;
      this.materialPrice = materialPrice;
      this.materialQuantity = materialQuantity;
      this.unitOfMeasure = unitOfMeasure;
      this.materialEAN = materialEAN;
      this.materialCreated = materialCreated;
      this.materialSafetyStock = materialSafetyStock;
      this.materialDescription = materialDescription;
      this.materialCategory = materialCategory;
      this.materialVendor = materialVendor;
      this.materialStatus = materialStatus;
    }
  }