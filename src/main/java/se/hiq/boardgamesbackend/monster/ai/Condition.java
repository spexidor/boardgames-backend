package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "effect_condition")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean survivorAtacking;
    private boolean survivorTargeted;
    private boolean survivorPassedOver;

    private int minCourage;
    private int minUnderstanding;
    private int minHits; //hits after dodge
    private int minInsanity;
    private boolean nonDeaf;

    private int diceRolld10; //min value to roll

    @OneToOne
    @JsonIgnore
    private CardEffect cardEffect;

    private Condition(){}

    public Condition(CardEffect cardEffect){ }

    public Condition(CardEffect cardEffect, boolean survivorAtacking, boolean survivorTargeted, int minCourage, int minUnderstanding) {
        this.survivorAtacking = survivorAtacking;
        this.survivorTargeted = survivorTargeted;
        this.minCourage = minCourage;
        this.minUnderstanding = minUnderstanding;
        this.cardEffect = cardEffect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSurvivorAtacking() {
        return survivorAtacking;
    }

    public void setSurvivorAtacking(boolean survivorAtacking) {
        this.survivorAtacking = survivorAtacking;
    }

    public boolean isSurvivorTargeted() {
        return survivorTargeted;
    }

    public void setSurvivorTargeted(boolean survivorTargeted) {
        this.survivorTargeted = survivorTargeted;
    }

    public int getMinCourage() {
        return minCourage;
    }

    public void setMinCourage(int minCourage) {
        this.minCourage = minCourage;
    }

    public int getMinUnderstanding() {
        return minUnderstanding;
    }

    public void setMinUnderstanding(int minUnderstanding) {
        this.minUnderstanding = minUnderstanding;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public void setCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
    }

    public int getMinHits() {
        return minHits;
    }

    public void setMinHits(int minHits) {
        this.minHits = minHits;
    }

    public int getDiceRolld10() {
        return diceRolld10;
    }

    public void setDiceRolld10(int diceRolld10) {
        this.diceRolld10 = diceRolld10;
    }

    public int getMinInsanity() {
        return minInsanity;
    }

    public void setMinInsanity(int minInsanity) {
        this.minInsanity = minInsanity;
    }

    public boolean isNonDeaf() {
        return nonDeaf;
    }

    public void setNonDeaf(boolean nonDeaf) {
        this.nonDeaf = nonDeaf;
    }

    public boolean isSurvivorPassedOver() {
        return survivorPassedOver;
    }

    public void setSurvivorPassedOver(boolean survivorPassedOver) {
        this.survivorPassedOver = survivorPassedOver;
    }
}
