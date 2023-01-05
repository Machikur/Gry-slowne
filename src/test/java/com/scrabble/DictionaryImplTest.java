package com.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class DictionaryImplTest {

    private static final String name0 = "janusz";
    private static final String name1 = "januszex";
    private static final String name2 = "jan";
    private static final String name3 = "janina";
    private static final String name4 = "żółć";

    @BeforeAll
    public static void mockProvider() {

    }

    @Test
    void findTheBestWord() {
        //given
        DictionaryImpl dictionary = new DictionaryImpl(createMockProvider());
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'j'),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, 'n'),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, 'u'),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT,'z' ),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT,'z'),
        };
        List<Character> playerChars = Arrays.asList('a', 's','z');

        //when
        String theBestWord = dictionary.findTheBestWord(fields, playerChars);

        //then
        Assertions.assertEquals(theBestWord, name0);
    }

    @Test
    void findTheBestWords() {
    }

    @Test
    void testFindTheBestWords() {
    }

    @Test
    void createLettersPoolForNewGame() {
    }

    private WordsProvider createMockProvider() {
        WordsProvider provider = Mockito.mock(WordsProvider.class);
        Mockito.when(provider.getEnabledWords()).thenReturn(Arrays.asList(name0, name1, name2, name3, name4));
        Mockito.when(provider.getPointsForWord(name0)).thenReturn(1);
        Mockito.when(provider.getPointsForWord(name1)).thenReturn(2);
        Mockito.when(provider.getPointsForWord(name2)).thenReturn(3);
        Mockito.when(provider.getPointsForWord(name3)).thenReturn(4);
        Mockito.when(provider.getPointsForWord(name4)).thenReturn(5);
        Mockito.when(provider.getLettersPool()).thenReturn(Arrays.asList('j', 'a', 'n', 'u', 's', 'z', 'e', 'x', 'i', 'ż', 'ó', 'ł', 'ć'));
        Mockito.when(provider.getPointsForChar(Mockito.anyChar())).thenReturn(1);
        return provider;
    }
}