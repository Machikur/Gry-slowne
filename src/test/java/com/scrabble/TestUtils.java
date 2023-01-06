package com.scrabble;

import com.scrabble.pojo.Direction;
import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleFieldBonus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {

    static final Random random = ThreadLocalRandom.current();

    public static List<ScrabbleChar> createListFrom(Character... chars){
        return Stream.of(chars)
                .map(c-> new ScrabbleChar(c,0))
                .collect(Collectors.toList());
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
