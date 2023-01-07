import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ScrabbleTableData } from './data/scrabble-table-data';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  private url: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  public getOrCreateTable(): Observable<ScrabbleTableData> {
    return this.http.post<ScrabbleTableData>(
      this.url + 'PL/getOrCreateNew',
      null
    );
  }
}
