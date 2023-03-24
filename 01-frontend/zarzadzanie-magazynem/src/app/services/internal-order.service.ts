import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InternalOrder } from '../common/internal-order/internal-order';

@Injectable({
  providedIn: 'root'
})
export class InternalOrderService {

  private baseUrl = "http://localhost:8080/api/v1/internal-orders";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<InternalOrder>> {
    return this.http.get<Array<InternalOrder>>(this.baseUrl);
  }

  getOrder(id: number): Observable<any>{
    const orderUrl = `${this.baseUrl}/${id}`;
    return this.http.get<InternalOrder>(orderUrl)
  }

  createOrder(internalOrder: InternalOrder): Observable<any>{
    return this.http.post<InternalOrder>(this.baseUrl, internalOrder, { observe: 'response' });
  }

  readyOrder(id: number): Observable<any>{
    const readyUrl = `${this.baseUrl}/ready/${id}`;
    return this.http.post<InternalOrder>(readyUrl, {}, { observe: 'response' })
  }

  cancelOrder(id: number): Observable<any> {
    const cancelUrl = `${this.baseUrl}/cancel/${id}`;
    return this.http.post<InternalOrder>(cancelUrl, {}, { observe: 'response' });
  }

  
}
