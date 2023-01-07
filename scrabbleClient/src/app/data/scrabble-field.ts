import { ScrabbleChar } from './scrabble-char';
import { ScrabbleFieldBonus } from './scrabble-field-bonus';

export interface ScrabbleField {
  x: number;
  y: number;
  scrabbleFieldBonus: ScrabbleFieldBonus;
  scrabbleCharOn?: ScrabbleChar;
}
