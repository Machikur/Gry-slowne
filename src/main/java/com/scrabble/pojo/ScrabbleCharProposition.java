package com.scrabble.pojo;

import lombok.Data;

@Data
public class ScrabbleCharProposition {
    private final int x;
    private final int y;
    private final char c;
    private final int points;

}
