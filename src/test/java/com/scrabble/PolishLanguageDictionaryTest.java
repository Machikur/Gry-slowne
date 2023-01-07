package com.scrabble;

import com.scrabble.core.Language;
import com.scrabble.core.ScrabbleDictionaryImpl;
import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;
import com.scrabble.pojo.ScrabbleWordProposition;
import com.scrabble.utill.CharUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PolishLanguageDictionaryTest {


    @Test
    void shouldFindTheBestWord() {
        //given
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(Language.PL);
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('b', 1)),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(4, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(5, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(6, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(7, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(8, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(9, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(10, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(11, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(12, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(13, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(14, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('ć', 1)),
        };
        List<ScrabbleChar> playerChars = Language.PL.getLettersPool();

        //when
        ScrabbleWordProposition theBestWord = dictionary.findTheBestProposition(fields, playerChars);
        //then

        Assertions.assertNotNull(theBestWord);
        Assertions.assertEquals(theBestWord.toWord(), "bluffujecie");
        Assertions.assertTrue(theBestWord.getPoints() > 0);
    }

    @Test
    void shouldFind10Results() {
        //given
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(Language.PL);
        List<ScrabbleChar> characters = Language.PL.getLettersPool();

        //when
        List<String> results = dictionary.findTheBestWords(CharUtils.convertToCharArray(characters), 10);
        //then

        Assertions.assertNotNull(results);
        Assertions.assertEquals(10, results.size());
    }
}
