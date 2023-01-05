package com.scrabble;

import com.scrabble.pojo.Direction;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    static final Random random = ThreadLocalRandom.current();

    static ScrabbleField[] createTableWithIncompleteWordOn(String word, Direction direction) {

        int wordLength = word.length();
        int tableLength = wordLength * 2;
        ScrabbleField[] fields = new ScrabbleField[tableLength];
        switch (direction) {
            case VERTICALLY:
                int xPos = random.nextInt(tableLength);
                for (int y = 0; y < tableLength; y++) {
                    fields[y] = new ScrabbleField(xPos, y, ScrabbleFieldBonus.DEFAULT);
                }
                break;
            case HORIZONTALLY:
                int yPos = random.nextInt(tableLength);
                for (int x = 0; x < tableLength; x++) {
                    fields[x] = new ScrabbleField(x, yPos, ScrabbleFieldBonus.DEFAULT);
                }
                break;
        }
        int startPosition = random.nextInt(wordLength);
        boolean letterOn = false;

        int wordIndex = 0;
        for (int i = startPosition; i < startPosition + wordLength; i++) {
            if (letterOn) {
                fields[i].setLetterOn(word.charAt(wordIndex));
            }
            letterOn = !letterOn;
            wordIndex++;
        }
        return fields;
    }


    static char[] generateRandomLowerLetterArray(int length, char startChar, char maxChar) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = getRandomLowerCaseLetter(startChar, maxChar);
        }
        return result;
    }

    static char getRandomLowerCaseLetter(char minChar, char maxChar) {
        return (char) (random.nextInt(maxChar - minChar) + minChar + 1);
    }

    @SuppressWarnings("ConstantConditions")
    static String createWordFromGivenRandomChars(char[] chars) {
        if (chars.length == 0) {
            throw new IllegalArgumentException("char array can not be empty");
        }
        int length = Math.max(1, random.nextInt(chars.length));
        char[] word = new char[length];
        LinkedList<Character> enableLetters = new LinkedList<>();
        Collections.shuffle(enableLetters);
        for (char c : chars) {
            enableLetters.add(c);
        }
        for (int i = 0; i < length; i++) {
            word[i] = enableLetters.poll();
        }
        return new String(word);
    }
}
