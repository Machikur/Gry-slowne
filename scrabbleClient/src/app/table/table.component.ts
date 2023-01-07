import { Component, OnInit } from '@angular/core';
import { GameData } from '../data/game-data';
import { ScrabbleChar } from '../data/scrabble-char';
import { ScrabbleField } from '../data/scrabble-field';
import { HttpService } from '../http-service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  fields?: ScrabbleField[][];

  constructor(private http: HttpService, gameData: GameData) {
    gameData.subscribeDataChange((data) => {
      this.fields = data.fields;
    });
  }

  ngOnInit(): void {
    this.http.getOrCreateTable();
  }
}
