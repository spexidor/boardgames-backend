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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Card> cardsInDeck;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Card> cardsInDiscard;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Card> cardsRemoved;

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
}
