package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;
import java.util.List;

@Entity
public class HLCardTableResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CardEffect> cardEffects; //one entry for each result

    @ElementCollection
    private List<Integer> tableIndexes; //array indexes corresponding to above list. Random value is picked here.

    public HLCardTableResult(){
        this.cardEffects = null;
        this.tableIndexes = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CardEffect> getCardEffects() {
        return cardEffects;
    }

    public void setCardEffects(List<CardEffect> cardEffectTable) {
        this.cardEffects = cardEffectTable;
    }

    public List<Integer> getTableIndexes() {
        return tableIndexes;
    }

    public void setTableIndexes(List<Integer> tableIndexes) {
        this.tableIndexes = tableIndexes;
    }
}
