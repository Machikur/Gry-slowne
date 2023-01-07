import { Component } from '@angular/core';
import { GameData } from '../data/game-data';
import { ScrabbleChar } from '../data/scrabble-char';
import { HttpService } from '../http-service';

@Component({
  selector: 'app-player-menu',
  templateUrl: './player-menu.component.html',
  styleUrls: ['./player-menu.component.css'],
})
export class PlayerMenuComponent {
  playerChars: ScrabbleChar[] = [];

  constructor(private gameData: GameData, private http: HttpService) {
    gameData.subscribeDataChange((data) => {
      this.playerChars = data.playerChars;
    });
  }

  poolNextLetter() {
    this.http.poolNextLetter().subscribe((char) => {
      this.playerChars.push(char);
      this.http.updateData();
    });
  }

  restartTable() {
    this.http.getOrCreateTable(true);
  }
}
