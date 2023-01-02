package com.example.demo;

public class Field {

    private final int x;
    private final int y;
    private boolean bonusCaught = false;
    private String letterOn;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getLetterOn() {
        return letterOn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLetterOn(String letterOn) {
        this.letterOn = letterOn;
    }

    public boolean isBonusCaught() {
        return bonusCaught;
    }

    public void setBonusCaught(boolean bonusCaught) {
        this.bonusCaught = bonusCaught;
    }
}
