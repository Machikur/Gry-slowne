package com.scrabble.core;

import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.utill.FileUtils;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Words provider based on language provides all letters and words used in appropriate language
 * Use resource files to keep all words in separate lines
 */

public enum Language implements WordsProvider {
    PL(
            new ScrabbleCharInit('a', 9, 1),
            new ScrabbleCharInit('e', 7, 1),
            new ScrabbleCharInit('i', 8, 1),
            new ScrabbleCharInit('n', 5, 1),
            new ScrabbleCharInit('o', 6, 1),
            new ScrabbleCharInit('r', 4, 1),
            new ScrabbleCharInit('s', 4, 1),
            new ScrabbleCharInit('w', 4, 1),
            new ScrabbleCharInit('z', 5, 1),

            new ScrabbleCharInit('c', 3, 2),
            new ScrabbleCharInit('d', 3, 2),
            new ScrabbleCharInit('k', 3, 2),
            new ScrabbleCharInit('l', 3, 2),
            new ScrabbleCharInit('m', 3, 2),
            new ScrabbleCharInit('p', 3, 2),
            new ScrabbleCharInit('t', 3, 2),
            new ScrabbleCharInit('y', 4, 2),


            new ScrabbleCharInit('b', 2, 3),
            new ScrabbleCharInit('g', 2, 3),
            new ScrabbleCharInit('h', 2, 3),
            new ScrabbleCharInit('j', 2, 3),
            new ScrabbleCharInit('ł', 2, 3),
            new ScrabbleCharInit('u', 2, 3),


            new ScrabbleCharInit('ą', 5),
            new ScrabbleCharInit('ę', 5),
            new ScrabbleCharInit('f', 5),
            new ScrabbleCharInit('ó', 5),
            new ScrabbleCharInit('ś', 5),
            new ScrabbleCharInit('ż', 5),

            new ScrabbleCharInit('ć', 6),
            new ScrabbleCharInit('ń', 7),
            new ScrabbleCharInit('ź', 9),

            new ScrabbleCharInit(' ', 2, 0),

            //todo remove from all words
            new ScrabbleCharInit('v', 0, 0),
            new ScrabbleCharInit('q', 0, 0),
            new ScrabbleCharInit('x', 0, 0)),
    ;


    @Override
    public List<ScrabbleChar> getLettersPool() {
        List<ScrabbleChar> allChars = new LinkedList<>();
        for (ScrabbleCharInit scrabbleCharInit : charList) {
            for (int i = 0; i < scrabbleCharInit.quantity; i++) {
                allChars.add(new ScrabbleChar(scrabbleCharInit.letter, scrabbleCharInit.points));
            }
        }
        return allChars;
    }

    @Override
    public int getBasicPointsForChar(char ch) {
        return charList.stream().filter(c -> c.letter == ch)
                .mapToInt(l -> l.points)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s' char is not recognized", ch)));
    }

    @Override
    public void saveNewWord(String word) {
        FileUtils.saveNewLine(this.name().toLowerCase(), word);
    }

    @Override
    public List<String> getEnabledWords(int maxSize) {
        return FileUtils.loadGzipFile(this.name().toLowerCase()).stream()
                .filter(s -> s.length() <= maxSize)
                .collect(Collectors.toList());
    }

    final List<ScrabbleCharInit> charList;

    Language(ScrabbleCharInit... chars) {
        this.charList = Arrays.asList(chars);
    }

    @AllArgsConstructor
    private static class ScrabbleCharInit {
        private final char letter;
        private final int quantity;
        private final int points;

        public ScrabbleCharInit(char letter, int points) {
            this.letter = letter;
            this.quantity = 1;
            this.points = points;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ScrabbleCharInit that = (ScrabbleCharInit) o;
            return letter == that.letter;
        }

        @Override
        public int hashCode() {
            return Objects.hash(letter);
        }
    }

}
