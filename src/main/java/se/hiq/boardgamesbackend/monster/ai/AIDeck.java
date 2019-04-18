package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;

@Entity
public class AIDeck extends Deck {

    public AIDeck(){
        this.cardsInDeck.add(new AICard("Claw", new TargetRule(new Target(true, true, true, true), new Target(true, true)), new Attack(2, 2, 1)));
        this.cardsInDeck.add(new AICard("Bloody Claw", new TargetRule(new Target(true, true, true, true), new Target(true, true)), new Attack(1, 2, 3)));
    }
}
