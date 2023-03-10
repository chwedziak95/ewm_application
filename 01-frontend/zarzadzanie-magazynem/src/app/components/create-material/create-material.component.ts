import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Category } from 'src/app/common/category/category';
import { Vendor } from 'src/app/common/vendor/vendor';
import { CategoryService } from 'src/app/services/category.service';
import { VendorService } from 'src/app/services/vendor.service';
import { EwmAppValidators } from 'src/app/validators/ewm-app-validators';

@Component({
  selector: 'app-create-material',
  templateUrl: './create-material.component.html',
  styleUrls: ['./create-material.component.css'],
})
export class CreateMaterialComponent implements OnInit {
  vendors: Vendor[] = [];
  categories: Category[] = [];

  createMaterialForm: FormGroup;

  
  private PRICE_PATTERN = /^[0-9]+(\.[0-9]{1,2})?$/;
  private ST_CHAR_PATTERN = /^[a-zA-Z0-9 ]*$/;
  private ONLY_CHAR_PATTERN = /^[a-zA-Z]*$/;

  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private vendorService: VendorService,
  ) {  }

  ngOnInit() {
    this.createMaterialForm = this.formBuilder.group({
      material: this.formBuilder.group({
        materialNumber: new FormControl('', [
          Validators.required,
          Validators.pattern(this.ST_CHAR_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialManufacturer: new FormControl('', [
          Validators.required,
          Validators.pattern(this.ST_CHAR_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialName: new FormControl('', [
          Validators.required,
          Validators.pattern(this.ST_CHAR_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialPrice: new FormControl('', [
          Validators.required,
          Validators.pattern(this.PRICE_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialQuantity: new FormControl('', [
          Validators.required,
          Validators.pattern(this.PRICE_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        unitOfMeasure: new FormControl('', [
          Validators.required,
          Validators.pattern(this.ONLY_CHAR_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialEAN: new FormControl('', [
          Validators.pattern(this.ST_CHAR_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialSafetyStock: new FormControl('', [
          Validators.pattern(this.PRICE_PATTERN),
          EwmAppValidators.notOnlyWhiteSpace,
        ]),
        materialDescription: [''],
        category: new FormControl('',[Validators.required]),
        vendor: new FormControl('',[Validators.required])
      }),
    });

    this.categoryService.getAll().subscribe(
      data => {
        this.categories = data;
      }
    )
  
    this.vendorService.getAll().subscribe(
      data => {
        this.vendors = data;
      }
    );
  }

  

  createMaterialSubmit() {
    if (this.createMaterialForm.invalid) {
      this.createMaterialForm.markAllAsTouched();
    }

    console.log('Tworzenie materiału...');
    console.log(this.createMaterialForm.get('material').value);
  }

  get materialNumber() {
    return this.createMaterialForm.get('material.materialNumber');
  }
  get materialManufacturer() {
    return this.createMaterialForm.get('material.materialManufacturer');
  }
  get materialName() {
    return this.createMaterialForm.get('material.materialName');
  }
  get materialPrice() {
    return this.createMaterialForm.get('material.materialPrice');
  }
  get materialQuantity() {
    return this.createMaterialForm.get('material.materialQuantity');
  }
  get unitOfMeasure() {
    return this.createMaterialForm.get('material.unitOfMeasure');
  }
  get materialEAN() {
    return this.createMaterialForm.get('material.materialEAN');
  }
  get materialSafetyStock() {
    return this.createMaterialForm.get('material.materialSafetyStock');
  }
  get materialDescription() {
    return this.createMaterialForm.get('material.materialDescription');
  }
  get materialCategory() {
    return this.createMaterialForm.get('material.category');
  }
  get materialVendor() {
    return this.createMaterialForm.get('material.vendor');
  }
}
