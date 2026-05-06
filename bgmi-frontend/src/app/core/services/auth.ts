import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  
  private baseUrl = 'http://localhost:8090/api/auth';

  constructor(private http: HttpClient){}

  register(data: any){
    return this.http.post(`${this.baseUrl}/register`, data);
  }

  login(data: any){
    return this.http.post(`${this.baseUrl}/login`, data);
  }

  isLoggedIn(): boolean {
  return !!localStorage.getItem('token'); // or your auth logic
}

}
