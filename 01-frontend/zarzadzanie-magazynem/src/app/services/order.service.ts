import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Orders } from '../common/orders/orders';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const baseUrl = theEndpoint + '/orders';
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Orders>> {
    return this.http.get<Array<Orders>>(baseUrl);
  }

  getAllByUser(id: number): Observable<Array<Orders>> {
    const url = `${baseUrl}/by-user/${id}`;
    return this.http.get<Array<Orders>>(url);
  }

  getOrder(ordersId: number): Observable<Orders> {
    const orderUrl = `${baseUrl}/${ordersId}`;
    return this.http.get<Orders>(orderUrl);
  }

  createOrder(orders: Orders): Observable<any>{
    return this.http.post<Orders>(baseUrl, orders, { observe: 'response' });
  }

  deliveryOrder(ordersId: number, deliveryDate: Date): Observable<any> {
    const deliveryUrl = `${baseUrl}/delivery/${ordersId}`;
    const deliveryData = { deliveryDate: deliveryDate.toISOString() };
    return this.http.post<Orders>(deliveryUrl, deliveryData, { observe: 'response' });
  }
  

  cancelOrder(ordersId: number): Observable<any> {
    const orderUrl = `${baseUrl}/cancel/${ordersId}`;
    return this.http.post<Orders>(orderUrl, {}, { observe: 'response' });
  }
  
}
