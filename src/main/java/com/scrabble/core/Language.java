package com.scrabble.core;

import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.utill.FileUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Words provider based on language provides all letters and words used in appropriate language
 * Use resource files to keep all words in separate lines
 */

public enum Language implements WordsProvider {
    PL(
            new ScrabbleChar('a', 9, 1),
            new ScrabbleChar('e', 7, 1),
            new ScrabbleChar('i', 8, 1),
            new ScrabbleChar('n', 5, 1),
            new ScrabbleChar('o', 6, 1),
            new ScrabbleChar('r', 4, 1),
            new ScrabbleChar('s', 4, 1),
            new ScrabbleChar('w', 4, 1),
            new ScrabbleChar('z', 5, 1),

            new ScrabbleChar('c', 3, 2),
            new ScrabbleChar('d', 3, 2),
            new ScrabbleChar('k', 3, 2),
            new ScrabbleChar('l', 3, 2),
            new ScrabbleChar('m', 3, 2),
            new ScrabbleChar('p', 3, 2),
            new ScrabbleChar('t', 3, 2),
            new ScrabbleChar('y', 4, 2),


            new ScrabbleChar('b', 2, 3),
            new ScrabbleChar('g', 2, 3),
            new ScrabbleChar('h', 2, 3),
            new ScrabbleChar('j', 2, 3),
            new ScrabbleChar('ł', 2, 3),
            new ScrabbleChar('u', 2, 3),


            new ScrabbleChar('ą', 5),
            new ScrabbleChar('ę', 5),
            new ScrabbleChar('f', 5),
            new ScrabbleChar('ó', 5),
            new ScrabbleChar('ś', 5),
            new ScrabbleChar('ż', 5),

            new ScrabbleChar('ć', 6),
            new ScrabbleChar('ń', 7),
            new ScrabbleChar('ź', 9),

            new ScrabbleChar(' ', 2, 0));

    @Override
    public List<Character> getLettersPool() {
        List<Character> allChars = new LinkedList<>();
        for (ScrabbleChar scrabbleChar : charList) {
            char letter = scrabbleChar.getLetter();
            for (int i = 0; i < scrabbleChar.getQuantity(); i++) {
                allChars.add(letter);
            }
        }
        return allChars;
    }

    @Override
    public int getBasicPointsForChar(char ch) {
        return charList.stream().filter(c -> c.getLetter() == ch)
                .mapToInt(ScrabbleChar::getPoints)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s' char is not recognized", ch)));
    }

    @Override
    public List<String> getEnabledWords(int maxSize) {
        return FileUtils.loadFromResourceFile(this.name().toLowerCase()).stream()
                .filter(s -> s.length() <= maxSize)
                .collect(Collectors.toList());
    }

    final List<ScrabbleChar> charList;

    Language(ScrabbleChar... chars) {
        this.charList = Arrays.asList(chars);
    }

}
