package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
public class CriticalWound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private NegativeToken negativeToken;

    @OneToOne(cascade = CascadeType.ALL)
    private HLCardTableResult hlCardTableResult;

    public CriticalWound() {
    }
    public CriticalWound(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NegativeToken getNegativeToken() {
        return negativeToken;
    }

    public void setNegativeToken(NegativeToken negativeToken) {
        this.negativeToken = negativeToken;
    }

    public HLCardTableResult getHlCardTableResult() {
        return hlCardTableResult;
    }

    public void setHlCardTableResult(HLCardTableResult hlCardTableResult) {
        this.hlCardTableResult = hlCardTableResult;
    }
}
