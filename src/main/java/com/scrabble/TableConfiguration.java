package com.scrabble;

public interface TableConfiguration {

    int getTableSize();

   ScrabbleFieldBonus resolveFieldBonus(int x, int y);

}
