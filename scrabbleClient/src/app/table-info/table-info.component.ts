import { Component } from '@angular/core';
import { GameData } from '../data/game-data';
import { HttpService } from '../http-service';

@Component({
  selector: 'app-table-info',
  templateUrl: './table-info.component.html',
  styleUrls: ['./table-info.component.css'],
})
export class TableInfoComponent {
  words: string[] = [];

  constructor(http: HttpService, gameData: GameData) {
    gameData.subscribeDataChange((d) => {
      http.getPosibleWords().subscribe((data) => {
        this.words = data;
      });
    });
  }
}
