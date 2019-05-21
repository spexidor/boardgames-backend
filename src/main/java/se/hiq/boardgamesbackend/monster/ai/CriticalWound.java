package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CriticalWound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private NegativeToken negativeToken;

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
}
