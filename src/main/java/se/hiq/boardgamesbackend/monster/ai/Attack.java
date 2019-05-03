package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;

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
    private boolean ignoreEvasion;
    private int reach; // -1 for unlimited

    @Enumerated(EnumType.STRING)
    private HitlocationType targetLocation;

    @OneToOne(cascade = CascadeType.ALL)
    private Trigger trigger;

    @OneToOne(cascade = CascadeType.ALL)
    private TriggerEffect triggerEffect;

    public Attack(){
        this(0, 0, 0);
    }

    public Attack(int speed, int toHitValue, int damage){
        this.speed = speed;
        this.toHitValue = toHitValue;
        this.damage = damage;
        this.reach = 1;
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

    public AICard getAiCard() { return aiCard; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void setToHitValue(int toHitValue) { this.toHitValue = toHitValue; }

    public void setDamage(int damage) { this.damage = damage; }

    public int getReach() { return reach; }

    public void setReach(int reach) { this.reach = reach; }

    public HitlocationType getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(HitlocationType targetLocation) {
        this.targetLocation = targetLocation;
    }
}
