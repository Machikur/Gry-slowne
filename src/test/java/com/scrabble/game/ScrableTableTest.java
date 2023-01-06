package com.scrabble.game;

import com.scrabble.TestUtils;
import com.scrabble.config.DefaultTableConfiguration;
import com.scrabble.core.ScrabbleDictionary;
import com.scrabble.core.ScrabbleDictionaryImpl;
import com.scrabble.core.WordsProvider;
import com.scrabble.pojo.ScrabbleChar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScrableTableTest {

    @Test
    void poolNextLetterTest() {
        //given
        List<ScrabbleChar> startLetters = TestUtils.createListFrom('a', 'b', 'c', 'd');

        WordsProvider provider = mock(WordsProvider.class);
        ScrabbleDictionary dictionary = new ScrabbleDictionaryImpl(provider);
        when(dictionary.getScrabbleCharsPoolForNewGame()).thenReturn(startLetters);
        ScrableTable table = new ScrableTable(dictionary, new DefaultTableConfiguration());

        //when
        int startPlayerLettersQuantity = table.getPlayerLetters().size();

        for (int i = 0; i < startLetters.size(); i++) {
            table.poolNextLetter();
        }

        int playerLettersAfterPoolQuantity = table.getPlayerLetters().size();

        Assertions.assertEquals(0, startPlayerLettersQuantity);
        Assertions.assertEquals(4, playerLettersAfterPoolQuantity);
        Assertions.assertTrue(startLetters.containsAll(table.getPlayerLetters()));
        Assertions.assertThrows(RuntimeException.class, table::poolNextLetter);
    }

}