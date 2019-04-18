package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    @JsonIgnore
    private Deck deck;

    protected String title;

    public Card(){
        this("AUTO-GEN CARD");
    }
    public Card(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
