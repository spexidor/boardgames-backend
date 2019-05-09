package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AICard extends Card{

    @OneToOne(cascade= CascadeType.ALL)
    private TargetRule targetRule;

    @OneToOne(cascade=CascadeType.ALL)
    private Attack attack;

    private boolean noMove;

    private boolean mood;

    private AICard(){
        this("AUTO-GEN AICARD", new TargetRule(), new Attack());
    }
    public AICard(String title, TargetRule targetRule, Attack attack){
        this.title = title;
        this.targetRule = targetRule;
        this.targetRule.setAiCard(this);
        this.attack = attack;
        this.attack.setAiCard(this);
    }

    public TargetRule getTargetRule() {
        return targetRule;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setTargetRule(TargetRule targetRule) {
        this.targetRule = targetRule;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public boolean isNoMove() {
        return noMove;
    }

    public void setNoMove(boolean noMove) {
        this.noMove = noMove;
    }
}
