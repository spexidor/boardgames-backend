package se.hiq.boardgamesbackend.survivor.gear;

import javax.persistence.Entity;

@Entity
class Weapon extends Gear {
    private int speed;
    private int toHitValue;
    private int strengthBonus;

    private Weapon(){
        this("AUTO-GEN WEAPON", 1,2,10);
    }
    public Weapon(String name, int speed, int toHitValue, int strengthBonus){
        super(name, "WEAPON");
        this.toHitValue = toHitValue;
        this.speed = speed;
        this.strengthBonus = strengthBonus;
    }

    public int getSpeed() {
        return speed;
    }

    public int getToHitValue() {
        return toHitValue;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setToHitValue(int toHitValue) {
        this.toHitValue = toHitValue;
    }

    public void setStrengthBonus(int strengthBonus) {
        this.strengthBonus = strengthBonus;
    }
}
