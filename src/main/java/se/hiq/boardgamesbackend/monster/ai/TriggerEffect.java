package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
public class TriggerEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int bleed;
    private int brainDamage;
    private int damage;
    private boolean knockDown;
    private boolean grab;

    @OneToOne(cascade=CascadeType.ALL)
    private Move move;

    public TriggerEffect() {
    }

    public int getBleed() {
        return bleed;
    }

    public void setBleed(int bleed) {
        this.bleed = bleed;
    }

    public int getBrainDamage() {
        return brainDamage;
    }

    public void setBrainDamage(int brainDamage) {
        this.brainDamage = brainDamage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isKnockDown() {
        return knockDown;
    }

    public void setKnockDown(boolean knockDown) {
        this.knockDown = knockDown;
    }

    public boolean isGrab() {
        return grab;
    }

    public void setGrab(boolean grab) {
        this.grab = grab;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
