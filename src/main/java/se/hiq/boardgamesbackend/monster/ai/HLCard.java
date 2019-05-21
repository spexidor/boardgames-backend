package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hl_card")
class HLCard extends Card{
    private boolean trap;
    private boolean impervious;
    private String description;

    private boolean woundEffect;
    private boolean reflexEffect;
    private boolean failureEffect;
    private boolean critable;
    private boolean persistantInjury;

    @OneToOne(cascade = CascadeType.ALL)
    private CriticalWound criticalWound;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEffect effect;

    private HLCard(){
    }
    HLCard(String title){
        this.critable = true;
        this.title = title;
    }
    public HLCard(String title, Deck deck){

        this.title = title;
        this.setDeck(deck);
    }

    public boolean isTrap() {
        return trap;
    }

    public boolean isImpervious() {
        return impervious;
    }

    public boolean isWoundEffect() {
        return woundEffect;
    }

    public boolean isReflexEffect() {
        return reflexEffect;
    }

    public boolean isFailureEffect() {
        return failureEffect;
    }

    public CardEffect getEffect() {
        return effect;
    }

    public boolean isCritable() {
        return critable;
    }

    public void setCritable(boolean critable) {
        this.critable = critable;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    public void setImpervious(boolean impervious) {
        this.impervious = impervious;
    }

    public void setWoundEffect(boolean woundEffect) {
        this.woundEffect = woundEffect;
    }

    public void setReflexEffect(boolean reflexEffect) {
        this.reflexEffect = reflexEffect;
    }

    public void setFailureEffect(boolean failureEffect) {
        this.failureEffect = failureEffect;
    }

    public void setEffect(CardEffect effect) {
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPersistantInjury() {
        return persistantInjury;
    }

    public void setPersistantInjury(boolean persistantInjury) {
        this.persistantInjury = persistantInjury;
    }

    public CriticalWound getCriticalWound() {
        return criticalWound;
    }

    public void setCriticalWound(CriticalWound criticalWound) {
        this.criticalWound = criticalWound;
    }
}
