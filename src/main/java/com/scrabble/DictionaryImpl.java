package com.scrabble;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class DictionaryImpl implements ScrabbleDictionary {

    private final WordsProvider provider;
    private final TreeMap<Integer, List<ScrabbleWord>> sortedByPointsWords;

    public DictionaryImpl(WordsProvider provider) {
        this.provider = provider;
        List<ScrabbleWord> words = this.provider.getEnabledWords().stream()
                .map(ScrabbleWord::new)
                .collect(Collectors.toList());
        this.sortedByPointsWords = sortWordsByPoints(words);
    }

    @Override
    public String findTheBestWord(String regex, int quantity) {
        return null;
    }

    @Override
    public List<String> findTheBestWords(char[] chars, int quantity) {
        int availableBlankLetters = CharUtils.countLettersNumberInArray(chars, Config.BLANK_LETTER);
        char[] sortedCharsWithoutBlanks = CharUtils.sortCharsAndRemoveBlanks(chars, availableBlankLetters);

        int lettersNumber = chars.length;
        List<String> result = new LinkedList<>();

        main:
        for (int i = lettersNumber; i > 0; i--) {
            List<ScrabbleWord> wordsByPoints = sortedByPointsWords.get(i);
            if (wordsByPoints == null) {
                continue;
            }
            for (ScrabbleWord w : wordsByPoints) {
                if (!CharUtils.canBeBuildScrableWordFrom(w, sortedCharsWithoutBlanks, availableBlankLetters)) {
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

    private TreeMap<Integer, List<ScrabbleWord>> sortWordsByPoints(List<ScrabbleWord> words) {
        return words.stream().collect(Collectors.groupingBy(ScrabbleWord::getLength, () -> new TreeMap<>(Integer::compare), Collectors.toList()));
    }
}
