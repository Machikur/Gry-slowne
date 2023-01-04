package com.scrabble;

public enum ScrabbleFieldBonus {
    MULTIPLY_TWICE_LETTER {
        @Override
        public int multiplyLetter(int value) {
            return value * 2;
        }
    },
    MULTIPLY_TRIPLE_LETTER {
        @Override
        public int multiplyLetter(int value) {
            return value * 3;
        }
    },
    MULTIPLY_TWICE_WORD {
        @Override
        public int multiplyWord(int value) {
            return value * 2;
        }
    },
    MULTIPLY_TRIPLE_WORD {
        @Override
        public int multiplyWord(int value) {
            return value * 3;
        }
    },
    DEFAULT;

    public int multiplyLetter(int value) {
        return value;
    }

    public int multiplyWord(int value) {
        return value;
    }

}
