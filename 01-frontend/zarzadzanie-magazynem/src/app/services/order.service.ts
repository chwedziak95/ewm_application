import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Orders } from '../common/orders/orders';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Orders>> {
    return this.http.get<Array<Orders>>('http://localhost:8080/api/v1/orders');
  }
}
