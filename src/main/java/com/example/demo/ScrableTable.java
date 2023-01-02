package com.example.demo;

import static com.example.demo.Config.MAX_CHARS;
import static com.example.demo.Config.TABLE_SIZE;

public class ScrableTable {

    private final Field[][] fields;
    private Dictionary dictionary;
    private char[] enabledChars = new char[MAX_CHARS];

    public ScrableTable(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.fields = new Field[TABLE_SIZE][TABLE_SIZE];
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                this.fields[x][y] = new Field(x, y);
            }
        }
    }


}
