import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { InternalOrder } from '../common/internal-order/internal-order';

const BASE_URL = 'http://localhost:8080/api/v1/internal-orders';
const CANCEL_URL = `${BASE_URL}/cancel`;
const READY_URL = `${BASE_URL}/ready`;
const WITHDRAW_URL = `${BASE_URL}/withdraw`;
const BY_USER_URL = `${BASE_URL}/by-user`;

@Injectable({
  providedIn: 'root',
})
export class InternalOrderService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Array<InternalOrder>> {
    return this.http.get<Array<InternalOrder>>(BASE_URL);
  }

  getAllByUser(id: number): Observable<Array<InternalOrder>> {
    const url = `${BY_USER_URL}/${id}`; 
    return this.http.get<Array<InternalOrder>>(url);
  }

  getOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    const orderUrl = this.getOrderUrl(id);
    return this.http.get<InternalOrder>(orderUrl, { observe: 'response' }).pipe(
      catchError((error: any): Observable<HttpResponse<InternalOrder>> => {
        console.log('Error getting order', error);
        return error;
      })
    );
  }

  createOrder(internalOrder: InternalOrder): Observable<HttpResponse<InternalOrder>> {
    return this.http.post<InternalOrder>(BASE_URL, internalOrder, { observe: 'response' }).pipe(
      catchError((error: any): Observable<HttpResponse<InternalOrder>> => {
        console.log('Error creating order', error);
        return error;
      })
    );
  }

  readyOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    const readyUrl = `${READY_URL}/${id}`;
    return this.http.post<InternalOrder>(readyUrl, {}, { observe: 'response' }).pipe(
      catchError((error: any): Observable<HttpResponse<InternalOrder>> => {
        console.log('Error marking order as ready', error);
        return error;
      })
    );
  }

  cancelOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    const cancelUrl = `${CANCEL_URL}/${id}`;
    return this.http.post<InternalOrder>(cancelUrl, {}, { observe: 'response' }).pipe(
      catchError((error: any): Observable<HttpResponse<InternalOrder>> => {
        console.log('Error cancelling order', error);
        return error;
      })
    );
  }

  withdrawOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    const withdrawUrl = `${WITHDRAW_URL}/${id}`;
    return this.http.post<InternalOrder>(withdrawUrl, {}, { observe: 'response' }).pipe(
      catchError((error: any): Observable<HttpResponse<InternalOrder>> => {
        console.log('Error withdrawing order', error);
        return error;
      })
    );
  }

  private getOrderUrl(id: number): string {
    return `${BASE_URL}/${id}`;
  }
}
