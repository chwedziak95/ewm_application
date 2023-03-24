import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Material } from '../common/material/material';
import { CreateMaterialPayload } from '../components/create-material/create-material.payload';
import { UpdateMaterialPayload } from '../components/create-material/update-material.payload';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  private baseUrl = "http://localhost:8080/api/v1/material"

  constructor(private http: HttpClient) { }

  createMaterial(materialPayload: CreateMaterialPayload): Observable<any>{
    return this.http.post(this.baseUrl, materialPayload, { observe: 'response' });
  }

  getAll(): Observable<Array<Material>> {
    return this.http.get<Array<Material>>(this.baseUrl);
  }

  getMaterial(theMaterialId: number): Observable<Material> {
    const materialUrl = `${this.baseUrl}/${theMaterialId}`;
    return this.http.get<Material>(materialUrl);
  }

  updateMaterial(id: number, materialPayload: UpdateMaterialPayload): Observable<any> {
    const url = `${this.baseUrl}/update/${id}`;
    return this.http.post<Material>(url, materialPayload, { observe: 'response' });
  }

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private handleError(error: any) {
    console.error(error);
    return throwError(() => error);
  }
  
  
}
