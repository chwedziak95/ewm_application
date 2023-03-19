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

  createOrder(internalOrder: InternalOrder): Observable<any>{
    return this.http.post<InternalOrder>(this.baseUrl, internalOrder);
  }

  
}
