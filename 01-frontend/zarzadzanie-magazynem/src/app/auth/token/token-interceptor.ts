import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  filter,
  Observable,
  switchMap,
  take,
  throwError,
} from 'rxjs';
import { LoginResponse } from '../login/login-response.payload';
import { AuthService } from '../shared/auth.service';

@Injectable({
  providedIn: 'root',
})
export class TokenInterceptor implements HttpInterceptor {
  isTokenRefreshing = false;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor(public authService: AuthService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Login, refresh and logout are public; never send (or refresh) a JWT for them,
    // otherwise a failing refresh call would wait on itself forever.
    if (req.url.includes('/auth/')) {
      return next.handle(req);
    }
    const token = this.authService.getToken();
    if (token) {
      // HttpRequest is immutable: addToken returns a new request that must be used.
      req = this.addToken(req, token);
    }
    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 403 && token) {
          return this.handleAuthErrors(req, next);
        } else {
          return throwError(() => error);
        }
      })
    );
  }

  private handleAuthErrors(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!this.isTokenRefreshing) {
      this.isTokenRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.authService.refreshToken().pipe(
        catchError((error) => {
          // Refresh token rejected: reset so later requests can retry, and end the session.
          this.isTokenRefreshing = false;
          this.authService.logout();
          return throwError(() => error);
        }),
        switchMap((refreshTokenResponse: LoginResponse) => {
          this.isTokenRefreshing = false;
          this.refreshTokenSubject.next(
            refreshTokenResponse.token
          );
          return next.handle(
            this.addToken(req, refreshTokenResponse.token)
          );
        })
      );
    } else {
      return this.refreshTokenSubject.pipe(
        filter((result) => result !== null),
        take(1),
        switchMap((res) => {
          return next.handle(this.addToken(req, this.authService.getToken()));
        })
      );
    }
  }

  addToken(req: HttpRequest<any>, token: any) {
    return req.clone({
      headers: req.headers.set('Authorization', `Bearer `+ token),
    });
  }
}