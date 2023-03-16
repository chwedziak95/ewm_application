import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../common/category/category';
import { CreateCategoryPayload } from '../components/create-category/create-category.payload';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://localhost:8080/api/v1/category';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>(this.baseUrl);
  }

  createCategory(categoryPayload: CreateCategoryPayload): Observable<any>{
    return this.http.post(this.baseUrl, categoryPayload);
  }
}
