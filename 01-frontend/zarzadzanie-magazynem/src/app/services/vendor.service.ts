import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Vendor } from '../common/vendor/vendor';
import { CreateVendorPayload } from '../components/create-vendor/create-vendor.payload';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  
  private baseUrl = 'http://localhost:8080/api/v1/vendor';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Vendor>> {
    return this.http.get<Array<Vendor>>(this.baseUrl);
  }

  createVendor(vendorPayload: CreateVendorPayload): Observable<any> {
    return this.http.post(this.baseUrl, vendorPayload);
  }

  getVendor(id: number): Observable<Vendor> {
    const vendorUrl = `${this.baseUrl}/${id}`;
    return this.http.get<Vendor>(vendorUrl);
  }

}

