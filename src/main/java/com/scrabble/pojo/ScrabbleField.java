package com.scrabble.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class ScrabbleField {

    private final int x;
    private final int y;
    private boolean bonusCaught = false;
    private final ScrabbleFieldBonus scrabbleFieldBonus;
    private ScrabbleChar scrabbleCharOn;

    public ScrabbleField(int x, int y, ScrabbleFieldBonus scrabbleFieldBonus, ScrabbleChar charOn) {
        this.x = x;
        this.y = y;
        this.scrabbleFieldBonus = scrabbleFieldBonus;
        this.scrabbleCharOn = charOn;
    }

    public boolean isNextTo(ScrabbleField scrabbleField, Direction direction) {
        switch (direction) {
            case HORIZONTALLY:
                return this.y == scrabbleField.y && Math.abs(this.x - scrabbleField.x) == 1;
            case VERTICALLY:
                return this.x == scrabbleField.x && Math.abs(this.y - scrabbleField.y) == 1;
            default:
                throw new RuntimeException(String.format("%s - direction not recognized", direction));
        }
    }

    public int getIndexByDirection(Direction direction) {
        switch (direction) {
            case HORIZONTALLY:
                return x;
            case VERTICALLY:
                return y;
            default:
                throw new RuntimeException(String.format("%s - direction not recognized", direction));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrabbleField field = (ScrabbleField) o;
        return x == field.x && y == field.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
