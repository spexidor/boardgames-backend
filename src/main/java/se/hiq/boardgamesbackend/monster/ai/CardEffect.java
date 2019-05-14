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

    private String name;

    private String description;

    //negative
    private int bleed;
    private int brainDamage;
    private int damage;
    private boolean knockDown;
    private boolean grab;
    private boolean basicAttack;
    private boolean priorityToken; //gives priority target to attacker
    private int drawAI;
    private int knockBack;
    private int attackExtraDamage;

    //positive
    private int gainSurvival;
    private int gainUnderstanding;
    private int gainCourage;

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

    public int getDrawAI() { return drawAI; }

    public void setDrawAI(int drawAI) {
        this.drawAI = drawAI;
    }

    public int getGainUnderstanding() {
        return gainUnderstanding;
    }

    public void setGainUnderstanding(int gainUnderstanding) {
        this.gainUnderstanding = gainUnderstanding;
    }

    public int getGainCourage() {
        return gainCourage;
    }

    public void setGainCourage(int gainCourage) {
        this.gainCourage = gainCourage;
    }

    public int getKnockBack() {
        return knockBack;
    }

    public void setKnockBack(int knockBack) {
        this.knockBack = knockBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAttackExtraDamage() {
        return attackExtraDamage;
    }

    public void setAttackExtraDamage(int attackExtraDamage) {
        this.attackExtraDamage = attackExtraDamage;
    }
}
