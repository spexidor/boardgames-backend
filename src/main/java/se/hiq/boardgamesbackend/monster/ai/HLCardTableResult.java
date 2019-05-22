package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;
import java.util.List;

@Entity
public class HLCardTableResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HLCardEffect> hlCardEffectList; //one entry for each result
    private int[] tableIndexes; //array indexes corresponding to above list. Random value is picked here.
}
