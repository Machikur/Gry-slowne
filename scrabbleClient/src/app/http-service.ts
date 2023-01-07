import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GameData } from './data/game-data';
import { Position } from './data/position';
import { ScrabbleChar } from './data/scrabble-char';
import { ScrabbleTableData } from './data/scrabble-table-data';
import { ScrabbleWordProposition } from './data/scrabble-word-proposition';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  private url: string = 'http://localhost:8080/';

  constructor(private http: HttpClient, private gameData: GameData) {}

  public getOrCreateTable(force: boolean = false): void {
    let params = new HttpParams().set('force', force);
    this.http
      .post<ScrabbleTableData>(this.url + 'PL/getOrCreateNew', null, {
        params: params,
      })
      .subscribe((data) => this.gameData.updateData(data));
  }

  public updateData(): void {
    this.http
      .post<ScrabbleTableData>(this.url + 'PL/getData', null)
      .subscribe((data) => this.gameData.updateData(data));
  }

  public updateProposition(x: number, y: number): void {
    let params = new HttpParams().set('x', x).set('y', y);
    this.http
      .get<ScrabbleWordProposition>(this.url + 'prop', {
        params,
      })
      .subscribe((data) => this.gameData.updateScrableCharProposition(data));
  }

  public getPosibleWords(): Observable<string[]> {
    let params = new HttpParams().set('quantity', 10);
    return this.http.get<string[]>(this.url + 'theBestWords', {
      params: params,
    });
  }

  public poolNextLetter(): Observable<ScrabbleChar> {
    return this.http.post<ScrabbleChar>(this.url + 'pool', null);
  }
}
