package com.scrabble;

import java.util.List;

public interface ScrabbleDictionary {

    String findTheBestWord(String regex, int quantity);

    List<String> findTheBestWords(char[] chars, int quantity);

    List<Character> createLettersPoolForNewGame();

}
