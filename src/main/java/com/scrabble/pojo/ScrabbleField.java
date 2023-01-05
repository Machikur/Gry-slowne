package com.scrabble.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScrabbleField {

    private final int x;
    private final int y;
    private boolean bonusCaught = false;
    private final ScrabbleFieldBonus scrabbleFieldBonus;
    private Character letterOn;

    public ScrabbleField(int x, int y, ScrabbleFieldBonus scrabbleFieldBonus, Character letterOn) {
        this.x = x;
        this.y = y;
        this.scrabbleFieldBonus = scrabbleFieldBonus;
        this.letterOn = letterOn;
    }

    public boolean isNextTo(ScrabbleField scrabbleField, Direction direction) {
        switch (direction) {
            case HORIZONTALLY:
                return this.y == scrabbleField.y && Math.abs(this.x - scrabbleField.x) == 1;
            case VERTICALLY:
                return this.x == scrabbleField.x && Math.abs(this.y - scrabbleField.y) == 1;
            default:
                throw new UnsupportedOperationException(String.format("%s - direction not recognized", direction));
        }
    }

    public int getIndexByDirection(Direction direction) {
        switch (direction) {
            case HORIZONTALLY:
                return x;
            case VERTICALLY:
                return y;
            default:
                throw new UnsupportedOperationException(String.format("%s - direction not recognized", direction));
        }
    }
}
