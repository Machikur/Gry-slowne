package com.scrabble.core;

import com.scrabble.config.Config;
import com.scrabble.pojo.*;
import com.scrabble.utill.CharUtils;
import com.scrabble.utill.PatternUtils;
import org.springframework.lang.Nullable;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Simple Dictionary Implementation based on Patterns to find the best results
 */

public class DictionaryImpl implements ScrabbleDictionary {

    private final WordsProvider provider;
    private final TreeMap<Integer, List<ScrabbleWord>> sortedByPointsWords;

    public DictionaryImpl(WordsProvider provider) {
        this(provider, Config.DEFAULT_TABLE_SIZE);
    }

    public DictionaryImpl(WordsProvider provider, int tableSize) {
        this.provider = provider;
        List<ScrabbleWord> words = this.provider.getEnabledWords(tableSize).stream()
                .map(ScrabbleWord::new)
                .collect(Collectors.toList());
        this.sortedByPointsWords = sortWordsByLength(words);
    }

    @Override
    public ScrabbleWordProposition findTheBestProposition(ScrabbleField[] availableFieldsInLine, List<Character> playerChars) {

        List<Character> allChars = new LinkedList<>(playerChars);
        addAllCharsToList(allChars, availableFieldsInLine);
        char[] allAvailableChars = CharUtils.convertToArray(allChars);

        Direction direction = Direction.recognizeDirection(availableFieldsInLine);

        List<String> possibleWords = findTheBestWords(allAvailableChars, -1);
        if (possibleWords.isEmpty()) {
            return null;
        }

        Pattern pattern = PatternUtils.createPattern(availableFieldsInLine, direction);
        return possibleWords.stream()
                .filter(s -> pattern.matcher(s).find())
                .map(s -> toProposition(availableFieldsInLine, direction, s))
                .filter(Objects::nonNull)
                .max(ScrabbleWordProposition::compareTo)
                .orElse(null);
    }

    @Override
    public List<String> findTheBestWords(char[] chars, int maxQuantity) {
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
                if (result.size() == maxQuantity) {
                    break main;
                }
            }
        }
        return result;
    }

    @Nullable
    private ScrabbleWordProposition toProposition(ScrabbleField[] fields, Direction direction, String word) {
        char firstSign = word.charAt(0);
        for (int i = 0; i < fields.length; i++) {
            ScrabbleField field = fields[i];
            Character current = field.getLetterOn();
            if (current == null) {
                continue;
            }
            if (current.equals(firstSign)) {
                if (!checkIfAnyLetterIsAdded(fields, word, i)) {
                    return null;
                }
                int pointsForWord = countPoints(fields, word, i);
                return new ScrabbleWordProposition(new Position(field.getX(), field.getY()), word, pointsForWord, direction);
            }
        }
        return null;
    }

    private boolean checkIfAnyLetterIsAdded(ScrabbleField[] fields, String word, int startIndex) {
        for (int i = startIndex; i < startIndex + word.length(); i++) {
            if (fields[i].getLetterOn() == null) {
                return true;
            }
        }
        return false;
    }

    private int countPoints(ScrabbleField[] fields, String word, int startIndex) {
        List<ScrabbleFieldBonus> wordBonuses = new ArrayList<>();
        int points = 0;
        int currentLetter = 0;
        for (int i = startIndex; i < startIndex + word.length(); i++) {
            ScrabbleField current = fields[i];
            ScrabbleFieldBonus bonus = current.getScrabbleFieldBonus();
            int charPoints = provider.getBasicPointsForChar(word.charAt(currentLetter));
            if (!current.isBonusCaught()) {
                if (bonus.isWordBonus) {
                    wordBonuses.add(bonus);
                } else {
                    charPoints = bonus.multiplyLetter(charPoints);
                }
            }
            points += charPoints;
            currentLetter++;
        }
        for (ScrabbleFieldBonus bonus : wordBonuses) {
            points = bonus.multiplyWord(points);
        }
        return points;
    }

    @Override
    public List<Character> getLettersPoolForNewGame() {
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
