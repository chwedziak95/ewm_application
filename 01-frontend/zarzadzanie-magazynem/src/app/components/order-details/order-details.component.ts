import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ModalDismissReasons, NgbDateStruct, NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Material } from 'src/app/common/material/material';
import { OrderItems } from 'src/app/common/order-items/order-items';
import { Orders } from 'src/app/common/orders/orders';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orders: Orders;
  material: Material;
  closeResult = '';
  deliveryDate: NgbDateStruct;
  minDate: string;
  maxDate: string;
  isDeliveryDateValid: boolean;

  orderItems$: Array<OrderItems> = [];


  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private toastr: ToastrService
  ) {
    this.orders = {} as Orders
    this.material = new Material();
  }

  ngOnInit() {
    this.getOrderDetails(this.route.snapshot.params['id']);
    this.setMinMaxDates();
    this.isDeliveryDateValid = true;
  }

  getOrderDetails(id: number) {
    this.orderService.getOrder(id).subscribe(
      orders => {
        console.log(orders);
        this.orders = orders;
        this.orderItems$ = orders.orderItems;
      }
    );
  }

  open(content) {
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

  // TBD: NIE WYŚWIELTLA SIE TOASTR SUCCESS POMIMO ŻE DANE SĄ PRZEKAZYWANE DO SEWRWERA

  confirmDeliveryAndCloseModal(modal) {
    this.orders.deliveryDate = new Date(Date.UTC(this.deliveryDate.year, this.deliveryDate.month - 1, this.deliveryDate.day));
    this.orderService.deliveryOrder(this.orders.ordersId, this.orders.deliveryDate).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204) {
          this.toastr.success('Potwierdzono dostawę');
          modal.close();
          console.log('Delivery confirmed', response);
        } else {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
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

  cancelOrderAndCloseModal(modal) {
    this.orderService.cancelOrder(this.orders.ordersId).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204) {
          this.toastr.success('Zamówienie anulowane');
          modal.close();
          // Aktualizuj dane zamówienia tutaj
          this.updateOrderData();
        } else {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
    );
  }

  updateOrderData() {
    this.orderService.getOrder(this.orders.ordersId).subscribe(
      (updatedOrder) => {
        this.orders = updatedOrder;
      },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
    );
  }

  setMinMaxDates() {
    const today = new Date();
    const orderDate = new Date(/* Data zamówienia */);

    this.minDate = orderDate.toISOString().split('T')[0];
    this.maxDate = today.toISOString().split('T')[0];
  }

  onDeliveryDateChanged() {
    if (!this.deliveryDate) {
      this.isDeliveryDateValid = false;
      return;
    }

    const deliveryDateObj = new Date(
      this.deliveryDate.year,
      this.deliveryDate.month - 1,
      this.deliveryDate.day
    );

    const orderDateObj = new Date(this.orders.orderDate);

    if (deliveryDateObj.setHours(0, 0, 0, 0) >= orderDateObj.setHours(0, 0, 0, 0) && deliveryDateObj.setHours(0,0,0,0) <= orderDateObj.setHours(0,0,0,0)) {
      this.isDeliveryDateValid = true;
    } else {
      this.isDeliveryDateValid = false;
    }
  }
}

