import { Injectable } from '@angular/core';
import { Schedule } from '../models/schedule.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ScheduleFilter } from '../types/filters.type';
import moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private url: string = '';

  private readonly SCHEDULE_PATH: string = "schedule";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.SCHEDULE_PATH}`;
  }

  public create(schedule: Schedule): Observable<Schedule> {
    return this.http.post<Schedule>(this.url, schedule);
  }

  public findById(id: number): Observable<Schedule> {
    return this.http.get<Schedule>(`${this.url}/${id}`);
  }

  public findAll(filter: ScheduleFilter): Observable<Schedule[]> {
    return this.http.get<Schedule[]>(
      `${this.url}/all`
    );
  }

  public update(schedule: Schedule): Observable<Schedule> {
    return this.http.put<Schedule>(this.url, schedule);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
