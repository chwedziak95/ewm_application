import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/shared/auth.service';
import { InternalOrder } from 'src/app/common/internal-order/internal-order';
import { Orders } from 'src/app/common/orders/orders';
import { OrderItems } from 'src/app/common/order-items/order-items';
import { User } from 'src/app/common/user/user';
import { InternalOrderService } from 'src/app/services/internal-order.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';
import { InternalOrderItem } from 'src/app/common/internal-order-item/internal-order-item';
import { ModalDismissReasons, NgbModal, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {

  user: User;
  totalValue: number;
  orderQty: number;
  internalOrderQty: number;
  orders$: Array<Orders> = [];
  internalOrders$: Array<InternalOrder> = [];
  internalOrder: InternalOrder;
  orders: Orders;
  orderItems: OrderItems;
  internalOrderItem: InternalOrderItem;
  vendorTotalValues: Map<string, number> = new Map();
  maxTotalValue: number;
  closeResult = '';
  deliveryDateFrom: NgbDate | null = null;
  deliveryDateTo: NgbDate | null = null;
  selectedStatus: string | null = null;
  uniqueStatuses: string[] = [];


  constructor(
    private authService: AuthService,
    private userService: UserService,
    private orderService: OrderService,
    private internalOrderService: InternalOrderService,
    private router: Router,
    private modalService: NgbModal,
    private toastr: ToastrService
  ) {

  }

  ngOnInit() {
    this.updateData();
  }

  calculateTotalValue() {
    this.totalValue = this.orders$.reduce((accumulator, order) => accumulator + order.orderTotal, 0);
    this.orderQty = this.orders$.reduce((accumulator, order) => {
      return accumulator + order.orderItems.reduce((itemAccumulator, item) => itemAccumulator + item.quantity, 0);
    }, 0);
    this.calculateVendorTotalValues();
  }

  calculateVendorTotalValues() {
    this.orders$.forEach(order => {
      order.orderItems.forEach(orderItem => {
        const vendor = orderItem.materialId.materialVendor;
        const orderItemTotal = orderItem.materialId.materialPrice * orderItem.quantity;
        const currentValue = this.vendorTotalValues.get(vendor.vendorName) || 0;
        const newValue = currentValue + orderItemTotal;
        this.vendorTotalValues.set(vendor.vendorName, newValue);
        this.maxTotalValue = Math.max(this.maxTotalValue, newValue);
      });
    });
  }

  open(content, internalOrder) {
    this.internalOrder = internalOrder;
    const modalRef = this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    modalRef.result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  cancel(modal) {
    this.internalOrderService.cancelOrder(this.internalOrder.internalOrderId).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204 || response.status === 201) {
          this.toastr.warning('Potwierdzono anulowanie zamówienia');
          modal.close();
          this.updateData();
        } else {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
    );
  }

  updateData() {
    const username = this.authService.getUserName();
    this.userService.getUser(username).subscribe((response) => {
      this.user = User.fromResponse(response);

      this.orderService.getAllByUser(this.user.userId).subscribe(orders => {
        this.orders$ = orders;
        this.calculateTotalValue();
      });

      this.internalOrderService.getAllByUser(this.user.userId).subscribe(internalOrders => {
        this.internalOrders$ = internalOrders;
        this.uniqueStatuses = [...new Set(internalOrders.map(order => order.status.name))];
      });
    });
  }  

  filterInternalOrders() {
    const fromDate = this.deliveryDateFrom ? new Date(this.deliveryDateFrom.year, this.deliveryDateFrom.month - 1, this.deliveryDateFrom.day) : null;
    const toDate = this.deliveryDateTo ? new Date(this.deliveryDateTo.year, this.deliveryDateTo.month - 1, this.deliveryDateTo.day) : null;

    this.internalOrderService.getAllByUser(this.user.userId).subscribe(internalOrders => {
      this.internalOrders$ = internalOrders.filter(order => {
        const orderDate = new Date(order.orderDate);
        const isAfterFromDate = !fromDate || orderDate >= fromDate;
        const isBeforeToDate = !toDate || orderDate <= toDate;
        const hasSelectedStatus = !this.selectedStatus || order.status.name === this.selectedStatus;

        return isAfterFromDate && isBeforeToDate && hasSelectedStatus;
      });
    });
  }

  onDateRangeChange() {
    this.filterInternalOrders();
  }

  onStatusChange(status: string) {
    this.selectedStatus = status;
    this.filterInternalOrders();
  }

}
