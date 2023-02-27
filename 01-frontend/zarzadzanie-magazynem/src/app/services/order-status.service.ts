import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderStatus } from '../common/order-status/order-status';

@Injectable({
  providedIn: 'root'
})
export class OrderStatusService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<OrderStatus>> {
    return this.http.get<Array<OrderStatus>>('http://localhost:8080/api/v1/status');
  }
}
