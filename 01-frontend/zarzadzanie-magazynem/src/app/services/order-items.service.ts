import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderItems } from '../common/order-items/order-items';

@Injectable({
  providedIn: 'root'
})
export class OrderItemsService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<OrderItems>> {
    return this.http.get<Array<OrderItems>>('http://localhost:8080/api/v1/order-items');
  }
}
