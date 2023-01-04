package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Field {

    private final int x;
    private final int y;
    private boolean bonusCaught = false;
    private Character letterOn;


}
