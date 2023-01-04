package com.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class CharUtilsTest {

    private final Random random = ThreadLocalRandom.current();

    @Test
    void shouldFind4Letters() {
        //given
        char[] chars = new char[]{'a', 'j', 'a', 'k', 'a', 'l', 'f', 'a'};

        //when
        int result = CharUtils.countLettersNumberInArray(chars, 'a');

        //then
        Assertions.assertEquals(4, result);
    }

    @Test
    void shouldFind0Letters() {
        //given
        char[] chars = new char[]{'a', 'j', 'a', 'k', 'a', 'l', 'f', 'a'};

        //when
        int result = CharUtils.countLettersNumberInArray(chars, 'g');

        //then
        Assertions.assertEquals(0, result);
    }



    @Test
    void shouldIncludeLackAndBuild() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j','k'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("klif");
        int maxLackOfChars = 1;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);
    }

    @Test
    void shouldIncludeLackAndBuild2() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("klif");
        int maxLackOfChars = 3;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);
    }

    @Test
    void shouldIncludeLackAndNotBuild() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("klif");
        int maxLackOfChars = 2;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertFalse(canBeBuild);
    }

    @Test
    void shouldBuildWordFromGivenChars() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("klif");
        int maxLackOfChars = 0;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);
    }

    @Test
    void shouldBuildWordFromGivenChars2() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l', 'l', 'l', 'l'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("kliflll");
        int maxLackOfChars = 0;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);

    }

    @Test
    void shouldBuildWordFromGivenChars3() {
        //given
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l', 'l', 'l', 'l'};
        ScrabbleWord scrabbleWord = new ScrabbleWord("klifllls");
        int maxLackOfChars = 1;

        //when
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, chars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);
    }

    @RepeatedTest(200)
    void shouldBuildWordFromRandomLetters() {
        //given
        char[] availableChars = generateRandomLowerLetterArray(random.nextInt(50) + 10, 'a', 'z');
        ScrabbleWord scrabbleWord = new ScrabbleWord(createWordFromGivenRandomChars(availableChars));
        int maxLackOfChars = 0;

        //when
        Arrays.sort(availableChars);
        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, availableChars, maxLackOfChars);

        //then
        Assertions.assertTrue(canBeBuild);
    }

    @RepeatedTest(200)
    void shouldNotBuildWordFromGivenLetters() {
        //given
        char[] availableChars = generateRandomLowerLetterArray(random.nextInt(50) + 10, 'a', 'g');
        char[] scrableChars = generateRandomLowerLetterArray(random.nextInt(50) + 10, 'h', 'z');
        ScrabbleWord scrabbleWord = new ScrabbleWord(createWordFromGivenRandomChars(scrableChars));
        int maxLackOfChars = 0;

        //when
        Arrays.sort(availableChars);

        boolean canBeBuild = CharUtils.canBeBuildScrableWordFrom(scrabbleWord, availableChars, maxLackOfChars);

        //then
        Assertions.assertFalse(canBeBuild);
    }

    @SuppressWarnings("ConstantConditions")
    private String createWordFromGivenRandomChars(char[] chars) {
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

    private char[] generateRandomLowerLetterArray(int length, char startChar, char maxChar) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = getRandomLowerCaseLetter(startChar, maxChar);
        }
        return result;
    }

    private char getRandomLowerCaseLetter(char minChar, char maxChar) {
        return (char) (random.nextInt(maxChar - minChar) + minChar + 1);
    }

}