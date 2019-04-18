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
    private List<Target> targetList;

    public TargetRule(){
        this(new Target());
    }

    public TargetRule(Target... targets){
        this.targetList = new ArrayList<>();
        for(Target n: targets){
            this.targetList.add(n);
        }
    }

    public List<Target> getTargetList() {
        return targetList;
    }

    public AICard getAiCard() {
        return aiCard;
    }

    public void setAiCard(AICard aiCard) {
        this.aiCard = aiCard;
    }
}
