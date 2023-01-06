package com.scrabble.core;

import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleWordProposition;

import java.util.List;

public interface ScrabbleDictionary {

    /**
     * @param availableFieldsInLine available fields in line to use in ascending indexes
     * @param playerChars           lower case player chars pool available
     * @return highest scoring Proposition or {@link ScrabbleWordProposition#EMPTY_PROPOSITION} when can't find any
     */
    ScrabbleWordProposition findTheBestProposition(ScrabbleField[] availableFieldsInLine, List<Character> playerChars);

    /**
     * @param chars       lower case chars which can be used to create word
     * @param maxQuantity quantity of looking for results, negative value disable counting
     * @return List of words sorted by points
     */
    List<String> findTheBestWords(char[] chars, int maxQuantity);


    /**
     * @return lower case letters Pool enabled in single game
     */
    List<Character> getLettersPoolForNewGame();

    boolean containsWord(String word);

}
