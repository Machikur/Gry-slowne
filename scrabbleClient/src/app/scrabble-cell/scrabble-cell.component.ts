import { Component, Input, OnInit } from '@angular/core';
import { ScrabbleChar } from '../data/scrabble-char';
import { ScrabbleField } from '../data/scrabble-field';
import { ScrabbleFieldBonus } from '../data/scrabble-field-bonus';

@Component({
  selector: 'app-scrabble-cell',
  templateUrl: './scrabble-cell.component.html',
  styleUrls: ['./scrabble-cell.component.css'],
})
export class ScrabbleCellComponent implements OnInit {
  @Input()
  field?: ScrabbleField;

  constructor() {}

  ngOnInit(): void {}

  getDescByBonus(bonus: ScrabbleFieldBonus) {
    // const charOn: ScrabbleChar = { letter: 'a', points: 1 };
    // if (this.field && Math.random() > 0.5) {
    //   this.field.scrabbleCharOn = charOn;
    // }

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
