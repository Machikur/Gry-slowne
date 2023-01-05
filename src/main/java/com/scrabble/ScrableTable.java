package com.scrabble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ScrableTable {

    private final ScrabbleField[][] scrabbleFields;
    private final LinkedList<Character> lettersPool;
    private ScrabbleDictionary scrabbleDictionary;
    private List<Character> playerLetters = new ArrayList<>();

    public ScrableTable(ScrabbleDictionary dictionary, TableConfiguration c) {
        this.scrabbleDictionary = dictionary;
        this.lettersPool = new LinkedList<>(scrabbleDictionary.createLettersPoolForNewGame());
        Collections.shuffle(this.lettersPool);
        this.scrabbleFields = new ScrabbleField[c.getTableSize()][c.getTableSize()];
        for (int x = 0; x < scrabbleFields.length; x++) {
            for (int y = 0; y < scrabbleFields[x].length; y++) {
                this.scrabbleFields[x][y] = new ScrabbleField(x, y, c.resolveFieldBonus(x, y));
            }
        }
    }

    public String getBestWordProposition(Position position) {
        String horizontal = scrabbleDictionary.findTheBestWord(getAllFieldsInLine(position, Direction.RIGHT), playerLetters);
        String vertical = scrabbleDictionary.findTheBestWord(getAllFieldsInLine(position, Direction.DOWN), playerLetters);
        if (horizontal == null) {
            return vertical;
        } else if (vertical == null) {
            return horizontal;
        }
        return horizontal.compareTo(vertical) < 0 ? vertical : horizontal;
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

    private ScrabbleField[] getAllFieldsInLine(Position position, Direction direction) {
        ScrabbleField[] result = new ScrabbleField[scrabbleFields.length];
        switch (direction) {
            case DOWN:
                int y = position.getY();
                for (int i = 0; i < result.length; i++) {
                    result[i] = scrabbleFields[i][y];
                }
                return result;
            case RIGHT:
                int x = position.getX();
                for (int i = 0; i < result.length; i++) {
                    result[i] = scrabbleFields[x][i];
                }
                return result;
            default:
                throw new UnsupportedOperationException(String.format("%s given direction is not recognized", direction));
        }
    }
}
