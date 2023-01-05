package com.scrabble;

import com.scrabble.pojo.ScrabbleWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

class ScrabbleWordTest {

    @RepeatedTest(200)
    public void shouldSortCharsAscending() {
        char[] randomArray = TestUtils.generateRandomLowerLetterArray(50, 'a', 'z');
        ScrabbleWord scrabbleWord = new ScrabbleWord(new String(randomArray));

        boolean isAscending = true;
        char[] sortedWordArray = scrabbleWord.getSortedChars();
        char previous = sortedWordArray[0];
        for (int i = 1; i < sortedWordArray.length; i++) {
            char current = sortedWordArray[i];
            if (previous > current) {
                isAscending = false;
            }
            previous = current;
        }
        Assertions.assertTrue(isAscending);
    }

}