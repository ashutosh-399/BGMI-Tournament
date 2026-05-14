import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Match {

  private baseUrl = environment.apiUrl;
  
  constructor(private http: HttpClient){};
  

  createMatch(match: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/admin/createTournament`, match);
  }

  getAllMatch(): Observable<any> {
    return this.http.get(`${this.baseUrl}/api/user/showAllTournament`);
  }

}
