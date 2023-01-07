package com.scrabble.pojo;

import lombok.Data;

@Data
public class ScrabbleWordProposition implements Comparable<ScrabbleWordProposition> {

    public static final ScrabbleWordProposition EMPTY_PROPOSITION = new ScrabbleWordProposition(new ScrabbleCharProposition[0], 0);
    private final ScrabbleCharProposition[] scrabbleCharPropositions;

    public ScrabbleWordProposition(ScrabbleCharProposition[] scrabbleCharPropositions, int points) {
        this.scrabbleCharPropositions = scrabbleCharPropositions;
        this.points = points;
    }

    private final int points;

    public String toWord() {
        char[] chars = new char[scrabbleCharPropositions.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = scrabbleCharPropositions[i].getC();
        }
        return new String(chars);
    }

    @Override
    public int compareTo(ScrabbleWordProposition o) {
        return this.points - o.points;
    }
}
