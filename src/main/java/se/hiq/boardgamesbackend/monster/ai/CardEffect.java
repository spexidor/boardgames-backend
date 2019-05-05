package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
public class CardEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Condition condition;

    @OneToOne(cascade=CascadeType.ALL)
    private Move move;

    private int bleed;
    private int brainDamage;
    private int damage;
    private boolean knockDown;
    private boolean grab;
    private boolean basicAttack;
    private boolean priorityToken; //gives priority target to attacker
    private int gainSurvival;

    public CardEffect() {
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

    public void setKnockDown(boolean knockDown) {
        this.knockDown = knockDown;
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

    public void setBasicAttack(boolean basicAttack) {
        this.basicAttack = basicAttack;
    }

    public void setPriorityToken(boolean priorityToken) {
        this.priorityToken = priorityToken;
    }

    public boolean isKnockDown() {
        return knockDown;
    }

    public boolean isGrab() {
        return grab;
    }

    public boolean isBasicAttack() {
        return basicAttack;
    }

    public boolean isPriorityToken() {
        return priorityToken;
    }

    public int getGainSurvival() {
        return gainSurvival;
    }

    public void setGainSurvival(int gainSurvival) {
        this.gainSurvival = gainSurvival;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
