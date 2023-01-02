package com.example.demo;

public class ScrabbleChar {

    private final char letter;
    private final int quantity;
    private final int points;

    public ScrabbleChar(char letter, int quantity, int points) {
        this.letter = letter;
        this.quantity = quantity;
        this.points = points;
    }

    public ScrabbleChar(char letter, int points) {
        this.letter = letter;
        this.quantity = 1;
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPoints() {
        return points;
    }
}
