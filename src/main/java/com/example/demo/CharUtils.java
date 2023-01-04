package com.example.demo;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

import static com.example.demo.Config.BLANK_LETTER;

@UtilityClass
public class CharUtils {

    public static int countLetters(char[] letters, char wantedLetter) {
        int result = 0;
        for (char c : letters) {
            if (c == wantedLetter) {
                result++;
            }
        }
        return result;
    }

    public static boolean canBeBuildFrom(ScrableWord scrableWord, char[] sortedLowerCaseChars, int maxLack) {
        int currentLack = 0;
        char[] scrableWordChars = scrableWord.getSortedChars();

        int givenCharsIndex = 0;

        main:
        for (int i = 0; i < scrableWord.getLength(); i++) {
            char wantedChar = scrableWordChars[i];
            char checkedChar = sortedLowerCaseChars[givenCharsIndex];

            while (checkedChar < wantedChar) {
                givenCharsIndex++;
                if (givenCharsIndex == sortedLowerCaseChars.length) {
                    currentLack += scrableWord.getLength() - (i + 1);
                    break main;
                }
                checkedChar = sortedLowerCaseChars[givenCharsIndex];
            }
            if (checkedChar == wantedChar) {
                givenCharsIndex++;
                if (givenCharsIndex == sortedLowerCaseChars.length) {
                    currentLack += scrableWord.getLength() - (i + 1);
                    break;
                }
                continue;
            }

            if (++currentLack > maxLack) {
                return false;

            }

        }
        return currentLack <= maxLack;
    }


    public static char[] sortCharsAndRemoveBlanks(char[] chars, int blanks) {
        char[] result = new char[chars.length - blanks];
        int counter = 0;
        for (int i = 0; i < result.length; i++) {
            char current = chars[i];
            if (current != BLANK_LETTER) {
                result[counter++] = current;
            }
        }
        Arrays.sort(result);
        return result;
    }


}
