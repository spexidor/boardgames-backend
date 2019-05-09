package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AICard.class, name = "aicard"),
        @JsonSubTypes.Type(value = HLCard.class, name = "hlcard")
})

@Entity
abstract class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Deck deck;

    private int orderInDeck;

    String title;

    Card(){
        this.title = "AUTO-GEN";
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrderInDeck() { return orderInDeck; }

    public void setOrderInDeck(int orderInDeck) { this.orderInDeck = orderInDeck; }
}
