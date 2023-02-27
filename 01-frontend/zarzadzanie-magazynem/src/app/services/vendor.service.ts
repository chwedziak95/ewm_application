import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendor } from '../common/vendor/vendor';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Vendor>> {
    return this.http.get<Array<Vendor>>('http://localhost:8080/api/v1/vendor');
  }
}
