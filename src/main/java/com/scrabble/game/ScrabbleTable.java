package com.scrabble.game;

import com.scrabble.config.Config;
import com.scrabble.config.TableConfiguration;
import com.scrabble.core.ScrabbleDictionary;
import com.scrabble.pojo.*;
import com.scrabble.utill.CharUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class used to create and manipulate game
 * Searching returns only propositions that do not affect neighbour words
 */

public class ScrabbleTable {

    private final TableConfiguration c;
    private final ScrabbleField[][] scrabbleFields;
    private final LinkedList<ScrabbleChar> lettersPool;
    private final ScrabbleDictionary scrabbleDictionary;
    private final List<ScrabbleChar> playerLetters = new ArrayList<>();

    public ScrabbleTable(ScrabbleDictionary dictionary, TableConfiguration c) {
        this.c = c;
        this.scrabbleDictionary = dictionary;
        this.lettersPool = new LinkedList<>(scrabbleDictionary.getScrabbleCharsPoolForNewGame());
        Collections.shuffle(this.lettersPool);
        this.scrabbleFields = new ScrabbleField[this.c.getTableSize()][this.c.getTableSize()];
        for (int x = 0; x < scrabbleFields.length; x++) {
            for (int y = 0; y < scrabbleFields[x].length; y++) {
                this.scrabbleFields[x][y] = new ScrabbleField(x, y, this.c.resolveFieldBonus(x, y));
//                if (Math.random() < 0.2) {
//                    this.scrabbleFields[x][y].setScrabbleCharOn(lettersPool.poll());
//                }
            }
        }
    }


    public void start() {
        for (int i = 0; i < c.getLettersOnStart(); i++) {
            poolNextLetter();
        }
    }


    public ScrabbleWordProposition getBestWordProposition(Position position) {
        return getAllAvailableFieldsInLine(position).stream()
                .map(fields -> scrabbleDictionary.findTheBestProposition(fields, playerLetters))
                .max(ScrabbleWordProposition::compareTo)
                .orElse(ScrabbleWordProposition.EMPTY_PROPOSITION);
    }

    public List<String> getBestWordsToUse(int quantity) {
        return scrabbleDictionary.findTheBestWords(CharUtils.convertToCharArray(playerLetters), quantity);
    }

    public ScrabbleChar poolNextLetter() {
        if (lettersPool.isEmpty()) {
            throw new RuntimeException("There is no more letters to pool");
        }
        ScrabbleChar c = lettersPool.poll();
        this.playerLetters.add(c);
        return c;
    }

    public List<ScrabbleChar> getPlayerLetters() {
        return playerLetters;
    }

    public void putWord(ScrabbleWordRequest request) {


    }

    private List<ScrabbleField[]> getAllAvailableFieldsInLine(Position position) {
        List<ScrabbleField[]> allPossibleFieldsArray = new ArrayList<>();
        allPossibleFieldsArray.addAll(checkNeighbourFieldsAndSplitIntoAvailableFields(scrabbleFields[position.getX()], position, Direction.VERTICALLY));
        allPossibleFieldsArray.addAll(checkNeighbourFieldsAndSplitIntoAvailableFields(getAllByYPos(position.getY()), position, Direction.HORIZONTALLY));
        return allPossibleFieldsArray;
    }

    private List<ScrabbleField[]> checkNeighbourFieldsAndSplitIntoAvailableFields(ScrabbleField[] line, Position position, Direction direction) {
        int y = position.getX();
        int x = position.getY();
        List<ScrabbleField[]> fieldsToCheck = new ArrayList<>();
        switch (direction) {
            case VERTICALLY:
                if (y > 0) {
                    fieldsToCheck.add(scrabbleFields[y - 1]);
                }
                if (y < scrabbleFields.length - 1) {
                    fieldsToCheck.add(scrabbleFields[y + 1]);
                }
                return splitIntoAvailableFields(line, fieldsToCheck);
            case HORIZONTALLY:
                if (x > 0) {
                    fieldsToCheck.add(getAllByYPos(x - 1));
                }
                if (x < scrabbleFields.length - 1) {
                    fieldsToCheck.add(getAllByYPos(x + 1));
                }
                return splitIntoAvailableFields(line, fieldsToCheck);
        }
        return Collections.emptyList();
    }

    private List<ScrabbleField[]> splitIntoAvailableFields(ScrabbleField[] line, List<ScrabbleField[]> linesToCheck) {
        List<ScrabbleField[]> result = new ArrayList<>();
        List<ScrabbleField> currentPossibleFields = new ArrayList<>();
        int maxLength = line.length - 1;
        for (int i = 0; i <= maxLength; i++) {
            ScrabbleField currentField = line[i];
            if (currentField.getScrabbleCharOn() != null) {
                currentPossibleFields.add(currentField);
            } else {
                boolean anyNeighbourScrabbleChar = false;
                for (ScrabbleField[] fieldsLine : linesToCheck) {
                    if (fieldsLine[i].getScrabbleCharOn() != null) {
                        if (currentPossibleFields.size() >= Config.MIN_LETTERS_TO_CREATE_WORD) {
                            anyNeighbourScrabbleChar = true;
                            break;
                        }
                    }
                }
                if (anyNeighbourScrabbleChar) {
                    result.add(currentPossibleFields.toArray(new ScrabbleField[0]));
                    currentPossibleFields.clear();
                    continue;
                }
                currentPossibleFields.add(currentField);
            }
            if (i == maxLength && currentPossibleFields.size() >= Config.MIN_LETTERS_TO_CREATE_WORD) {
                result.add(currentPossibleFields.toArray(new ScrabbleField[0]));
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

    public ScrabbleTableData toData() {
        return new ScrabbleTableData(scrabbleFields, playerLetters, lettersPool.size());
    }

}
