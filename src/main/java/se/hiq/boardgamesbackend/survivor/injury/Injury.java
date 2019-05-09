package se.hiq.boardgamesbackend.survivor.injury;

public class Injury {
    private String location;
    private String title;
    private boolean dead;
    private int bleed;
    private boolean knockedDown;

    private Injury() { }

    public Injury(String title, String location, boolean dead, int bleed){
        this(title, location, dead, bleed, false);
    }

    public Injury(String title, String location, boolean dead, int bleed, boolean knockedDown) {
        this.title = title;
        this.location = location;
        this.dead = dead;
        this.bleed = bleed;
        this.knockedDown = knockedDown;
    }

    public boolean isDead() {
        return dead;
    }

    public int getBleed() {
        return bleed;
    }

    public String getLocation() { return location; }

    public String getTitle() { return title; }

    public boolean isKnockedDown() { return knockedDown; }
}
