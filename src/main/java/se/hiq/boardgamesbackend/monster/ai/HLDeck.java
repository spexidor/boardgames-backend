package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;

@Entity
public class HLDeck extends Deck {

    public HLDeck(){

    }
    public HLDeck(int monsterLevel){

        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Tail", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Rib", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Brow", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Flank", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Heel", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Knee", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Femur", monsterLevel, this));

        this.cardsInDeck.add(HLCardBuilder.getCardByName("Straining Neck", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Glorious Mane", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Soft Belly", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Clever Ploy", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Fuzzy Groin", monsterLevel, this));

        this.initCardOrder();
    }
}
