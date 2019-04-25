package se.hiq.boardgamesbackend.dice;

import java.util.Random;

public class DiceResult {

    private int sides;
    private int result;
    private boolean hit;

    public DiceResult(int diceSides){
        this(diceSides, 1);
    }

    public DiceResult(int diceSides, int toHitValue) {

        this.sides = diceSides;
        this.result = roll(diceSides);
        this.hit = (this.result >= toHitValue);
    }

    public int getSides() {
        return sides;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public static int roll(int diceSides){
        Random r = new Random();

        return r.nextInt(diceSides)  + 1;
    }
}
