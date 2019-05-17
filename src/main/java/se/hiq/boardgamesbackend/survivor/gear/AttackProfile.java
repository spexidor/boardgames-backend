package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AttackProfile {

    @Id
    @GeneratedValue
    long id;

    @ManyToOne
    @JsonIgnore
    private Weapon weapon;

    @OneToOne(cascade = CascadeType.ALL)
    private ActivationCost activationCost;

    private int speed;
    private int toHitValue;
    private int strengthBonus;

    private boolean deadly;
    private int reach;
    private boolean alwaysHits;
    private boolean alwaysCrits;
    private boolean infiniteReach;
    private String useName;

    public AttackProfile() {
    }

    public AttackProfile(int speed, int toHitValue, int strengthBonus) {
        this.speed = speed;
        this.toHitValue = toHitValue;
        this.strengthBonus = strengthBonus;

        this.activationCost = new ActivationCost(true, false, false);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getToHitValue() {
        return toHitValue;
    }

    public void setToHitValue(int toHitValue) {
        this.toHitValue = toHitValue;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public void setStrengthBonus(int strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    public boolean isDeadly() {
        return deadly;
    }

    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public ActivationCost getActivationCost() {
        return activationCost;
    }

    public void setActivationCost(ActivationCost activationCost) {
        this.activationCost = activationCost;
    }

    public boolean isAlwaysHits() {
        return alwaysHits;
    }

    public void setAlwaysHits(boolean alwaysHits) {
        this.alwaysHits = alwaysHits;
    }

    public boolean isAlwaysCrits() {
        return alwaysCrits;
    }

    public void setAlwaysCrits(boolean alwaysCrits) {
        this.alwaysCrits = alwaysCrits;
    }

    public boolean isInfiniteReach() {
        return infiniteReach;
    }

    public void setInfiniteReach(boolean infiniteReach) {
        this.infiniteReach = infiniteReach;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }
}
