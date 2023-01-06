package com.scrabble.pojo;

import lombok.Data;

@Data
public class Position {
    private final int x;
    private final int y;

    public static Position of(int x, int y) {
        return new Position(x, y);
    }
}
