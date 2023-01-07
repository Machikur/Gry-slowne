package com.scrabble.config;

import com.scrabble.pojo.ScrabbleFieldBonus;

public interface TableConfiguration {
    /**
     * @return table size used for creating game
     */
    int getTableSize();


    int getLettersOnStart();

    /**
     * @param x field position
     * @param y field position
     * @return ScrabbleFieldBonus assigned to given position
     */
    ScrabbleFieldBonus resolveFieldBonus(int x, int y);

}
