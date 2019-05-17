package se.hiq.boardgamesbackend.monster;

public class MonsterStatline {

    final int width;
    final int height;

    private final int movement;
    private final int toughness;

    protected MonsterStatline(int width, int height, int movement, int toughness){
        this.height = height;
        this.width = width;
        this.movement = movement;
        this.toughness = toughness;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMovement() {
        return movement;
    }

    public int getToughness() {
        return toughness;
    }
}
