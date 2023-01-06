package com.scrabble;

import com.scrabble.pojo.Direction;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;
import com.scrabble.utill.PatternUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class PatternUtilsTest {

    @Test
    public void shouldCreateEqualPattern() {
        //given
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 2, ScrabbleFieldBonus.DEFAULT, 'a'),
                new ScrabbleField(1, 3, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 5, ScrabbleFieldBonus.DEFAULT, 'b'),
                new ScrabbleField(1, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 10, ScrabbleFieldBonus.DEFAULT, 'c'),
                new ScrabbleField(1, 11, ScrabbleFieldBonus.DEFAULT, 'd'),
                new ScrabbleField(1, 12, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 13, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 14, ScrabbleFieldBonus.DEFAULT, 'e')
        };
        String expectedPattern = "^((\\w{0,2}a\\w{2}b\\w{4}cd\\w{2}e)|(\\w{0,2}a\\w{2}b\\w{4}cd\\w?)|(\\w{0,2}a\\w{2}b\\w{0,3})|(\\w{0,2}a\\w?)|(\\w?b\\w{4}cd\\w{2}e)|(\\w?b\\w{4}cd\\w?)|(\\w?b\\w{0,3})|(\\w{0,3}cd\\w{2}e)|(\\w{0,3}cd\\w?)|(\\w?e))$";
        //when
        Pattern pattern = PatternUtils.createPattern(fields, Direction.VERTICALLY);

        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

    @Test
    public void shouldCreateEqualPattern2() {
        //given
        ScrabbleField[] fields = new ScrabbleField[0];
        String expectedPattern = "^\\w+$";

        //when
        Pattern pattern = PatternUtils.createPattern(fields, Direction.VERTICALLY);

        //then
        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

    @Test
    public void shouldCreateEqualPattern3() {
        //given
        String expectedPattern = "^((aga\\w?))$";
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'a'),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT, 'g'),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT, 'a'),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT),
        };

        //when
        Pattern pattern = PatternUtils.createPattern(fields, Direction.recognizeDirection(fields));

        //then
        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

}