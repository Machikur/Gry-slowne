import { Component, Input, OnInit } from '@angular/core';
import { ScrabbleChar } from '../data/scrabble-char';

@Component({
  selector: 'app-scrabble-block',
  templateUrl: './scrabble-block.component.html',
  styleUrls: ['./scrabble-block.component.css'],
})
export class ScrabbleBlockComponent implements OnInit {
  @Input()
  scrableCharOn?: ScrabbleChar;

  @Input()
  rotation: number;

  constructor() {
    const maxHalfDeg: number = 10;
    this.rotation = Math.random() * maxHalfDeg - maxHalfDeg / 2;
  }

  ngOnInit(): void {}
}
