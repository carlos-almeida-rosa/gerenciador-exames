import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const router = inject(Router);
    const token = localStorage.getItem('auth-token');

    let authReq = req;
    if (token) {
        authReq = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${token}`)
        });
    }
    return next(authReq).pipe(
        catchError((err: any) => {
            if(err instanceof HttpErrorResponse){
                if(err.status === 401 || err.status == 403){
                    localStorage.clear();
                    router.navigate(['/login']);
                }
            }
            return throwError(() => err);
        })
    );
};