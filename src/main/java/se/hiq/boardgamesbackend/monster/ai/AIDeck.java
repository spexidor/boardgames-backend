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

        this.basicAction = AICardBuilder.getCardByName("Basic Action", monsterLevel);
        //this.cardsInDeck.add(AICardBuilder.getCardByName("Claw", monsterLevel));
        //this.cardsInDeck.add(AICardBuilder.getCardByName("Size Up", monsterLevel));
        //this.cardsInDeck.add(AICardBuilder.getCardByName("Grasp", monsterLevel));


        this.cardsInDeck.add(AICardBuilder.getCardByName("Size Up", monsterLevel));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Size Up", monsterLevel));
        this.cardsInDeck.add(AICardBuilder.getCardByName("Size Up", monsterLevel));

    }

    public AICard getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(AICard basicAction) { this.basicAction = basicAction; }
}
