import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InternalOrder } from '../common/internal-order/internal-order';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const BASE_URL = theEndpoint + '/internal-orders';
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
    return this.http.get<InternalOrder>(this.getOrderUrl(id), { observe: 'response' });
  }

  createOrder(internalOrder: InternalOrder): Observable<HttpResponse<InternalOrder>> {
    return this.http.post<InternalOrder>(BASE_URL, internalOrder, { observe: 'response' });
  }

  readyOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    return this.http.post<InternalOrder>(`${READY_URL}/${id}`, {}, { observe: 'response' });
  }

  cancelOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    return this.http.post<InternalOrder>(`${CANCEL_URL}/${id}`, {}, { observe: 'response' });
  }

  withdrawOrder(id: number): Observable<HttpResponse<InternalOrder>> {
    return this.http.post<InternalOrder>(`${WITHDRAW_URL}/${id}`, {}, { observe: 'response' });
  }

  private getOrderUrl(id: number): string {
    return `${BASE_URL}/${id}`;
  }
}
