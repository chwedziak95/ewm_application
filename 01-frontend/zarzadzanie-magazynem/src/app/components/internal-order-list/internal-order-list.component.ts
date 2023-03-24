import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { InternalOrder } from 'src/app/common/internal-order/internal-order';
import { InternalOrderService } from 'src/app/services/internal-order.service';

@Component({
  selector: 'app-internal-order-list',
  templateUrl: './internal-order-list.component.html',
  styleUrls: ['./internal-order-list.component.css']
})
export class InternalOrderListComponent implements OnInit {

  internalOrders$: Array<InternalOrder> = [];
  internalOrdersNew$: Array<InternalOrder> = [];
  sortOrder = 'asc';
  closeResult = '';
  internalOrder: InternalOrder;

  constructor(
    private internalOrderService: InternalOrderService,
    private modalService: NgbModal,
    private toastr: ToastrService
  ) {
    this.internalOrderService.getAll().subscribe(internalOrders => {
      this.internalOrders$ = internalOrders;
      this.internalOrdersNew$ = internalOrders.filter(order => order.status?.statusId === 1 || order.status?.statusId === 4);
    })
  }

  ngOnInit() {
  }

  getUniqueStatusNames(): string[] {
    const statusNames = this.internalOrders$.map(internalOrders => internalOrders.status?.name);
    return Array.from(new Set(statusNames));
  }

  toggleSortOrder() {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
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


  ready(modal) {
    this.internalOrderService.readyOrder(this.internalOrder.internalOrderId).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204 || response.status === 201) {
          this.toastr.success('Potwierdzono przygotowanie zamówienia');
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

  withdraw(modal) {
    this.internalOrderService.withdrawOrder(this.internalOrder.internalOrderId).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204 || response.status === 201) {
          this.toastr.success('Potwierdzono wydanie zamówienia');
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
    this.internalOrderService.getAll().subscribe(internalOrders => {
      this.internalOrders$ = internalOrders;
      this.internalOrdersNew$ = internalOrders.filter(order => order.status?.statusId === 1 || order.status?.statusId === 4);
    },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
    );
  }

}
