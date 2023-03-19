import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { InternalCartItem } from 'src/app/common/internal-cart-item/internal-cart-item';
import { InternalOrder } from 'src/app/common/internal-order/internal-order';
import { InternalCartService } from 'src/app/services/internal-cart.service';
import { InternalOrderService } from 'src/app/services/internal-order.service';

@Component({
  selector: 'app-internal-order-cart',
  templateUrl: './internal-order-cart.component.html',
  styleUrls: ['./internal-order-cart.component.css']
})
export class InternalOrderCartComponent implements OnInit{

  pickUpForm: FormGroup;

  pickup = '';

  cartItems: InternalCartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;


  constructor(
    private cartService: InternalCartService,
    private orderService: InternalOrderService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.listCartDetails(),
      this.pickUpForm = new FormGroup({
        pickup: new FormControl('')
      });
  }

  listCartDetails(){
    this.cartItems = this.cartService.internalCartItems;
  }

  incrementQuantity(theCartItem: InternalCartItem) {
    this.cartService.addToInternalCart(theCartItem);
  }

  decrementQuantity(theCartItem: InternalCartItem) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: InternalCartItem) {
    this.cartService.remove(theCartItem);
  }

  checkout() {
    let pickup = this.pickUpForm.get('pickup').value;

    const cartItems = this.cartService.internalCartItems;

    let orderItems: { materialId: number; quantity: number; }[] = cartItems.map(tempInternalCartItem => {
      return {
        materialId: tempInternalCartItem.id!,
        quantity: tempInternalCartItem.quantity
      };
    });

    let interalOrder = new InternalOrder();

    interalOrder.pickUpLocation = pickup;
    interalOrder.orderItems = orderItems as any;

    this.orderService.createOrder(interalOrder).subscribe({
      next: _response => {
        this.toastr.success("Stworzono rezerwację");
        this.cartService.resetCart();
        this.router.navigateByUrl("/material");
      },
      error: (err) => {
        this.toastr.error(`Wystąpił błąd: ${err.message}`);
      }
    });
  }


}
