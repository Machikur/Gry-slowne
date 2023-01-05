package com.scrabble;

import lombok.Data;

@Data
public class ScrabbleWordResponse {
    private final int startXPoint;
    private final int startYPoint;
    private final Direction direction;
}
