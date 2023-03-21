import { Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignupRequestPayload } from '../signup/signup-request.payload';
import { map, Observable, tap, throwError } from 'rxjs';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LoginResponse } from '../login/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName(),
  };

  // Add the BehaviorSubject
  private _authStatus = new BehaviorSubject<boolean>(this.isLoggedIn());
  authStatus$ = this._authStatus.asObservable();

  constructor(
    private httpClient: HttpClient,
    private localStorage: LocalStorageService
  ) {}

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    return this.httpClient.post(
      'http://localhost:8080/api/v1/auth/signup',
      signupRequestPayload,
      { responseType: 'text' }
    );
  }

  login(loginRequestPayload: LoginRequestPayload, rememberMe: boolean): Observable<boolean> {
    return this.httpClient
      .post<LoginResponse>(
        'http://localhost:8080/api/v1/auth/authenticate',
        loginRequestPayload
      )
      .pipe(
        map((data) => {
          this.localStorage.store("token", data.token);
          this.localStorage.store('refreshToken', data.refreshToken);
          this.localStorage.store('expiresAt', data.expiresAt);
          this.localStorage.store('username', data.username);
  
          // Store the rememberMe value
          this.localStorage.store('rememberMe', rememberMe);
  
          // Emit the new value for authStatus BehaviorSubject
          this._authStatus.next(true);
  
          return true;
        })
      );
  }
  

  getToken() {
    return this.localStorage.retrieve('token');
  }

  refreshToken() {
    this.refreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName(),
    };
  
    return this.httpClient
      .post<LoginResponse>(
        'http://localhost:8080/api/v1/auth/refresh/token',
        this.refreshTokenPayload
      )
      .pipe(
        tap((response) => {
          this.localStorage.clear('token');
          this.localStorage.clear('expiresAt');
  
          this.localStorage.store('token', response.token);
          this.localStorage.store('expiresAt', response.expiresAt);
        })
      );
  }
  

  logout() {
    this.httpClient
      .post(
        'http://localhost:8080/api/v1/auth/logout',
        this.refreshTokenPayload,
        {
          responseType: 'text',
        }
      )
      .subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => {
          this.localStorage.clear('token');
          this.localStorage.clear('username');
          this.localStorage.clear('refreshToken');
          this.localStorage.clear('expiresAt');
  
          this._authStatus.next(false);
        }
      });
  }
  

  getUserName() {
    return this.localStorage.retrieve('username');
  }
  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getToken() != null;
  }
  
}
