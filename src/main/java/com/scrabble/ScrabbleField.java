package com.scrabble;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScrabbleField {

    private final int x;
    private final int y;
    private boolean bonusCaught = false;
    private final ScrabbleFieldBonus scrabbleFieldBonus;
    private Character letterOn;

}
