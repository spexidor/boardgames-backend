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

    //negative for survivors
    private int bleed;
    private int brainDamage;
    private int damage; //direct damage, eg from grab
    private boolean knockDown;
    private boolean grab;
    private boolean basicAttack;
    private boolean priorityToken; //gives priority target to attacker
    private boolean permanentPriorityToken;
    private int drawAI;
    private int knockBack;
    private int attackExtraDamage; // -additional- damage to attack
    private Token positiveToken; //adds permanent boost

    //positive for survivors
    private int gainSurvival;
    private int gainUnderstanding;
    private int gainCourage;
    private boolean monsterKnockDown;
    private boolean monsterDiesNextTurn;
    private Token negativeToken;

    public CardEffect() {
    }

    public int getBleed() {
        return bleed;
    }

    void setBleed(int bleed) {
        this.bleed = bleed;
    }

    public int getBrainDamage() {
        return brainDamage;
    }

    void setBrainDamage(int brainDamage) {
        this.brainDamage = brainDamage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    void setKnockDown(boolean knockDown) {
        this.knockDown = knockDown;
    }

    void setGrab(boolean grab) {
        this.grab = grab;
    }

    public Move getMove() {
        return move;
    }

    void setMove(Move move) {
        this.move = move;
    }

    void setBasicAttack(boolean basicAttack) {
        this.basicAttack = basicAttack;
    }

    void setPriorityToken(boolean priorityToken) {
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

    void setGainSurvival(int gainSurvival) {
        this.gainSurvival = gainSurvival;
    }

    public Condition getCondition() {
        return condition;
    }

    void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getDrawAI() { return drawAI; }

    void setDrawAI(int drawAI) {
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

    void setKnockBack(int knockBack) {
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

    void setAttackExtraDamage(int attackExtraDamage) {
        this.attackExtraDamage = attackExtraDamage;
    }

    public boolean isMonsterKnockDown() {
        return monsterKnockDown;
    }

    void setMonsterKnockDown(boolean monsterKnockDown) {
        this.monsterKnockDown = monsterKnockDown;
    }

    public boolean isMonsterDiesNextTurn() {
        return monsterDiesNextTurn;
    }

    void setMonsterDiesNextTurn(boolean monsterDiesNextTurn) {
        this.monsterDiesNextTurn = monsterDiesNextTurn;
    }

    public boolean isPermanentPriorityToken() {
        return permanentPriorityToken;
    }

    void setPermanentPriorityToken(boolean permanentPriorityToken) {
        this.permanentPriorityToken = permanentPriorityToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Token getPositiveToken() {
        return positiveToken;
    }

    public void setPositiveToken(Token positiveToken) {
        this.positiveToken = positiveToken;
    }

    public Token getNegativeToken() {
        return negativeToken;
    }

    public void setNegativeToken(Token negativeToken) {
        this.negativeToken = negativeToken;
    }
}
