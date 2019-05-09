package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Simon och Ines byggplats

@Entity
class Deck {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("orderInDeck ASC")
    List<Card> cardsInDeck;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("orderInDeck ASC")
    private List<Card> cardsInDiscard;
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("orderInDeck ASC")
    private List<Card> cardsRemoved;

    @OneToOne
    @JsonIgnore
    private Monster monster;

    public Deck(){
        this.cardsInDeck = new ArrayList<>();
        this.cardsInDiscard = new ArrayList<>();
        this.cardsRemoved = new ArrayList<>();
    }

    public long getId() { return id; }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public List<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    public List<Card> getCardsInDiscard() {
        return cardsInDiscard;
    }

    public List<Card> getCardsRemoved() {
        return cardsRemoved;
    }

    void initCardOrder(){
        for(int n=0; n<this.getCardsInDeck().size(); n++){
            this.getCardsInDeck().get(n).setOrderInDeck(n);
        }
    }

    public void updateState(Deck updatedDeck) {

        System.out.println("Updating deck");
        if(updatedDeck.cardsInDeck != null){
            System.out.println("type check 1: " +this.cardsInDeck.getClass() +", " +updatedDeck.cardsInDeck.getClass());
            this.cardsInDeck = updatedDeck.cardsInDeck;
        }
        if(updatedDeck.cardsInDiscard != null){
            System.out.println("type check 2: " +this.cardsInDiscard.getClass() +", " +updatedDeck.cardsInDiscard.getClass());
            this.cardsInDiscard = updatedDeck.cardsInDiscard;
        }
        if(updatedDeck.cardsRemoved != null){
            this.cardsRemoved = updatedDeck.cardsRemoved;
        }
    }

    public void setCardsInDeck(List<Card> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public void setCardsInDiscard(List<Card> cardsInDiscard) {
        this.cardsInDiscard = cardsInDiscard;
    }

    public void setCardsRemoved(List<Card> cardsRemoved) {
        this.cardsRemoved = cardsRemoved;
    }

    public void setId(long id) {
        this.id = id;
    }
}
