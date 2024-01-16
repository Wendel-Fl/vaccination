import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Allergy } from '../models/allergy.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AllergyService {

  private url: string = '';

  private readonly ALLERGY_PATH: string = "allergy";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.ALLERGY_PATH}`;
  }

  public create(allergy: Allergy): Observable<Allergy> {
    return this.http.post<Allergy>(this.url, allergy);
  }

  public findById(id: number): Observable<Allergy> {
    return this.http.get<Allergy>(`${this.url}/${id}`);
  }

  public findAll(): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(`${this.url}/all`);
  }

  public update(allergy: Allergy): Observable<Allergy> {
    return this.http.put<Allergy>(this.url, allergy);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
