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
    const selectedDate = this.deliveryDate.year + '-' + this.deliveryDate.month + '-' + this.deliveryDate.day;
    this.orders.deliveryDate = new Date(selectedDate);
    this.orderService.deliveryOrder(this.orders.ordersId, this.orders).subscribe(
      (data) => {
        if (data) {
          this.toastr.success('Potwierdzono dostawę');
          modal.close();
          console.log('Delivery confirmed', data);
        } else {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      });
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

}
