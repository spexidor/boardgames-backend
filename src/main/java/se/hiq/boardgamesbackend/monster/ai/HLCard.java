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

    private boolean woundEffect;
    private boolean reflexEffect;
    private boolean failureEffect;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEffect effect;

    private HLCard(){
    }
    HLCard(String title){

        this.title = title;
    }
    public HLCard(String title, Deck deck){

        this.title = title;
        this.setDeck(deck);
    }

    public boolean isTrap() {
        return trap;
    }

    void setTrap() {
        this.trap = true;
    }

    public boolean isImpervious() {
        return impervious;
    }

    void setImpervious() {
        this.impervious = true;
    }

    public boolean isWoundEffect() {
        return woundEffect;
    }

    void setWoundEffect() {
        this.woundEffect = true;
    }

    public boolean isReflexEffect() {
        return reflexEffect;
    }

    void setReflexEffect() {
        this.reflexEffect = true;
    }

    public boolean isFailureEffect() {
        return failureEffect;
    }

    public void setFailureEffect(boolean failureEffect) {
        this.failureEffect = failureEffect;
    }

    public CardEffect getEffect() {
        return effect;
    }

    void setEffect(CardEffect effect) {
        this.effect = effect;
    }
}
