package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public boolean closest;
    public boolean threat;
    public boolean facing;
    public boolean inRange;
    public boolean knockedDown;
    public boolean blindSpot;

    public Target(){
        this(false);
    }
    public Target(boolean closest){
        this(closest, false);
    }
    public Target(boolean closest, boolean threat){
        this(closest, threat, false);
    }
    public Target(boolean closest, boolean threat, boolean facing){
        this(closest, threat, facing, false);
    }
    public Target(boolean closest, boolean threat, boolean facing, boolean inRange){
        this(closest, threat, facing, inRange, false, false);
    }
    public Target(boolean closest, boolean threat, boolean facing, boolean inRange, boolean knockedDown, boolean blindSpot){
        this.closest = closest;
        this.threat = threat;
        this.facing = facing;
        this.inRange = inRange;
        this.knockedDown = knockedDown;
        this.blindSpot = blindSpot;
    }

    public long getId() {
        return id;
    }

    public boolean isClosest() {
        return closest;
    }

    public boolean isThreat() {
        return threat;
    }

    public boolean isFacing() {
        return facing;
    }

    public boolean isInRange() {
        return inRange;
    }

    public boolean isKnockedDown() {
        return knockedDown;
    }

    public boolean isBlindSpot() {
        return blindSpot;
    }
}
