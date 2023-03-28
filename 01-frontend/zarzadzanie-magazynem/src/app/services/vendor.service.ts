import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendor } from '../common/vendor/vendor';
import { CreateVendorPayload } from '../components/create-vendor/create-vendor.payload';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const baseUrl = theEndpoint +'/vendor';
@Injectable({
  providedIn: 'root'
})
export class VendorService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Vendor>> {
    return this.http.get<Array<Vendor>>(baseUrl);
  }

  createVendor(vendorPayload: CreateVendorPayload): Observable<any> {
    return this.http.post(baseUrl, vendorPayload);
  }

  updateVendor(id: number, vendorPayload: CreateVendorPayload): Observable<any>{
    const url = `${baseUrl}/update/${id}`;
    return this.http.post<Vendor>(url, vendorPayload, { observe: 'response' });
  }

  getVendor(id: number): Observable<Vendor> {
    const vendorUrl = `${baseUrl}/${id}`;
    return this.http.get<Vendor>(vendorUrl);
  }

}

