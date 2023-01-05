package com.scrabble.game;

import com.scrabble.config.Config;
import com.scrabble.config.TableConfiguration;
import com.scrabble.core.ScrabbleDictionary;
import com.scrabble.pojo.Direction;
import com.scrabble.pojo.Position;
import com.scrabble.pojo.ScrabbleField;
import com.scrabble.pojo.ScrabbleWordProposition;
import com.scrabble.utill.CharUtils;

import java.util.*;

/**
 * Main class used to create and manipulate game
 * Searching returns only propositions that do not affect neighbour words
 */

public class ScrableTable {

    private final ScrabbleField[][] scrabbleFields;
    private final LinkedList<Character> lettersPool;
    private final ScrabbleDictionary scrabbleDictionary;
    private final List<Character> playerLetters = new ArrayList<>();

    public ScrableTable(ScrabbleDictionary dictionary, TableConfiguration c) {
        this.scrabbleDictionary = dictionary;
        this.lettersPool = new LinkedList<>(scrabbleDictionary.getLettersPoolForNewGame());
        Collections.shuffle(this.lettersPool);
        this.scrabbleFields = new ScrabbleField[c.getTableSize()][c.getTableSize()];
        for (int x = 0; x < scrabbleFields.length; x++) {
            for (int y = 0; y < scrabbleFields[x].length; y++) {
                this.scrabbleFields[x][y] = new ScrabbleField(x, y, c.resolveFieldBonus(x, y));
            }
        }
    }

    public ScrabbleWordProposition getBestWordProposition(Position position) {
        return getAllAvailableFieldsInLine(position).stream()
                .map(fields -> scrabbleDictionary.findTheBestProposition(fields, playerLetters))
                .filter(Objects::nonNull)
                .max(ScrabbleWordProposition::compareTo)
                .orElse(null);
    }

    public List<String> getBestWordsToUse(int quantity) {
        return scrabbleDictionary.findTheBestWords(CharUtils.convertToArray(playerLetters), quantity);
    }

    public void poolNextLetter() {
        if (lettersPool.isEmpty()) {
            throw new UnsupportedOperationException("There is no more letters to pool");
        }
        this.playerLetters.add(lettersPool.poll());
    }

    private List<ScrabbleField[]> getAllAvailableFieldsInLine(Position position) {
        List<ScrabbleField[]> allPossibleFieldsArray = new ArrayList<>();
        allPossibleFieldsArray.addAll(checkNeighbourFieldsAndSplitIntoAvailableFields(scrabbleFields[position.getX()], Direction.VERTICALLY));
        allPossibleFieldsArray.addAll(checkNeighbourFieldsAndSplitIntoAvailableFields(getAllByYPos(position.getY()), Direction.HORIZONTALLY));
        return allPossibleFieldsArray;
    }

    private List<ScrabbleField[]> checkNeighbourFieldsAndSplitIntoAvailableFields(ScrabbleField[] line, Direction direction) {
        int y = line[0].getY();
        int x = line[0].getX();
        List<ScrabbleField[]> fieldsToCheck = new ArrayList<>();
        switch (direction) {
            case HORIZONTALLY:
                if (x > 0) {
                    fieldsToCheck.add(scrabbleFields[x - 1]);
                }
                if (x < scrabbleFields.length) {
                    fieldsToCheck.add(scrabbleFields[x + 1]);
                }
                return splitIntoAvailableFields(line, fieldsToCheck, direction);
            case VERTICALLY:
                if (y > 0) {
                    fieldsToCheck.add(getAllByYPos(y - 1));
                }
                if (y < scrabbleFields.length) {
                    fieldsToCheck.add(getAllByYPos(y + 1));
                }
                return splitIntoAvailableFields(line, fieldsToCheck, direction);
        }
        return Collections.emptyList();
    }

    private List<ScrabbleField[]> splitIntoAvailableFields(ScrabbleField[] line, List<ScrabbleField[]> fieldsToCheck, Direction direction) {
        List<ScrabbleField[]> result = new ArrayList<>();
        List<ScrabbleField> current = new ArrayList<>();
        int maxLength = line.length - 1;
        for (int i = 0; i <= maxLength; i++) {
            for (ScrabbleField[] field : fieldsToCheck) {
                ScrabbleField currentField = field[i];
                if (currentField.getScrabbleFieldBonus() != null) {
                    if (current.size() >= Config.MIN_LETTERS_TO_CREATE_WORD) {
                        result.add(current.toArray(new ScrabbleField[0]));
                    }
                    current.clear();
                } else {
                    current.add(currentField);
                }
            }
            if (i == maxLength) {
                if (current.size() >= Config.MIN_LETTERS_TO_CREATE_WORD) {
                    result.add(current.toArray(new ScrabbleField[0]));
                }
            }
        }
        return result;
    }

    private ScrabbleField[] getAllByYPos(int yPos) {
        ScrabbleField[] fields = new ScrabbleField[scrabbleFields.length];
        for (int x = 0; x < fields.length; x++) {
            fields[x] = scrabbleFields[x][yPos];
        }
        return fields;
    }
}
