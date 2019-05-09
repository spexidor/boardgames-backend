package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AIDeck extends Deck {

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AICard basicAction;

    public AIDeck(){
        this(1);
    }

    public AIDeck(int monsterLevel){

        this.basicAction = AICardBuilder.getCardByName("Basic Action", monsterLevel, this);
        this.cardsInDeck.add(AICardBuilder.getCardByName("Claw", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Size Up", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Grasp", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Revenge", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Bat Around", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Vicious Claw", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Power Swat", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Combo Claw", monsterLevel, this));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Chomp", monsterLevel, this));

        this.initCardOrder();
    }

    public AICard getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(AICard basicAction) { this.basicAction = basicAction; }
}
