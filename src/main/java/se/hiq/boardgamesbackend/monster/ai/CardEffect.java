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

    //general
    private boolean repeatEveryTurn;
    private int repeatOnRoll;
    private boolean affectsAllSurvivors;

    //negative for survivors
    private int bleed;
    private int brainDamage;
    private int damage; //direct damage, eg from grab
    private boolean survivorKnockdown;
    private boolean grab;
    private boolean basicAttack;
    private boolean priorityToken; //gives priority target to attacker
    private boolean permanentPriorityToken;
    private int drawAI;
    private int knockBack;
    private int attackExtraDamage; // -additional- damage to attack
    private Token monsterPositiveToken; //adds permanent boost
    private Token survivorNegativeToken;

    //positive for survivors
    private int survivorGainSurvival;
    private int survivorGainUnderstanding;
    private int survivorGainCourage;
    private int survivorGainInsanity;
    private boolean monsterKnockDown;
    private boolean monsterDiesNextTurn;
    private Token monsterNegativeToken;
    private Token survivorPositiveToken;
    private boolean survivorStand;

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

    void setSurvivorKnockdown(boolean survivorKnockdown) {
        this.survivorKnockdown = survivorKnockdown;
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

    public boolean isSurvivorKnockdown() {
        return survivorKnockdown;
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

    public int getSurvivorGainSurvival() {
        return survivorGainSurvival;
    }

    void setSurvivorGainSurvival(int survivorGainSurvival) {
        this.survivorGainSurvival = survivorGainSurvival;
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

    public int getSurvivorGainUnderstanding() {
        return survivorGainUnderstanding;
    }

    public void setSurvivorGainUnderstanding(int survivorGainUnderstanding) {
        this.survivorGainUnderstanding = survivorGainUnderstanding;
    }

    public int getSurvivorGainCourage() {
        return survivorGainCourage;
    }

    public void setSurvivorGainCourage(int survivorGainCourage) {
        this.survivorGainCourage = survivorGainCourage;
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

    public Token getMonsterPositiveToken() {
        return monsterPositiveToken;
    }

    public void setMonsterPositiveToken(Token monsterPositiveToken) {
        this.monsterPositiveToken = monsterPositiveToken;
    }

    public Token getMonsterNegativeToken() {
        return monsterNegativeToken;
    }

    public void setMonsterNegativeToken(Token monsterNegativeToken) {
        this.monsterNegativeToken = monsterNegativeToken;
    }

    public boolean isRepeatEveryTurn() {
        return repeatEveryTurn;
    }

    public void setRepeatEveryTurn(boolean repeatEveryTurn) {
        this.repeatEveryTurn = repeatEveryTurn;
    }

    public int getRepeatOnRoll() {
        return repeatOnRoll;
    }

    public void setRepeatOnRoll(int repeatOnRoll) {
        this.repeatOnRoll = repeatOnRoll;
    }

    public Token getSurvivorPositiveToken() {
        return survivorPositiveToken;
    }

    public void setSurvivorPositiveToken(Token survivorPositiveToken) {
        this.survivorPositiveToken = survivorPositiveToken;
    }

    public boolean isAffectsAllSurvivors() {
        return affectsAllSurvivors;
    }

    public void setAffectsAllSurvivors(boolean affectsAllSurvivors) {
        this.affectsAllSurvivors = affectsAllSurvivors;
    }

    public boolean isSurvivorStand() {
        return survivorStand;
    }

    public void setSurvivorStand(boolean survivorStand) {
        this.survivorStand = survivorStand;
    }

    public int getSurvivorGainInsanity() {
        return survivorGainInsanity;
    }

    public void setSurvivorGainInsanity(int survivorGainInsanity) {
        this.survivorGainInsanity = survivorGainInsanity;
    }

    public Token getSurvivorNegativeToken() {
        return survivorNegativeToken;
    }

    public void setSurvivorNegativeToken(Token survivorNegativeToken) {
        this.survivorNegativeToken = survivorNegativeToken;
    }
}
