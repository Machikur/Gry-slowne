package com.scrabble;

import java.util.List;

public interface ScrabbleDictionary {

    String findTheBestWord(ScrabbleField[] fieldsInLine, List<Character> playerChars);

    List<String> findTheBestWords(char[] chars, int quantity);

    List<Character> createLettersPoolForNewGame();

}
