package com.scrabble.core;

import java.util.List;

public interface WordsProvider {

    /**
     * @param maxSize maxSizeOfWord
     * @return all correct lower case words available in game
     */
    List<String> getEnabledWords(int maxSize);

    /**
     * @return lower case letters available in game including quantity
     */
    List<Character> getLettersPool();

    /**
     * @param c searching lower case char
     * @return basic points for char
     * @throws IllegalArgumentException if char is not available in game
     */
    int getBasicPointsForChar(char c);

}
