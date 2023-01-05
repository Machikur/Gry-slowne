package com.scrabble;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class DictionaryImpl implements ScrabbleDictionary {

    private final WordsProvider provider;
    private final TreeMap<Integer, List<ScrabbleWord>> sortedByPointsWords;

    public DictionaryImpl(WordsProvider provider) {
        this.provider = provider;
        List<ScrabbleWord> words = this.provider.getEnabledWords().stream()
                .map(ScrabbleWord::new)
                .collect(Collectors.toList());
        this.sortedByPointsWords = sortWordsByLength(words);
    }

    @Override
    public String findTheBestWord(ScrabbleField[] fieldsInLine, List<Character> playerChars) {

        List<Character> allChars = new LinkedList<>(playerChars);
        addAllCharsToList(allChars, fieldsInLine);
        char[] allAvailableChars = CharUtils.convertToArray(allChars);

        List<String> possibleWords = findTheBestWords(allAvailableChars);

        Pattern pattern = PatternUtils.createPattern(fieldsInLine);
        return possibleWords.stream()
                .filter(s -> pattern.matcher(s).find())
                .max(Comparator.comparingInt(provider::getPointsForWord))
                .orElse(null);
    }

    public List<String> findTheBestWords(char[] chars) {
        return findTheBestWords(chars, Integer.MAX_VALUE);
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
        return provider.getLettersPool();
    }

    private void addAllCharsToList(List<Character> list, ScrabbleField[] fields) {
        for (ScrabbleField field : fields) {
            if (field.getLetterOn() != null) {
                list.add(field.getLetterOn());
            }
        }
    }


    /**
     * @param words all enabled words
     * @return sorted map with keys sorted by length related to the word
     */
    private TreeMap<Integer, List<ScrabbleWord>> sortWordsByLength(List<ScrabbleWord> words) {
        return words.stream().collect(Collectors.groupingBy(ScrabbleWord::getLength, () -> new TreeMap<>((o1, o2) -> -Integer.compare(o1, o2)), Collectors.toList()));
    }
}
