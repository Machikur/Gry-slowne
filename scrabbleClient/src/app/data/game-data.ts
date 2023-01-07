import { Injectable } from '@angular/core';
import { Subscriber } from 'rxjs';
import { ScrabbleTableData } from './scrabble-table-data';
import { ScrabbleWordProposition } from './scrabble-word-proposition';

@Injectable()
export class GameData {
  private tableDataSubscribers: Subscriber<ScrabbleTableData>[] = [];
  private scrablePropositionSubscribers: Subscriber<ScrabbleWordProposition>[] =
    [];
  tableData: ScrabbleTableData = {
    playerChars: [],
    fields: [],
    lettersLeft: 0,
  };

  scrabbleWordProposition: ScrabbleWordProposition = {
    scrabbleCharPropositions: [],
    points: 0,
  };

  public updateScrableCharProposition(scp: ScrabbleWordProposition) {
    Object.assign(this.scrabbleWordProposition, scp);
    for (let subscriber of this.scrablePropositionSubscribers) {
      subscriber.next(this.scrabbleWordProposition);
    }
  }

  public subscribePropositionChange(
    func: (scp: ScrabbleWordProposition) => void
  ) {
    this.scrablePropositionSubscribers.push(new Subscriber(func));
  }

  public updateData(ScrabbleTableData: ScrabbleTableData) {
    Object.assign(this.tableData, ScrabbleTableData);
    for (let subscriber of this.tableDataSubscribers) {
      subscriber.next(this.tableData);
    }
  }

  public subscribeDataChange(func: (gameData: ScrabbleTableData) => void) {
    this.tableDataSubscribers.push(new Subscriber(func));
  }
}
