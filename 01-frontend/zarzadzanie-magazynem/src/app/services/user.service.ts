import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user/user';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const userUrl = theEndpoint + '/user'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<User>> {
    return this.http.get<Array<User>>(userUrl);
  }

  getUser(email: string): Observable<User>{
    return this.http.get<User>(userUrl + `/${email}`);
  }
}
