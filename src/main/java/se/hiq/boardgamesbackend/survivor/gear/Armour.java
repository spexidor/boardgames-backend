package se.hiq.boardgamesbackend.survivor.gear;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
class Armour extends Gear {

    @Enumerated(EnumType.STRING)
    private HitlocationType hitlocationType;
    private int hitpoints;

    private Armour(){
        this("AUTO-GEN ARMOUR", HitlocationType.HEAD, 0);
    }

    public Armour(String name, HitlocationType type, int hitpoints){
     super(name, "ARMOUR");
     this.hitlocationType = type;
     this.hitpoints = hitpoints;
    }

    public HitlocationType getHitlocationType () {
        return hitlocationType;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setHitlocationType(HitlocationType hitlocationType) {
        this.hitlocationType = hitlocationType;
    }
}
