package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public enum Language implements LetterProvider {
    PL(new ArrayList<ScrabbleChar>() {
        {
            add(new ScrabbleChar('a', 9, 1));
            add(new ScrabbleChar('e', 7, 1));
            add(new ScrabbleChar('i', 8, 1));
            add(new ScrabbleChar('n', 5, 1));
            add(new ScrabbleChar('o', 6, 1));
            add(new ScrabbleChar('r', 4, 1));
            add(new ScrabbleChar('s', 4, 1));
            add(new ScrabbleChar('w', 4, 1));
            add(new ScrabbleChar('z', 5, 1));

            add(new ScrabbleChar('c', 3, 2));
            add(new ScrabbleChar('d', 3, 2));
            add(new ScrabbleChar('k', 3, 2));
            add(new ScrabbleChar('l', 3, 2));
            add(new ScrabbleChar('m', 3, 2));
            add(new ScrabbleChar('p', 3, 2));
            add(new ScrabbleChar('t', 3, 2));
            add(new ScrabbleChar('y', 4, 2));


            add(new ScrabbleChar('b', 2, 3));
            add(new ScrabbleChar('g', 2, 3));
            add(new ScrabbleChar('h', 2, 3));
            add(new ScrabbleChar('j', 2, 3));
            add(new ScrabbleChar('ł', 2, 3));
            add(new ScrabbleChar('u', 2, 3));


            add(new ScrabbleChar('ą', 5));
            add(new ScrabbleChar('ę', 5));
            add(new ScrabbleChar('f', 5));
            add(new ScrabbleChar('ó', 5));
            add(new ScrabbleChar('ś', 5));
            add(new ScrabbleChar('ż', 5));

            add(new ScrabbleChar('ć', 6));
            add(new ScrabbleChar('ń', 7));
            add(new ScrabbleChar('ź', 9));

        }
    }) {
        @Override
        public char[] getEnabledChars() {
            StringBuilder builder = new StringBuilder();
            for (ScrabbleChar scrabbleChar : charList) {
                char letter = scrabbleChar.getLetter();
                for (int i = 0; i < scrabbleChar.getQuantity(); i++) {
                    builder.append(letter);
                }
            }
            return builder.toString().toCharArray();
        }

        @Override
        public int getPointsForChar(char ch) {
            return charList.stream().filter(
                            c -> c.getLetter() == ch)
                    .mapToInt(ScrabbleChar::getPoints)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("'%s' char is not recognized", ch)));
        }
    };

    final List<ScrabbleChar> charList;


    Language(List<ScrabbleChar> charList) {
        this.charList = charList;
    }

}
