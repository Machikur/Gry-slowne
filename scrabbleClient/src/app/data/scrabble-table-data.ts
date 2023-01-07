import { ScrabbleChar } from './scrabble-char';
import { ScrabbleField } from './scrabble-field';

export interface ScrabbleTableData {
  fields: ScrabbleField[][];
  playerChars: ScrabbleChar[];
  lettersLeft: number;
}
