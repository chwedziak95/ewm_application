import { Injectable, Output } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SignupRequestPayload } from '../signup/signup-request.payload';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LoginResponse } from '../login/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

const theEndpoint = environment.ewmAppUrl;
const loginUrl = theEndpoint + '/auth/authenticate';
const refreshTokenUrl = theEndpoint + '/auth/refresh/token';
const logoutUrl = theEndpoint + '/auth/logout';
const signupUrl = theEndpoint + '/auth/signup';
const resetPasswordUrl = theEndpoint + '/auth/password-reset';
const baseUrl = theEndpoint + '/auth';
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
    private http: HttpClient,
    private localStorage: LocalStorageService
  ) {}

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    return this.http.post(
      signupUrl,
      signupRequestPayload,
      { responseType: 'text' }
    );
  }
  

  completeRegistration(token: string, password: string, confirmPassword: string): Observable<any> {
    return this.http.post<any>(`${baseUrl}/complete-registration`, { token, password, confirmPassword });
  }

  resetPassword(email: string): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(resetPasswordUrl, email,{ observe: 'response' })
  }

  login(loginRequestPayload: LoginRequestPayload, rememberMe: boolean): Observable<boolean> {
    return this.http
      .post<LoginResponse>(
        loginUrl,
        loginRequestPayload,
        { observe: 'response' }
      )
      .pipe(
        map((response) => {
          console.log("response : " + response)
          const data = response.body;
          this.localStorage.store("token", data.token);
          this.localStorage.store('refreshToken', data.refreshToken);
          this.localStorage.store('expiresAt', data.expiresAt);
          this.localStorage.store('username', data.username);
    
          // Store the rememberMe value
          this.localStorage.store('rememberMe', rememberMe);
    
          // Emit the new value for authStatus BehaviorSubject
          this._authStatus.next(true);
    
          return true;
        }),
        catchError((error) => {
          // Rzuć nowy błąd w przypadku błędu uwierzytelnienia
          if (error.status === 401 || error.status === 403) {
            return throwError(() => new Error('Nieprawidłowe poświadczenia logowania'));
          }
        
          // W przypadku innych błędów, przekaż dalej
          return throwError(error);
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
  
    return this.http
      .post<LoginResponse>(
        refreshTokenUrl,
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
    this.http
      .post(
        logoutUrl,
        this.refreshTokenPayload,
        {
          responseType: 'text',
        }
      )
      .subscribe({
        next: (_data) => {
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
