import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Category } from 'src/app/common/category/category';
import { Material } from 'src/app/common/material/material';
import { Vendor } from 'src/app/common/vendor/vendor';
import { CartService } from 'src/app/services/cart.service';
import { CategoryService } from 'src/app/services/category.service';
import { MaterialService } from 'src/app/services/material.service';
import { VendorService } from 'src/app/services/vendor.service';

@Component({
  selector: 'app-material-details',
  templateUrl: './material-details.component.html',
  styleUrls: ['./material-details.component.css']
})
export class MaterialDetailsComponent implements OnInit {

  categories: Category[];
  vendors: Vendor[];
  material: Material;
  selectedVendor: Vendor;
  isEditMode: boolean = false;


  constructor(private materialService: MaterialService,
              private router: Router,
              private cartService: CartService,
              private route: ActivatedRoute,
              private categoryService: CategoryService,
              private vendorService: VendorService) {
                this.selectedVendor = {} as Vendor;
                this.material = {} as Material;
                this.categories = [];
                this.vendors = [];
               }

  ngOnInit() {
    this.getMaterialDetails(this.route.snapshot.params['materialId']);
    this.getCategories();
    this.getVendors();
  }

  addToCart() {

    console.log(`Adding to cart: ${this.material.materialId}`);
    const theCartItem = new CartItem(this.material);
    this.cartService.addToCart(theCartItem);
    
  }

  getMaterialDetails(id: number) {
    this.materialService.getMaterial(id).subscribe(
      material => this.material = material
    );
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

  saveChanges(): void { // add this method
    this.isEditMode = false;
    this.materialService.updateMaterial(this.material).subscribe();
  }

  editVendor(): void {
    this.router.navigate(['/vendors']);
  }

  toggleStatus(): void {
    this.material.materialStatus = !this.material.materialStatus;
    this.materialService.updateMaterial(this.material).subscribe();
  }

  toggleEditMode(): void {
    this.isEditMode = true;
  }

  cancelChanges(): void {
    this.isEditMode = false;
  }
  
}
