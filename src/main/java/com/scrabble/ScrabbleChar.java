package com.scrabble;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class ScrabbleChar {

    private final char letter;
    private final int quantity;
    private final int points;


    public ScrabbleChar(char letter, int points) {
        this.letter = letter;
        this.quantity = 1;
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
}
