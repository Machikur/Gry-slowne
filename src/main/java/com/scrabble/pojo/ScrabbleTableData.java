package com.scrabble.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ScrabbleTableData {

    private final ScrabbleField[][] fields;
    private final List<ScrabbleChar> chars;
    private final int lettersLeft;

}
