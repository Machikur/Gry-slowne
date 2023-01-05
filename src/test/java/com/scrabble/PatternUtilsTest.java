package com.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class PatternUtilsTest {

    @Test
    public void shouldCreateEqualPattern() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(1, 2, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 5, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 10, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 11, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 14, ScrabbleFieldBonus.DEFAULT)
        };
        for (int i = 0; i < chars.length; i++) {
            fields[i].setLetterOn(chars[i]);
        }
        String expectedPattern = "^((\\w{0,2}a\\w{2}b\\w{4}cd\\w{2}e)|(\\w{0,2}a\\w{2}b\\w{4}cd\\w{0,2})|(\\w{0,2}a\\w{2}b\\w{0,4})|(\\w{0,2}a\\w{0,2})|(\\w{0,2}b\\w{4}cd\\w{2}e)|(\\w{0,2}b\\w{4}cd\\w{0,2})|(\\w{0,2}b\\w{0,4})|(\\w{0,4}cd\\w{2}e)|(\\w{0,4}cd\\w{0,2})|(\\w{0,2}e))$";

        //when
        Pattern pattern = PatternUtils.createPattern(fields);

        //then
        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

    @Test
    public void shouldCreateEqualPattern2() {
        //given
        ScrabbleField[] fields = new ScrabbleField[0];
        String expectedPattern = "^\\w+$";

        //when
        Pattern pattern = PatternUtils.createPattern(fields);

        //then
        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

    @Test
    public void shouldCreateEqualPattern3() {
        //given
        char[] chars = new char[]{'a', 'g', 'a'};
        String expectedPattern = "^((aga\\w{0,12}))$";
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT),
        };
        for (int i = 0; i < chars.length; i++) {
            fields[i].setLetterOn(chars[i]);
        }

        //when
        Pattern pattern = PatternUtils.createPattern(fields);


        //then
        Assertions.assertEquals(expectedPattern, pattern.pattern());
    }

}