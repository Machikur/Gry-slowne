package com.scrabble;

import com.scrabble.core.ScrabbleDictionaryImpl;
import com.scrabble.core.WordsProvider;
import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;
import com.scrabble.pojo.ScrabbleWordProposition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ScrabbleDictionaryImplTest {

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
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(createMockProvider(name0, name1, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('j', 1)),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('n', 1)),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('u', 1)),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
        };
        List<ScrabbleChar> playerChars = Arrays.asList(
                new ScrabbleChar('a', 1),
                new ScrabbleChar('s', 1),
                new ScrabbleChar('z', 1));

        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).toWord();

        //then
        Assertions.assertEquals(name0, theBestWord);
    }

    @Test
    void shouldFindTheBestWordHorizontally() {
        //given
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(createMockProvider(name0, name1, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('j', 1)),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('n', 1)),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('u', 1)),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
        };
        List<ScrabbleChar> playerChars = Arrays.asList(
                new ScrabbleChar('a', 1),
                new ScrabbleChar('s', 1),
                new ScrabbleChar('z', 1),
                new ScrabbleChar('e', 1),
                new ScrabbleChar('x', 1),
                new ScrabbleChar('a', 1));

        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).toWord();

        //then
        Assertions.assertEquals(name1, theBestWord);
    }

    @Test
    void shouldFindTheBestWordHorizontally2() {
        //given
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(createMockProvider(name0, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('j', 1)),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('n', 1)),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('u', 1)),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1))
        };
        List<ScrabbleChar> playerChars = Arrays.asList(
                new ScrabbleChar('a', 1),
                new ScrabbleChar('s', 1),
                new ScrabbleChar('z', 1),
                new ScrabbleChar('e', 1),
                new ScrabbleChar('x', 1));


        //when
        String theBestWord = dictionary.findTheBestProposition(fields, playerChars).toWord();

        //then
        Assertions.assertEquals(name0, theBestWord);
    }

    @Test
    void shouldFindNoWord() {
        //given
        ScrabbleDictionaryImpl dictionary = new ScrabbleDictionaryImpl(createMockProvider(name0, name2, name3, name4));
        ScrabbleField[] fields = new ScrabbleField[]{
                new ScrabbleField(0, 0, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('j', 1)),
                new ScrabbleField(0, 1, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('a', 1)),
                new ScrabbleField(0, 2, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('n', 1)),
                new ScrabbleField(0, 3, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('u', 1)),
                new ScrabbleField(0, 4, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('s', 1)),
                new ScrabbleField(0, 5, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
                new ScrabbleField(0, 6, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('e', 1)),
                new ScrabbleField(0, 7, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('x', 1)),
                new ScrabbleField(0, 8, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('i', 1)),
                new ScrabbleField(0, 9, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('x', 1)),
                new ScrabbleField(0, 10, ScrabbleFieldBonus.DEFAULT, new ScrabbleChar('z', 1)),
        };
        List<ScrabbleChar> playerChars = Arrays.asList(
                new ScrabbleChar('a', 1),
                new ScrabbleChar('s', 1),
                new ScrabbleChar('z', 1),
                new ScrabbleChar('e', 1),
                new ScrabbleChar('x', 1));

        //when
        Object theBestWord = dictionary.findTheBestProposition(fields, playerChars);

        //then
        Assertions.assertEquals(theBestWord, ScrabbleWordProposition.EMPTY_PROPOSITION);
    }

    private WordsProvider createMockProvider(String... words) {
        List<ScrabbleChar> characters = Stream.of('j', 'a', 'n', 'u', 's', 'z', 'e', 'x', 'i', 'ż', 'ó', 'ł', 'ć')
                .map(c -> new ScrabbleChar(c, 0))
                .collect(Collectors.toList());
        WordsProvider provider = Mockito.mock(WordsProvider.class);
        Mockito.when(provider.getEnabledWords(Mockito.anyInt())).thenReturn(Arrays.asList(words));
        Mockito.when(provider.getLettersPool()).thenReturn(characters);
        Mockito.when(provider.getBasicPointsForChar(Mockito.anyChar())).then(a -> (int) (Character) a.getArguments()[0]);
        return provider;
    }
}