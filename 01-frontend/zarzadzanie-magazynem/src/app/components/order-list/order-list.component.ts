import { Component, OnInit } from '@angular/core';
import { Orders } from 'src/app/common/orders/orders';
import { FilterByPipe } from 'src/app/pipes/filter-by.pipe';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
  providers: [FilterByPipe]
})
export class OrderListComponent implements OnInit {

  orderNumberFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po numerze zamówienia
  orderDateFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po daty zamówienia
  orderStatusFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po statusu zamówienia
  orderVendorFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po dostawcy zamówienia

  orders$: Array<Orders> = [];
  constructor(
    private ordersService: OrderService
  ) {
    this.ordersService.getAll().subscribe(orders => {
      this.orders$ = orders;
    })
  }

  ngOnInit(): void {
  }

  // Get unique status names from orders
  getUniqueStatusNames(): string[] {
    const statusNames = this.orders$.map(order => order.status?.name);
    return Array.from(new Set(statusNames));
  }




}

