package com.scrabble.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ScrableTableData {

    private final ScrabbleField[][] fields;
    private final List<Character> chars;
    private final int lettersLeft;

}
