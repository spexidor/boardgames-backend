package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hl_card")
public class HLCard extends Card{
    private boolean trap;
    private boolean impervious;

    private boolean woundEffect;
    private boolean reflexEffect;
    private boolean failureEffect;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEffect effect;

    private HLCard(){
    }
    public HLCard(String title, Deck deck){

        this.title = title;
        this.setDeck(deck);
    }

    public boolean isTrap() {
        return trap;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    public boolean isImpervious() {
        return impervious;
    }

    public void setImpervious(boolean impervious) {
        this.impervious = impervious;
    }

    public boolean isWoundEffect() {
        return woundEffect;
    }

    public void setWoundEffect(boolean woundEffect) {
        this.woundEffect = woundEffect;
    }

    public boolean isReflexEffect() {
        return reflexEffect;
    }

    public void setReflexEffect(boolean reflexEffect) {
        this.reflexEffect = reflexEffect;
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

    public void setEffect(CardEffect effect) {
        this.effect = effect;
    }
}
