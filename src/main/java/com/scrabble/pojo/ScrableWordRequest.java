package com.scrabble.pojo;

import lombok.Data;

@Data
public class ScrableWordRequest {

    private final Position startPosition;
    private final String word;
    private final Direction direction;
}
