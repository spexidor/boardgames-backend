package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;

@Entity
public class HLDeck extends Deck {

    public HLDeck(){
        HLCard card1 = new HLCard("Clever Ploy", this);
        ((HLCard) card1).setTrap(true);
        CardEffect cardEffect1 = new CardEffect();
        cardEffect1.setBasicAttack(true);
        ((HLCard) card1).setEffect(cardEffect1);
        this.cardsInDeck.add(card1);


        HLCard card2 = new HLCard("Straining Neck", this);
        this.cardsInDeck.add(card2);

        HLCard card3 = new HLCard("Beast's Femur", this);
        this.cardsInDeck.add(card3);

        HLCard card4 = new HLCard("Beast's Knee", this);
        this.cardsInDeck.add(card4);

        HLCard card5 = new HLCard("Fuzzy Groin", this);
        this.cardsInDeck.add(card5);

        HLCard card6 = new HLCard("Beast's Heel", this);
        this.cardsInDeck.add(card6);

        HLCard card7 = new HLCard("Soft Belly", this);
        this.cardsInDeck.add(card7);

        HLCard card8 = new HLCard("Glorious Mane", this);
        this.cardsInDeck.add(card8);

        HLCard card9 = new HLCard("Beast's Flank", this);
        ((HLCard) card9).setWoundEffect(true);
        CardEffect cardEffect9 = new CardEffect();
        cardEffect9.setPriorityToken(true);
        ((HLCard) card9).setEffect(cardEffect1);
        this.cardsInDeck.add(card9);

        HLCard card10 = new HLCard("Beast's Brow", this);
        ((HLCard) card10).setWoundEffect(true);
        CardEffect cardEffect10 = new CardEffect();
        cardEffect10.setBrainDamage(1);
        cardEffect10.setBasicAttack(true);
        ((HLCard) card10).setEffect(cardEffect10);
        this.cardsInDeck.add(card10);

        HLCard card11 = new HLCard("Beast's Rib", this);
        ((HLCard) card11).setWoundEffect(true);
        CardEffect cardEffect11 = new CardEffect();
        cardEffect11.setGainSurvival(1);
        Condition condition11 = new Condition(cardEffect11, true, false, 0, 3);
        cardEffect11.setCondition(condition11);
        ((HLCard) card11).setEffect(cardEffect11);
        this.cardsInDeck.add(card11);

        HLCard card12 = new HLCard("Beast's Tail", this);
        ((HLCard) card12).setReflexEffect(true);
        CardEffect cardEffect12 = new CardEffect();
        Move move12 = new Move(Direction.FORWARD, true, cardEffect12);
        cardEffect12.setMove(move12);
        ((HLCard) card12).setEffect(cardEffect12);
        this.cardsInDeck.add(card12);

    }
}
