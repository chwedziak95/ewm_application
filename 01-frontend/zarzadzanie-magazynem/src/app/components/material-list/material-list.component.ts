import { Component, OnInit } from '@angular/core';
import { Material } from 'src/app/common/material/material';
import { MaterialService } from 'src/app/services/material.service';
import { FilterByPipe } from 'src/app/pipes/filter-by.pipe';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/common/cart-item/cart-item';
import { InternalCartService } from 'src/app/services/internal-cart.service';
import { InternalCartItem } from 'src/app/common/internal-cart-item/internal-cart-item';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-material-list',
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css'],
  providers: [FilterByPipe]
})
export class MaterialListComponent implements OnInit {

  materialNumberFilter: string = '';
  materialNameFilter: string = '';
  materialCategoryFilter: string = '';
  materialVendorFilter: string = '';

  internalCartItemsCount: number = 0;
  internalCartSubscription: Subscription;

  material$: Array<Material> = [];
  constructor(
    private materialService: MaterialService,
    private cartService: CartService,
    private internalCartService: InternalCartService) {
    this.materialService.getAll().subscribe(material => {
      this.material$ = material;
    }),
    this.internalCartSubscription = this.internalCartService.internalCartItems$.subscribe(internalCartItems => {
      this.internalCartItemsCount = internalCartItems.reduce((acc, item) => acc + item.quantity, 0);
    });
  }

  ngOnDestroy() {
    this.internalCartSubscription.unsubscribe();
  }

  ngOnInit() {}

  addToCart(material: Material) {
    const cartItem = new CartItem(material);
    this.cartService.addToCart(cartItem);
  }

  decrementQuantity(material: Material) {
    const existingCartItem = this.cartService.cartItems.find(item => item.id === material.materialId);
    this.cartService.decrementQuantity(existingCartItem);
  }

  addToInternalCart(material: Material){
    const cartItem = new InternalCartItem(material);
    this.internalCartService.addToInternalCart(cartItem);
  }

  decrementInternalOrderQuantity(material: Material){
    const existingCartItem = this.internalCartService.internalCartItems.find(item => item.id === material.materialId);
    this.internalCartService.decrementQuantity(existingCartItem);
  }
}
