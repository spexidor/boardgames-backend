package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class TargetRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private AICard aiCard;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Target> targetOrder;

    public TargetRule(){
        this(new Target());
    }

    public TargetRule(Target... targets){
        this.targetOrder = new ArrayList<>();
        this.targetOrder.addAll(Arrays.asList(targets));
    }

    public List<Target> getTargetOrder() {
        return targetOrder;
    }

    public AICard getAiCard() {
        return aiCard;
    }

    public void setAiCard(AICard aiCard) {
        this.aiCard = aiCard;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("TargetRule id=" + this.id);
        int index = 0;
        for(Target n: this.targetOrder){
            str.append("Rule ").append(index).append(": ").append(n.toString());
        }

        return str.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTargetOrder(List<Target> targetOrder) {
        this.targetOrder = targetOrder;
    }
}
