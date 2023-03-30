import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';
import { Category } from 'src/app/common/category/category';
import { Material } from 'src/app/common/material/material';
import { Vendor } from 'src/app/common/vendor/vendor';
import { CategoryService } from 'src/app/services/category.service';
import { MaterialService } from 'src/app/services/material.service';
import { VendorService } from 'src/app/services/vendor.service';
import { CreateMaterialPayload } from './create-material.payload';

@Component({
  selector: 'app-create-material',
  templateUrl: './create-material.component.html',
  styleUrls: ['./create-material.component.css'],
})
export class CreateMaterialComponent implements OnInit {
  materials: Material[] = [];
  vendors: Vendor[] = [];
  categories: Category[] = [];
  materialPayload: CreateMaterialPayload;
  materialNumberExists: boolean = false;
  materialNameExists: boolean = false;
  materialEANExists: boolean = false;

  createMaterialForm: FormGroup;

  private PRICE_PATTERN = /^[0-9]+(\.[0-9]{1,2})?$/;
  private ST_CHAR_PATTERN = /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ0-9 ]*$/;
  private ONLY_CHAR_PATTERN = /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*$/;

  constructor(
    private categoryService: CategoryService,
    private vendorService: VendorService,
    private materialService: MaterialService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.materialPayload = {
      materialNumber: null,
      materialManufacturer: null,
      materialName: null,
      materialPrice: null,
      materialQuantity: null,
      unitOfMeasure: null,
      materialEAN: null,
      materialSafetyStock: null,
      materialDescription: null,
      materialLocation: null,
      materialCategory: null,
      materialVendor: null
    }
  }

  ngOnInit() {
    this.getMaterials();
    this.createMaterialForm = new FormGroup({
      materialNumber: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      materialManufacturer: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      materialName: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      materialPrice: new FormControl('', [
        Validators.required,
        Validators.pattern(this.PRICE_PATTERN)
      ]),
      materialQuantity: new FormControl('', [
        Validators.required,
        Validators.pattern(this.PRICE_PATTERN)
      ]),
      unitOfMeasure: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ONLY_CHAR_PATTERN)
      ]),
      materialEAN: new FormControl('', [
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      materialLocation: new FormControl('', [
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      materialSafetyStock: new FormControl('', [
        Validators.pattern(this.PRICE_PATTERN)
      ]),
      materialDescription: new FormControl('', [
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      category: new FormControl('', [Validators.required]),
      vendor: new FormControl('', [Validators.required])
    });

    this.categoryService.getAll().subscribe((data) => {
      this.categories = data;
    }, error => {
      return throwError(() => error);
    }
    );

    this.vendorService.getAll().subscribe((data) => {
      this.vendors = data;
    }, error => {
      return throwError(() => error);
    });
  }

  getMaterials() {
    this.materialService.getAll().subscribe(
      data => {
        this.materials = data;
      },
      error => {
        console.error('Wystąpił błąd podczas pobierania listy materiałów : ', error);
      }
    );
  }

  createMaterialSubmit() {
    this.materialPayload.materialNumber = this.createMaterialForm.get('materialNumber').value;
    this.materialPayload.materialManufacturer = this.createMaterialForm.get('materialManufacturer').value;
    this.materialPayload.materialName = this.createMaterialForm.get('materialName').value;
    this.materialPayload.materialPrice = this.createMaterialForm.get('materialPrice').value;
    this.materialPayload.materialQuantity = this.createMaterialForm.get('materialQuantity').value;
    this.materialPayload.unitOfMeasure = this.createMaterialForm.get('unitOfMeasure').value;
    this.materialPayload.materialEAN = this.createMaterialForm.get('materialEAN').value;
    this.materialPayload.materialSafetyStock = this.createMaterialForm.get('materialSafetyStock').value;
    this.materialPayload.materialDescription = this.createMaterialForm.get('materialDescription').value;
    this.materialPayload.materialLocation = this.createMaterialForm.get('materialLocation').value;
    this.materialPayload.materialCategory = this.createMaterialForm.get('category').value;
    this.materialPayload.materialVendor = this.createMaterialForm.get('vendor').value;

    if (this.createMaterialForm.invalid) {
      this.createMaterialForm.markAllAsTouched();
      Object.keys(this.createMaterialForm.controls).forEach((key) => {
        const control = this.createMaterialForm.controls[key];
        if (control.invalid) {
          console.log(key, control.errors);
        }
      });
      return;
    }

    this.materialService.createMaterial(this.materialPayload).subscribe({
      next: (response) => {
        this.toastr.success('Utworzono materiał');
      },
      error: (err) => {
        this.toastr.error(`Wystąpił błąd: ${err.message}`);
      }
    });
  }

  checkMaterialExists() {
    const materialNumber = this.createMaterialForm.get('materialNumber').value;
    const materialName = this.createMaterialForm.get('materialName').value;
    const materialEAN = this.createMaterialForm.get('materialEAN').value;
  
    this.materialNumberExists = materialNumber && this.materials.some(material => material.materialNumber === materialNumber);
    this.materialNameExists = materialName && this.materials.some(material => material.materialName === materialName);
    
    if (materialEAN) {
      this.materialEANExists = this.materials.some(material => material.materialEAN === materialEAN);
    } else {
      this.materialEANExists = false;
    }
  }
  

  get materialNumber() {
    return this.createMaterialForm.get('materialNumber');
  }
  get materialManufacturer() {
    return this.createMaterialForm.get('materialManufacturer');
  }
  get materialName() {
    return this.createMaterialForm.get('materialName');
  }
  get materialPrice() {
    return this.createMaterialForm.get('materialPrice');
  }
  get materialQuantity() {
    return this.createMaterialForm.get('materialQuantity');
  }
  get unitOfMeasure() {
    return this.createMaterialForm.get('unitOfMeasure');
  }
  get materialEAN() {
    return this.createMaterialForm.get('materialEAN');
  }
  get materialSafetyStock() {
    return this.createMaterialForm.get('materialSafetyStock');
  }
  get materialDescription() {
    return this.createMaterialForm.get('materialDescription');
  }
  get materialCategory() {
    return this.createMaterialForm.get('category');
  }
  get materialVendor() {
    return this.createMaterialForm.get('vendor');
  }
  get materialLocation() {
    return this.createMaterialForm.get('materialLocation');
  }
}
