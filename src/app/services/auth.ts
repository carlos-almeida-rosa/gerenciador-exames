import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  authenticated = false;
  private http = inject(HttpClient);

  authenticate(credentials: {email: string, password: string} | undefined, callback?: () => void): void {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.email + ":" + credentials.password)
    } : {});

    this.http.get<{email?:string}>('user', {headers}).subscribe({
      next: (response) => {
        this.authenticated = !!response?.email;
        if(callback){
          callback();
        }
      },
      error: () => {
        this.authenticated = false;
      }
    });
  }
  
}
