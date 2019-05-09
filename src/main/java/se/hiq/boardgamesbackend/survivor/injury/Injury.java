package se.hiq.boardgamesbackend.survivor.injury;

import se.hiq.boardgamesbackend.monster.ai.CardEffect;
import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;

public class Injury {
    private HitlocationType location;
    private String title;
    private boolean dead;
    private int bleed;
    private boolean knockedDown;
    private CardEffect cardEffect;

    private Injury() { }

    public Injury(String title, HitlocationType location, boolean dead, int bleed){
        this(title, location, dead, bleed, false);
    }

    public Injury(String title, HitlocationType location, boolean dead, int bleed, boolean knockedDown) {
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

    public HitlocationType getLocation() { return location; }

    public String getTitle() { return title; }

    public boolean isKnockedDown() { return knockedDown; }

    public CardEffect getCardEffect() { return cardEffect; }

    public void setCardEffect(CardEffect cardEffect) { this.cardEffect = cardEffect; }
}
