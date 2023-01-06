package com.scrabble.pojo;

public enum Direction {
    VERTICALLY,
    HORIZONTALLY;

    public static Direction recognizeDirection(ScrabbleField[] fields) {
        if (fields.length < 1) {
            throw new IllegalArgumentException("Array must contains at least two elements");
        }
        boolean xFlow = true;
        boolean yFlow = true;
        ScrabbleField previous = fields[0];
        for (int i = 1; i < fields.length; i++) {
            ScrabbleField current = fields[i];
            if (yFlow && !previous.isNextTo(current, Direction.VERTICALLY)) {
                yFlow = false;
            }
            if (xFlow && !previous.isNextTo(current, Direction.HORIZONTALLY)) {
                xFlow = false;
            }
            previous = current;
        }
        if (xFlow) return Direction.HORIZONTALLY;
        if (yFlow) return Direction.VERTICALLY;
        throw new RuntimeException("Unknown direction");
    }
}
