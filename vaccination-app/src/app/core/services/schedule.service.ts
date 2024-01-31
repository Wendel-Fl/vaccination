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

  private readonly CANCEL_PATH: string = "schedule-cancelled"

  private readonly CARRY_OUT_PATH: string = "schedule-carried-out";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.SCHEDULE_PATH}`;
  }

  public create(schedule: Schedule): Observable<Schedule[]> {
    return this.http.post<Schedule[]>(this.url, schedule);
  }

  public findById(id: number): Observable<Schedule> {
    return this.http.get<Schedule>(`${this.url}/${id}`);
  }

  public findAll(filter: ScheduleFilter): Observable<Schedule[]> {
    console.log(filter)
    return this.http.get<Schedule[]>(
      `${this.url}/all`
      +`?status=${filter?.status ? filter?.status : ''}`
      +`&initialDate=${filter?.initialDate ? filter?.initialDate?.toString().concat(':00') : ''}`
      +`&finalDate=${filter?.finalDate ? filter?.finalDate?.toString().concat(':00') : ''}`
    );
  }

  public update(schedule: Schedule): Observable<Schedule> {
    return this.http.put<Schedule>(this.url, schedule);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  public carryOut(schedule: Schedule): Observable<Schedule> {
    return this.http.put<Schedule>(`${this.url}/${this.CARRY_OUT_PATH}`, schedule);
  }

  public cancel(schedule: Schedule): Observable<Schedule> {
    return this.http.put<Schedule>(`${this.url}/${this.CANCEL_PATH}`, schedule);
  }

}
