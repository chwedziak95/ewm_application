import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Vendor } from '../common/vendor/vendor';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private vendorsUrl = 'http://localhost:8080/api/v1/vendor';

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Array<Vendor>> {
    return this.httpClient.get<Array<Vendor>>(this.vendorsUrl);
  }

}

