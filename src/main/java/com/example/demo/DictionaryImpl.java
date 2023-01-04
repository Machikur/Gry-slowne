package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.example.demo.CharUtils.canBeBuildFrom;
import static com.example.demo.CharUtils.sortCharsAndRemoveBlanks;
import static com.example.demo.Config.BLANK_LETTER;


public class DictionaryImpl implements ScrabbleDictionary {

    private final WordsProvider provider;
    private final TreeMap<Integer, List<ScrableWord>> sortedByPointsWords;

    public DictionaryImpl(WordsProvider provider) {
        this.provider = provider;
        List<ScrableWord> words = this.provider.getEnabledWords().stream()
                .map(ScrableWord::new)
                .collect(Collectors.toList());
        this.sortedByPointsWords = sortWordsByPoints(words);
    }

    @Override
    public String findTheBestWord(String regex, int quantity) {
        return null;
    }

    @Override
    public List<String> findTheBestWords(char[] chars, int quantity) {
        int availableBlankLetters = CharUtils.countLetters(chars, BLANK_LETTER);
        char[] sortedCharsWithoutBlanks = sortCharsAndRemoveBlanks(chars, availableBlankLetters);

        int lettersNumber = chars.length;
        List<String> result = new LinkedList<>();

        main:
        for (int i = lettersNumber; i > 0; i--) {
            List<ScrableWord> wordsByPoints = sortedByPointsWords.get(i);
            if (wordsByPoints == null) {
                continue;
            }
            for (ScrableWord w : wordsByPoints) {
                if (!canBeBuildFrom(w, sortedCharsWithoutBlanks, availableBlankLetters)) {
                    continue;
                }
                result.add(w.getWord());
                if (result.size() == quantity) {
                    break main;
                }
            }
        }
        return result;
    }

    @Override
    public List<Character> createLettersPoolForNewGame() {
        return new LinkedList<>(provider.getLettersPool());
    }


    /**
     * @param words all enabled words
     * @return sorted map with keys sorted by score related to the word
     */

    private TreeMap<Integer, List<ScrableWord>> sortWordsByPoints(List<ScrableWord> words) {
        return words.stream().collect(Collectors.groupingBy(ScrableWord::getLength, () -> new TreeMap<>(Integer::compare), Collectors.toList()));
    }
}
