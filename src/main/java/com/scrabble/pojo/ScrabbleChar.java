package com.scrabble.pojo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ScrabbleChar {

    private final char letter;
    private final int points;

    public ScrabbleChar(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrabbleChar that = (ScrabbleChar) o;
        return letter == that.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    @Override
    public String toString() {
        return "" + letter;
    }
}
