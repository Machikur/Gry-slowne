package com.scrabble.pojo;

import lombok.Data;

@Data
public class ScrabbleWordProposition implements Comparable<ScrabbleWordProposition> {

    public static final ScrabbleWordProposition EMPTY_PROPOSITION = new ScrabbleWordProposition(new Position(-1, -1), "", 0, null);

    private final Position startPosition;
    private final String word;
    private final int points;
    private final Direction direction;

    @Override
    public int compareTo(ScrabbleWordProposition o) {
        return this.points - o.points;
    }
}
