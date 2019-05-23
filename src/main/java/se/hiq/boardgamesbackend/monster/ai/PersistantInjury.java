package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
public class PersistantInjury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEffect cardEffect;

    private PersistantInjury(){

    }
    PersistantInjury(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    void setCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
    }
}
