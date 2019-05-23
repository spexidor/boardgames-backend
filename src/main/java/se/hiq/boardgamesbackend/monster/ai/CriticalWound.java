package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
public class CriticalWound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private HLCardTableResult hlCardTableResult;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEffect cardEffect;

    private boolean persistantInjury;

    CriticalWound() {
    }
    CriticalWound(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HLCardTableResult getHlCardTableResult() {
        return hlCardTableResult;
    }

    void setHlCardTableResult(HLCardTableResult hlCardTableResult) {
        this.hlCardTableResult = hlCardTableResult;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public void setCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
    }

    public boolean isPersistantInjury() {
        return persistantInjury;
    }

    public void setPersistantInjury(boolean persistantInjury) {
        this.persistantInjury = persistantInjury;
    }
}
