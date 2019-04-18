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
        Random r = new Random();

        this.sides = diceSides;
        this.result = r.nextInt(diceSides)  + 1;
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
}
