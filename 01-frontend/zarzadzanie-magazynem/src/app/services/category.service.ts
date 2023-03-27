import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Category } from '../common/category/category';
import { CreateCategoryPayload } from '../components/create-category/create-category.payload';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const baseUrl = theEndpoint + '/category';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>(baseUrl);
  }

  createCategory(categoryPayload: CreateCategoryPayload): Observable<any>{
    return this.http.post(baseUrl, categoryPayload);
  }

  getCategory(categoryId: number): Observable<Category>{
    const categoryUrl = `${baseUrl}/${categoryId}`;
    return this.http.get<Category>(categoryUrl);
  }

  
  private handleError(error: any) {
    console.error(error);
    return throwError(() => error);
  }
  
}
