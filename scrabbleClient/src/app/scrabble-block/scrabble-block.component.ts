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
  rotation: number;

  constructor() {
    const maxhalfDeg: number = 10;
    this.rotation = Math.random() * maxhalfDeg - maxhalfDeg / 2;
  }

  ngOnInit(): void {}

  getRandomRotation() {}
}
