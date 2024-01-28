import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Vaccine } from '../models/vaccine.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VaccineService {

  private url: string = '';

  private readonly VACCINE_PATH: string = "vaccination";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.VACCINE_PATH}`;
  }

  public create(vaccine: Vaccine): Observable<Vaccine> {
    return this.http.post<Vaccine>(this.url, vaccine);
  }

  public findById(id: number): Observable<Vaccine> {
    return this.http.get<Vaccine>(`${this.url}/${id}`);
  }

  public findAll(): Observable<Vaccine[]> {
    return this.http.get<Vaccine[]>(`${this.url}/all`);
  }

  public update(vaccine: Vaccine): Observable<Vaccine> {
    return this.http.put<Vaccine>(this.url, vaccine);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
