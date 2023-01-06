package com.scrabble.config;

import com.scrabble.pojo.Position;
import com.scrabble.pojo.ScrabbleFieldBonus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DefaultTableConfigurationTest {

    @Test
    void bonusQuantityTest() {
        //given
        TableConfiguration configuration = new DefaultTableConfiguration();

        //when
        Map<ScrabbleFieldBonus, Integer> bonusCounter = new HashMap<>();
        for (int x = 0; x < configuration.getTableSize(); x++) {
            for (int y = 0; y < configuration.getTableSize(); y++) {
                ScrabbleFieldBonus bonus = configuration.resolveFieldBonus(x, y);
                bonusCounter.compute(bonus, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        //then
        Assertions.assertEquals(5, bonusCounter.size());
        Assertions.assertEquals(165, bonusCounter.get(ScrabbleFieldBonus.DEFAULT));
        Assertions.assertEquals(24, bonusCounter.get(ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER));
        Assertions.assertEquals(12, bonusCounter.get(ScrabbleFieldBonus.MULTIPLY_TRIPLE_LETTER));
        Assertions.assertEquals(8, bonusCounter.get(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD));
        Assertions.assertEquals(16, bonusCounter.get(ScrabbleFieldBonus.MULTIPLY_TWICE_WORD));
    }

    @Test
    void bonusMultiplyTripleWordPositionTest() {
        //given
        TableConfiguration configuration = new DefaultTableConfiguration();
        int maxIndex = configuration.getTableSize() - 1;

        //when
        Map<Position, ScrabbleFieldBonus> bonusMap = createMapByPosition(configuration);

        //then
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD, bonusMap.get(Position.of(0, 0)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD, bonusMap.get(Position.of(0, maxIndex)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD, bonusMap.get(Position.of(maxIndex, 0)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD, bonusMap.get(Position.of(maxIndex, maxIndex)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_WORD, bonusMap.get(Position.of(0, maxIndex  / 2)));
    }

    @Test
    void bonusMultiplyTwiceWordPositionTest() {
        //given
        TableConfiguration configuration = new DefaultTableConfiguration();

        //when
        Map<Position, ScrabbleFieldBonus> bonusMap = createMapByPosition(configuration);

        //then
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_WORD, bonusMap.get(Position.of(1, 1)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_WORD, bonusMap.get(Position.of(2, 2)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_WORD, bonusMap.get(Position.of(1, 13)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_WORD, bonusMap.get(Position.of(12, 2)));
    }


    @Test
    void bonusMultiplyTwiceLetterPositionTest() {
        //given
        TableConfiguration configuration = new DefaultTableConfiguration();
        int maxIndex = configuration.getTableSize() - 1;

        //when
        Map<Position, ScrabbleFieldBonus> bonusMap = createMapByPosition(configuration);

        //then
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER, bonusMap.get(Position.of(0, 3)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER, bonusMap.get(Position.of(6, 2)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER, bonusMap.get(Position.of(6, 8)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TWICE_LETTER, bonusMap.get(Position.of(maxIndex, 3)));
    }


    @Test
    void bonusMultiplyTripleLetterPositionTest() {
        //given
        TableConfiguration configuration = new DefaultTableConfiguration();
        //when
        Map<Position, ScrabbleFieldBonus> bonusMap = createMapByPosition(configuration);

        //then
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_LETTER, bonusMap.get(Position.of(5, 1)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_LETTER, bonusMap.get(Position.of(9, 1)));
        Assertions.assertEquals(ScrabbleFieldBonus.MULTIPLY_TRIPLE_LETTER, bonusMap.get(Position.of(13, 9)));
    }



    private Map<Position, ScrabbleFieldBonus> createMapByPosition(TableConfiguration configuration) {
        Map<Position, ScrabbleFieldBonus> result = new HashMap<>();
        for (int x = 0; x < configuration.getTableSize(); x++) {
            for (int y = 0; y < configuration.getTableSize(); y++) {
                result.put(Position.of(x, y), configuration.resolveFieldBonus(x, y));
            }
        }
        return result;
    }
}