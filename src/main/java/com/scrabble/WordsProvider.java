package com.scrabble;

import java.util.List;

public interface WordsProvider {

    List<String> getEnabledWords();

    List<Character> getLettersPool();

    int getPointsForChar(char c);

}
