import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Auth {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient){}

  register(data: any){
    return this.http.post(`${this.baseUrl}/api/auth/register`, data);
  }

  login(data: any){
    return this.http.post(`${this.baseUrl}/api/auth/login`, data);
  }

  isLoggedIn(): boolean {
  return !!localStorage.getItem('token'); // or your auth logic
}

}
