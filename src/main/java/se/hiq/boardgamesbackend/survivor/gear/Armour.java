package se.hiq.boardgamesbackend.survivor.gear;

import se.hiq.boardgamesbackend.dice.HitlocationType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Armour extends Gear {

    @Enumerated(EnumType.STRING)
    private HitlocationType type;
    private int hitpoints;

    private Armour(){
        this("AUTO-GEN ARMOUR", HitlocationType.HEAD, 0);
    }

    public Armour(String name, HitlocationType type, int hitpoints){
     super(name);
     this.type = type;
     this.hitpoints = hitpoints;
    }

    public HitlocationType getType() {
        return type;
    }

    public void setType(HitlocationType type) {
        this.type = type;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }
}
