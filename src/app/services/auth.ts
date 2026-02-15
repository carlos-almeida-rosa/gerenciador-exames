import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LoginResponse } from '../types/LoginResponse';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  authenticated = false;
  private http = inject(HttpClient);

  // authenticate(credentials: {email: string, password: string} | undefined, callback?: () => void): void {
  //   const headers = new HttpHeaders(credentials ? {
  //     authorization: 'Basic ' + btoa(credentials.email + ":" + credentials.password)
  //   } : {});

  //   this.http.get<{email?:string}>('user', {headers}).subscribe({
  //     next: (response) => {
  //       this.authenticated = !!response?.email;
  //       if(callback){
  //         callback();
  //       }
  //     },
  //     error: () => {
  //       this.authenticated = false;
  //     }
  //   });
  // }

  login(name: string, password: string){
    return this.http.post<LoginResponse>("/login", {name, password}).pipe(
      tap((value) => {
        sessionStorage.setItem("auth-token", value.token)
        sessionStorage.setItem("username", value.name)
      })
    )
  }
  
}
