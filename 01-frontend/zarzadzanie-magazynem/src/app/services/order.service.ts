import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Orders } from '../common/orders/orders';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = "http://localhost:8080/api/v1/orders";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Orders>> {
    return this.http.get<Array<Orders>>(this.baseUrl);
  }

  getOrder(ordersId: number): Observable<Orders> {
    const orderUrl = `${this.baseUrl}/${ordersId}`;
    return this.http.get<Orders>(orderUrl);
  }

  createOrder(orders: Orders): Observable<any>{
    return this.http.post<Orders>(this.baseUrl, orders, { observe: 'response' });
  }

  deliveryOrder(ordersId: number, deliveryDate: Date): Observable<any> {
    const deliveryUrl = `${this.baseUrl}/delivery/${ordersId}`;
    const deliveryData = { deliveryDate: deliveryDate.toISOString() };
    return this.http.post<Orders>(deliveryUrl, deliveryData, { observe: 'response' });
  }
  

  cancelOrder(ordersId: number): Observable<any> {
    const orderUrl = `${this.baseUrl}/cancel/${ordersId}`;
    return this.http.post<Orders>(orderUrl, {}, { observe: 'response' });
  }
  
}
