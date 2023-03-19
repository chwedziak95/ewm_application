import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CartItem } from 'src/app/common/cart-item/cart-item';
import { Orders } from 'src/app/common/orders/orders';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  commentForm: FormGroup;

  comment = '';

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.listCartDetails(),
      this.commentForm = new FormGroup({
        commenttext: new FormControl('')
      });
  }

  listCartDetails() {
    this.cartItems = this.cartService.cartItems;

    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    this.cartService.computeCartTotals();

  }

  incrementQuantity(theCartItem: CartItem) {
    this.cartService.addToCart(theCartItem);
  }

  decrementQuantity(theCartItem: CartItem) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: CartItem) {
    this.cartService.remove(theCartItem);
  }

  checkout() {  
    let comment = this.commentForm.get('commenttext').value;
  
    const cartItems = this.cartService.cartItems;
  
    let orderItems: { materialId: number; quantity: number; }[] = cartItems.map(tempCartItem => {
      return {
        materialId: tempCartItem.id!,
        quantity: tempCartItem.quantity
      };
    });
  
    let orders = new Orders();
  
    orders.comment = comment;
    orders.orderItems = orderItems as any;
  
    this.orderService.createOrder(orders).subscribe({
      next: response => {
        this.toastr.success(`Zamówienie wysłane`);
        this.cartService.resetCart();
        this.router.navigateByUrl("/material");
      },
      error: (err) => {
        this.toastr.error(`Wystąpił błąd: ${err.message}`);
      }
    });
  }
}
