import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Match {

  private baseUrl = 'http://localhost:8090/api/'
  
  constructor(private http: HttpClient){};
  

  createMatch(match: any): Observable<any> {
    return this.http.post(`${this.baseUrl}admin/createTournament`, match);
  }

  getAllMatch(): Observable<any> {
    return this.http.get(`${this.baseUrl}user/showAllTournament`);
  }

}
