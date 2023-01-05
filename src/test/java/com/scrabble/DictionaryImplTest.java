package com.scrabble;

import com.scrabble.core.DictionaryImpl;
import com.scrabble.core.WordsProvider;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

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
    void shouldFindTheBestWordVertically() {
        //given
        DictionaryImpl dictionary = new DictionaryImpl(createMockProvider(name0, name1, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'j'),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, 'n'),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, 'u'),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT, 'z'),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT, 'z'),
        };
        List<Character> playerChars = Arrays.asList('a', 's', 'z');

        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).getWord();

        //then
        Assertions.assertEquals(name0, theBestWord);
    }

    @Test
    void shouldFindTheBestWordHorizontally() {
        //given
        DictionaryImpl dictionary = new DictionaryImpl(createMockProvider(name0, name1, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'j'),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT, 'n'),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT, 'u'),
                new ScrabbleField(4, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(5, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
                new ScrabbleField(6, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(7, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(8, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(9, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(10, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
        };
        List<Character> playerChars = Arrays.asList('a', 's', 'z', 'e', 'x');

        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).getWord();

        //then
        Assertions.assertEquals(name1, theBestWord);
    }

    @Test
    void shouldFindTheBestWordHorizontally2() {
        //given
        DictionaryImpl dictionary = new DictionaryImpl(createMockProvider(name0, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'j'),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT, 'n'),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT, 'u'),
                new ScrabbleField(4, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(5, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
                new ScrabbleField(6, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(7, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(8, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(9, 0, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(10, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
        };
        List<Character> playerChars = Arrays.asList('a', 's', 'z', 'e', 'x');

        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).getWord();

        //then
        Assertions.assertEquals(name0, theBestWord);
    }

    @Test
    void shouldFindNoWord() {
        //given
        DictionaryImpl dictionary = new DictionaryImpl(createMockProvider(name0, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, 'j'),
                new ScrabbleField(1, 0, ScrabbleFieldBonus.DEFAULT, 'a'),
                new ScrabbleField(2, 0, ScrabbleFieldBonus.DEFAULT, 'n'),
                new ScrabbleField(3, 0, ScrabbleFieldBonus.DEFAULT, 'u'),
                new ScrabbleField(4, 0, ScrabbleFieldBonus.DEFAULT, 's'),
                new ScrabbleField(5, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
                new ScrabbleField(6, 0, ScrabbleFieldBonus.DEFAULT, 'e'),
                new ScrabbleField(7, 0, ScrabbleFieldBonus.DEFAULT, 'x'),
                new ScrabbleField(8, 0, ScrabbleFieldBonus.DEFAULT, 'i'),
                new ScrabbleField(9, 0, ScrabbleFieldBonus.DEFAULT, 'x'),
                new ScrabbleField(10, 0, ScrabbleFieldBonus.DEFAULT, 'z'),
        };
        List<Character> playerChars = Arrays.asList('a', 's', 'z', 'e', 'x');

        //when
        Object theBestWord = dictionary.findTheBestProposition(fields, playerChars);

        //then
        Assertions.assertNull(theBestWord);
    }

    private WordsProvider createMockProvider(String... words) {
        WordsProvider provider = Mockito.mock(WordsProvider.class);
        Mockito.when(provider.getEnabledWords(Mockito.anyInt())).thenReturn(Arrays.asList(words));
        Mockito.when(provider.getLettersPool()).thenReturn(Arrays.asList('j', 'a', 'n', 'u', 's', 'z', 'e', 'x', 'i', 'ż', 'ó', 'ł', 'ć'));
        Mockito.when(provider.getBasicPointsForChar(Mockito.anyChar())).then(a -> (int) (Character) a.getArguments()[0]);
        return provider;
    }
}