import { ScrableCharProposition } from './scrable-char-proposition';

export interface ScrabbleWordProposition {
  scrabbleCharPropositions: ScrableCharProposition[];
  points: number;
}
