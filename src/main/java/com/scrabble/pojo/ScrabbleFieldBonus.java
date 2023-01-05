package com.scrabble.pojo;

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
    MULTIPLY_TWICE_WORD(true) {
        @Override
        public int multiplyWord(int value) {
            return value * 2;
        }
    },
    MULTIPLY_TRIPLE_WORD(true) {
        @Override
        public int multiplyWord(int value) {
            return value * 3;
        }
    },
    DEFAULT;

    public final boolean isWordBonus;

    ScrabbleFieldBonus(boolean isWordBonus) {
        this.isWordBonus = isWordBonus;
    }

    ScrabbleFieldBonus() {
        this.isWordBonus = false;
    }

    public int multiplyLetter(int value) {
        return value;
    }

    public int multiplyWord(int value) {
        return value;
    }

}
