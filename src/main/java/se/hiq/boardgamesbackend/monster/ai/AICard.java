package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class AICard extends Card{

    @OneToOne(cascade= CascadeType.ALL)
    private TargetRule targetRule;

    @OneToOne(cascade=CascadeType.ALL)
    private Attack attack;

    public AICard(){
        this("AUTO-GEN AICARD", new TargetRule(), new Attack());
    }
    public AICard(String title, TargetRule targetRule, Attack attack){
        this.title = title;
        this.targetRule = targetRule;
        this.targetRule.setAiCard(this);
        this.attack = attack;
        this.attack.setAiCard(this);
    }
}
