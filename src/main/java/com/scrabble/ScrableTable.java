package com.scrabble;

import java.util.List;


public class ScrableTable {

    private final ScrabbleField[][] scrabbleFields;
    private final List<Character> lettersPool;
    private ScrabbleDictionary scrabbleDictionary;

    public ScrableTable(ScrabbleDictionary dictionary, TableConfiguration c) {
        this.scrabbleDictionary = dictionary;
        this.lettersPool = scrabbleDictionary.createLettersPoolForNewGame();
        this.scrabbleFields = new ScrabbleField[c.getTableSize()][c.getTableSize()];
        for (int x = 0; x < scrabbleFields.length; x++) {
            for (int y = 0; y < scrabbleFields[x].length; y++) {
                this.scrabbleFields[x][y] = new ScrabbleField(x, y, c.getFieldBonus(x, y));
            }
        }
    }


}
