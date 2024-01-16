import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Enum } from '../types/enum.type';

@Injectable({
  providedIn: 'root'
})
export class EnumService {

  private url: string = '';

  private readonly ENUM_PATH: string = "enums";

  constructor(
    private http: HttpClient
  ) { 
    this.url = `${environment.apiUrl}/${this.ENUM_PATH}`;
  }

  public getEnum(name: string): Observable<Enum[]> {
    return this.http.get<Enum[]>(`${this.url}/${name}`);
  }

}
