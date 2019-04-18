package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;

@Entity
public class HLDeck extends Deck {

    public HLDeck(){
        this.cardsInDeck.add(new HLCard("Hand", false, false, false, false));
        this.cardsInDeck.add(new HLCard("Jaw", false, false, false, false));
        this.cardsInDeck.add(new HLCard("Trap", false, false, false, true));
    }
}
