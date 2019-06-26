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
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Fuzzy Groin", monsterLevel, this));

        this.cardsInDeck.add(HLCardBuilder.getCardByName( "Beast's Knee", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Elbow", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Chest", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Scapular Deltoid", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Back", monsterLevel, this));

        //this.cardsInDeck.add(HLCardBuilder.getCardByName("Fleshy Gut", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Ear", monsterLevel, this));
        //this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Tricep", monsterLevel, this));
        //this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Paw", monsterLevel, this));
        //this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Temple", monsterLevel, this));
        //this.cardsInDeck.add(HLCardBuilder.getCardByName("Strange Hand", monsterLevel, this));
        this.cardsInDeck.add(HLCardBuilder.getCardByName("Beast's Maw", monsterLevel, this));

        this.cardsInDeck.add(HLCardBuilder.getCardByName("Clever Ploy", monsterLevel, this)); //TRAP

        this.initCardOrderRandom();
        //this.setCardFirst("Strange Hand"); //uncomment when card is implemented
    }
}
