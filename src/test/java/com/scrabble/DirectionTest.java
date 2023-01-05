package com.scrabble;

import com.scrabble.pojo.Direction;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void shouldRecognizeVertically() {
        //given
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(1, 2, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 3, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 5, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 7, ScrabbleFieldBonus.DEFAULT),
        };

        //when
        Direction direction = Direction.recognizeDirection(fields);

        //then
        Assertions.assertEquals(direction, Direction.VERTICALLY);
    }

    @Test
    void shouldRecognizeHorizontally() {
        //given
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(4, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(5, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(6, 0, ScrabbleFieldBonus.DEFAULT),
        };

        //when
        Direction direction = Direction.recognizeDirection(fields);

        //then
        Assertions.assertEquals(direction, Direction.HORIZONTALLY);
    }

    @Test
    void shouldThrowException() {
        //given
        ScrabbleField[] fields = new ScrabbleField[0];

        //when && then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Direction.recognizeDirection(fields));
    }

    @Test
    void shouldThrowException2() {
//given
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 3, ScrabbleFieldBonus.DEFAULT)
        };

        //when && then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> Direction.recognizeDirection(fields));
    }
}