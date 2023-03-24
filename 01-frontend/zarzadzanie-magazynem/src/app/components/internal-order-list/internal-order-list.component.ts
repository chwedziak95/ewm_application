import { Component, OnInit } from '@angular/core';
import { InternalOrder } from 'src/app/common/internal-order/internal-order';
import { InternalOrderService } from 'src/app/services/internal-order.service';

@Component({
  selector: 'app-internal-order-list',
  templateUrl: './internal-order-list.component.html',
  styleUrls: ['./internal-order-list.component.css']
})
export class InternalOrderListComponent implements OnInit{

  internalOrders$: Array<InternalOrder> = [];
  internalOrdersNew$: Array<InternalOrder> = [];
  sortOrder = 'asc';

  constructor(
    private internalOrderService: InternalOrderService
  ){
    this.internalOrderService.getAll().subscribe( internalOrders =>{
      this.internalOrders$ = internalOrders;
      this.internalOrdersNew$ = internalOrders.filter(order => order.status?.statusId === 1);
    })
  }

  ngOnInit(){
  }

  getUniqueStatusNames(): string[] {
    const statusNames = this.internalOrders$.map(internalOrders => internalOrders.status?.name);
    return Array.from(new Set(statusNames));
  }

  toggleSortOrder() {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
  }


}
