package se.hiq.boardgamesbackend.monster;

public class MonsterStatline {

    protected String name;
    protected int width;
    protected int height;

    protected int movement;
    protected int toughness;

    public MonsterStatline(String name, int width, int height, int movement, int toughness){
        this.height = height;
        this.width = width;
        this.name = name;
        this.movement = movement;
        this.toughness = toughness;
    }

    /*
    private Deck AIdeck;
    private Deck HLdeck;
    private Deck Resources;
    */

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
