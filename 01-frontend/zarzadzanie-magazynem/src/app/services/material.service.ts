import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from '../common/material/material';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Material>> {
    return this.http.get<Array<Material>>('http://localhost:8080/api/v1/material');
  }
}

