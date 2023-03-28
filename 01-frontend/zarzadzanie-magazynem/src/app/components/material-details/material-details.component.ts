import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CartItem } from 'src/app/common/cart-item/cart-item';
import { Category } from 'src/app/common/category/category';
import { Material } from 'src/app/common/material/material';
import { Vendor } from 'src/app/common/vendor/vendor';
import { CartService } from 'src/app/services/cart.service';
import { CategoryService } from 'src/app/services/category.service';
import { MaterialService } from 'src/app/services/material.service';
import { VendorService } from 'src/app/services/vendor.service';
import { UpdateMaterialPayload } from '../create-material/update-material.payload';

@Component({
  selector: 'app-material-details',
  templateUrl: './material-details.component.html',
  styleUrls: ['./material-details.component.css'],
})
export class MaterialDetailsComponent implements OnInit {
  categories: Category[];
  vendors: Vendor[];
  material: Material;
  selectedVendor: Vendor;
  isEditMode: boolean = false;
  materialPayload: UpdateMaterialPayload;

  constructor(
    private materialService: MaterialService,
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private vendorService: VendorService
  ) {
    this.selectedVendor = {} as Vendor;
    this.material = {} as Material;
    this.categories = [];
    this.vendors = [];
    this.materialPayload = {} as UpdateMaterialPayload;
  }

  ngOnInit() {
    this.getMaterialDetails(this.route.snapshot.params['id']);
    this.getCategories();
    this.getVendors();
  }

  addToCart() {
    console.log(`Adding to cart: ${this.material.materialId}`);
    const theCartItem = new CartItem(this.material);
    this.cartService.addToCart(theCartItem);
  }

  getMaterialDetails(id: number) {
    this.materialService
      .getMaterial(id)
      .subscribe((material) => (this.material = material));
  }

  getCategories() {
    this.categoryService
      .getAll()
      .subscribe((categories) => (this.categories = categories));
  }

  getVendors() {
    this.vendorService
      .getAll()
      .subscribe((vendors) => (this.vendors = vendors));
  }

  saveChanges() {
    this.isEditMode = false;
    Object.assign(this.material, this.materialPayload);
    this.materialService
      .updateMaterial(this.material.materialId, this.materialPayload)
      .subscribe((response) => {
        if (response.status == 200 || response.status == 201) {
          this.toastr.success('Zapisano zmiany');
          this.updateData();
        } else {
          this.toastr.error('Wystąpił bład');
        }
      });
  }

  editVendor(): void {
    this.router.navigate(['/vendors']);
  }

  toggleStatus(): void {
    this.material.materialStatus = !this.material.materialStatus;
    this.materialService
      .updateMaterial(this.material.materialId, this.materialPayload)
      .subscribe();
  }

  toggleEditMode(): void {
    this.isEditMode = true;
    this.materialPayload = {
      materialName: this.material.materialName,
      materialPrice: this.material.materialPrice,
      materialLocation: this.material.materialLocation,
      materialQuantity: this.material.materialQuantity,
      unitOfMeasure: this.material.unitOfMeasure,
      materialSafetyStock: this.material.materialSafetyStock,
      materialDescription: this.material.materialDescription,
      materialVendor: this.material.materialVendor.vendorId
    };
  }

  cancelChanges(): void {
    this.isEditMode = false;
  }

  updateData() {
    this.getMaterialDetails(this.material.materialId);
    this.getCategories();
    this.getVendors();
  }
}
