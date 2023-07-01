import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private loginService: LoginService,
    private router: Router) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log('error intercept');

    return next.handle(request).pipe(catchError(err => {
      console.log('error' + err);
      
      if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.loginService.logout();
        this.router.navigate(['/login']);
      }

      const error = err.error || err.statusText;
      return throwError(error);
    }))
  }
}

export const errorInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
  }
];
