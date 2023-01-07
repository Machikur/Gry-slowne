import { Component, OnInit } from '@angular/core';
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
  playersChar: ScrabbleChar[] = [];
  lettersLeft: number = 0;

  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
    this.createNewTable();
  }

  public createNewTable() {
    this.httpService.getOrCreateTable().subscribe((data) => {
      console.log(data);
      this.fields = data.fields;
      this.playersChar = data.playerChars;
      this.lettersLeft = data.lettersLeft;
    });
  }
}
