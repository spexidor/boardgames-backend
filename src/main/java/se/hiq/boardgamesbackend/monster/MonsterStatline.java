package se.hiq.boardgamesbackend.monster;

public class MonsterStatline {

    private final String name;
    final int width;
    final int height;

    private final int movement;
    private final int toughness;

    protected MonsterStatline(String name, int width, int height, int movement, int toughness){
        this.height = height;
        this.width = width;
        this.name = name;
        this.movement = movement;
        this.toughness = toughness;
    }

    public String getName() {
        return name;
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
