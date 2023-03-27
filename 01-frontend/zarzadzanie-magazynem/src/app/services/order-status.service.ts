import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderStatus } from '../common/order-status/order-status';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const baseUrl = theEndpoint + '/status';
@Injectable({
  providedIn: 'root'
})
export class OrderStatusService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<OrderStatus>> {
    return this.http.get<Array<OrderStatus>>(baseUrl);
  }
}
