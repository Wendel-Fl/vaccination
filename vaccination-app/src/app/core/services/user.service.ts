import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = '';

  private readonly USER_PATH: string = "user";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.USER_PATH}`;
  }

  public create(user: User): Observable<User> {
    return this.http.post<User>(this.url, user);
  }

  public findById(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${id}`);
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  public update(user: User): Observable<User> {
    return this.http.put<User>(this.url, user);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
