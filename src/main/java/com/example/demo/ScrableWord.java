package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class ScrableWord {

    private final String word;
    private final char[] sortedChars;

    public ScrableWord(String word) {
        this.word = word.toLowerCase();
        sortedChars = word.toCharArray();
        Arrays.sort(sortedChars);
    }

    public int getLength() {
        return sortedChars.length;
    }

    @Override
    public String toString() {
        return word;
    }
}
