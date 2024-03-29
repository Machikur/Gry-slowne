package com.scrabble.pojo;

import lombok.Data;

@Data
public class ScrabbleWordRequest {

    private final Position startPosition;
    private final String word;
    private final Direction direction;
}
