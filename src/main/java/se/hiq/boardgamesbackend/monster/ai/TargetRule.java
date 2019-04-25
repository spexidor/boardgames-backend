package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TargetRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        for(Target n: targets){
            this.targetOrder.add(n);
        }
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
        String str = "TargetRule id=" +this.id;
        int index = 0;
        for(Target n: this.targetOrder){
            str = str +"Rule " +index +": " +n.toString();
        }

        return str;
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
