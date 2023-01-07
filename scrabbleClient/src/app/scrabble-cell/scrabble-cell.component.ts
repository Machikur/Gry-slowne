import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { GameData } from '../data/game-data';
import { ScrabbleChar } from '../data/scrabble-char';
import { ScrabbleField } from '../data/scrabble-field';
import { ScrabbleFieldBonus } from '../data/scrabble-field-bonus';
import { HttpService } from '../http-service';

@Component({
  selector: 'app-scrabble-cell',
  templateUrl: './scrabble-cell.component.html',
  styleUrls: ['./scrabble-cell.component.css'],
})
export class ScrabbleCellComponent implements OnInit {
  @Input()
  field?: ScrabbleField;
  waitingAction: any = 0;
  prop?: ScrabbleChar;

  constructor(private http: HttpService, private gameData: GameData) {}

  ngOnInit(): void {
    this.gameData.subscribePropositionChange((swp) => {
      if (this.field && !this.field.scrabbleCharOn) {
        for (let scp of swp.scrabbleCharPropositions) {
          if (scp.x == this.field.x && scp.y == this.field.y) {
            this.prop = {
              letter: scp.c,
              points: scp.points,
            };
            return;
          }
        }
      }
      this.prop = undefined;
    });
  }

  // drop(event: CdkDragDrop<string[]>) {
  //   // moveItemInArray(this.items, event.previousIndex, event.currentIndex);
  // }

  mouseEnter() {
    this.waitingAction = setTimeout(() => {
      this.getProposition();
    }, 100);
  }

  mouseLeave() {
    clearTimeout(this.waitingAction);
  }

  getProposition(): void {
    if (this.field) {
      this.http.updateProposition(this.field.x, this.field.y);
    }
  }

  getDescByBonus(bonus: ScrabbleFieldBonus) {
    switch (bonus) {
      case ScrabbleFieldBonus.DEFAULT:
        return ' ';
      case ScrabbleFieldBonus.MULTIPLY_TRIPLE_LETTER:
        return '3L';
      case ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER:
        return '2L';
      case ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD:
        return '3W';
      case ScrabbleFieldBonus.MULTIPLY_TWICE_WORD:
        return '2W';
      default:
        throw new Error(`Non-existent size in switch: ${bonus}`);
    }
  }
}
