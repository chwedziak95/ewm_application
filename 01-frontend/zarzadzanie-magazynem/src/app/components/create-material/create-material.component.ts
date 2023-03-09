import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/common/category/category';
import { Vendor } from 'src/app/common/vendor/vendor';
import { CategoryService } from 'src/app/services/category.service';
import { VendorService } from 'src/app/services/vendor.service';

@Component({
  selector: 'app-create-material',
  templateUrl: './create-material.component.html',
  styleUrls: ['./create-material.component.css']
})
export class CreateMaterialComponent implements OnInit{

  vendors: Vendor[];
  categories: Category[];

  createMaterialForm!: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private vendorService: VendorService) { 
      this.categories = [];
      this.vendors = [];
  }

  ngOnInit(){
    this.createMaterialForm = this.formBuilder.group({
      material: this.formBuilder.group({
        materialNumber: [null, [ Validators.required ]],
        materialManufacturer: [null, [ Validators.required ]],
        materialName: [null, [ Validators.required ]],
        materialPrice: [null, [ Validators.required ]],
        materialQuantity: [null, [ Validators.required ]],
        unitOfMeasure: [null, [ Validators.required ]],
        materialEAN: [null, [ Validators.required ]],
        materialSafetyStock: [''],
        materialDescription: [''],
        materialCategory: [this.getCategories(), [ Validators.required ]],
        materialVendor: [this.getVendors(), [ Validators.required ]]
      })
    });
  }

  getCategories() {
    this.categoryService.getAll().subscribe(
      categories => this.categories = categories
    );
  }

  getVendors() {
    this.vendorService.getAll().subscribe(
      vendors => this.vendors = vendors
    );
  }

  createMaterialSubmit() {
    console.log("Tworzenie materiału...")
    console.log(this.createMaterialForm.get('material')!.value);
  };

}
