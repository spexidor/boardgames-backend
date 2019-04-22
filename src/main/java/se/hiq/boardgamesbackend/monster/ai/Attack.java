package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JsonIgnore
    private AICard aiCard;

    private int speed;
    private int toHitValue;
    private int damage;
    private boolean brainDamage;
    private boolean ignoreEvasion;

    @OneToOne
    private Trigger trigger;

    @OneToOne
    private TriggerEffect triggerEffect;

    public Attack(){
        this(0, 0, 0);
    }

    public Attack(int speed, int toHitValue, int damage){
        this.speed = speed;
        this.toHitValue = toHitValue;
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getToHitValue() {
        return toHitValue;
    }

    public int getDamage() {
        return damage;
    }

    public void setAiCard(AICard aiCard) {
        this.aiCard = aiCard;
    }

    public boolean isBrainDamage() {
        return brainDamage;
    }

    public void setBrainDamage(boolean brainDamage) {
        this.brainDamage = brainDamage;
    }

    public boolean isIgnoreEvasion() {
        return ignoreEvasion;
    }

    public void setIgnoreEvasion(boolean ignoreEvasion) {
        this.ignoreEvasion = ignoreEvasion;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public TriggerEffect getTriggerEffect() {
        return triggerEffect;
    }

    public void setTriggerEffect(TriggerEffect triggerEffect) {
        this.triggerEffect = triggerEffect;
    }
}
