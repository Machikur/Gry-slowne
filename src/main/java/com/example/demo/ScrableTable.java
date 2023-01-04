package com.example.demo;

import java.util.List;

import static com.example.demo.Config.TABLE_SIZE;

public class ScrableTable {

    private final Field[][] fields;
    private final List<Character> lettersPool;
    private ScrabbleDictionary scrabbleDictionary;

    public ScrableTable(ScrabbleDictionary dictionary) {
        this.scrabbleDictionary = dictionary;
        this.lettersPool = scrabbleDictionary.createLettersPoolForNewGame();
        this.fields = new Field[TABLE_SIZE][TABLE_SIZE];
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                this.fields[x][y] = new Field(x, y);
            }
        }
    }


}
