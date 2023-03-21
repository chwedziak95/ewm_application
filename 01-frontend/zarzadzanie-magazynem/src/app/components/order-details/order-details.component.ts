import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Material } from 'src/app/common/material/material';
import { OrderItems } from 'src/app/common/order-items/order-items';
import { Orders } from 'src/app/common/orders/orders';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent  implements OnInit{

  orders: Orders;
  material: Material;

  orderItems$: Array<OrderItems> = [];


  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ){
    this.orders = {} as Orders
    this.material = new Material();
  }

  ngOnInit() {
    this.getOrderDetails(this.route.snapshot.params['id']);
  }

  getOrderDetails(id: number){
    this.orderService.getOrder(id).subscribe(
      orders => {
        this.orders = orders;
        this.orderItems$ = orders.orderItems;
      }
    );
  }
}
