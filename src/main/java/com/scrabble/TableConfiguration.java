package com.scrabble;

public interface TableConfiguration {

    int getTableSize();

   ScrabbleFieldBonus getFieldBonus(int x, int y);

}
