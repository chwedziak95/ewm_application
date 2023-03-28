import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from '../common/material/material';
import { CreateMaterialPayload } from '../components/create-material/create-material.payload';
import { UpdateMaterialPayload } from '../components/create-material/update-material.payload';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const baseUrl = theEndpoint + '/material';
@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  constructor(private http: HttpClient) { }

  createMaterial(materialPayload: CreateMaterialPayload): Observable<any>{
    return this.http.post(baseUrl, materialPayload, { observe: 'response' });
  }

  getAll(): Observable<Array<Material>> {
    return this.http.get<Array<Material>>(baseUrl);
  }

  getMaterial(theMaterialId: number): Observable<Material> {
    const materialUrl = `${baseUrl}/${theMaterialId}`;
    return this.http.get<Material>(materialUrl);
  }

  updateMaterial(id: number, materialPayload: UpdateMaterialPayload): Observable<any> {
    const url = `${baseUrl}/update/${id}`;
    return this.http.post<Material>(url, materialPayload, { observe: 'response' });
  }  
  
}
