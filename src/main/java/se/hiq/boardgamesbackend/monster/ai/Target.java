package se.hiq.boardgamesbackend.monster.ai;

import se.hiq.boardgamesbackend.game.coordinates.MovementHelper;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public boolean closest;
    public boolean threat;
    public boolean facing;
    public boolean inRange;
    public boolean inFieldOfView;
    public boolean knockedDown;
    public boolean blindSpot;
    public boolean random;

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
        this(closest, threat, facing, inRange, false, false, false, false);
    }
    public Target(boolean closest, boolean threat, boolean facing, boolean inRange, boolean knockedDown, boolean blindSpot, boolean inFieldOfView, boolean random){
        this.closest = closest;
        this.threat = threat;
        this.facing = facing;
        this.inRange = inRange;
        this.knockedDown = knockedDown;
        this.blindSpot = blindSpot;
        this.inFieldOfView = inFieldOfView;
        this.random = random;
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

    public boolean validTarget(Monster monster, List<Survivor> survivors, Survivor chosenSurvivor){


        //Threat
        if(this.threat && chosenSurvivor.isKnockedDown()){
            System.out.println("Target (" +chosenSurvivor.getName() +") not threat");
            return false;
        }

        //Closest?
        if(this.closest && !chosenSurvivorClosest(monster, survivors, chosenSurvivor)){
            System.out.println("Target (" +chosenSurvivor.getName() +") not closest");
            return false;
        }

        //Facing
        if(this.facing && !monster.facingSurvivor(chosenSurvivor)) {
            System.out.println("Target (" +chosenSurvivor.getName() +") not facing");
            return false;
        }

        //In range
        if(this.inRange && MovementHelper.distance(chosenSurvivor.getPosition(), monster.getBaseCoordinates()) > monster.getStatline().getMovement()){
            System.out.println("Target (" +chosenSurvivor.getName() +") not in range");
            return false;
        }

        //Knocked down
        if(this.knockedDown && !chosenSurvivor.isKnockedDown()){
            System.out.println("Target (" +chosenSurvivor.getName() +") not knocked down");
            return false;
        }

        //Blind spot
        if(this.blindSpot && !MovementHelper.coordinateInList(monster.getBlindspot(), chosenSurvivor.getPosition())){
            System.out.println("Target (" +chosenSurvivor.getName() +") not in blind spot");
            return false;
        }

        return true;
    }

    private boolean chosenSurvivorClosest(Monster monster, List<Survivor> survivors, Survivor chosenSurvivor) {
        int distance = MovementHelper.distance(chosenSurvivor.getPosition(), monster.getBaseCoordinates());
        //System.out.println("Evaluating "+chosenSurvivor.getName() +" as closest survivor. Distance is " +distance);
        for(Survivor s: survivors){
            //System.out.println("Checking if " +s.getName() +" is closer. " +MovementHelper.distance(s.getPosition(), monster.getBaseCoordinates()));
            if(MovementHelper.distance(s.getPosition(), monster.getBaseCoordinates()) < distance){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        String str = "";

        if(this.closest){
            str = "closest ";
        }
        if(this.threat){
            str = str +" threat, ";
        }
        if(this.facing) {
            str = str +" facing, ";
        }

        if(this.inRange){
            str = str +" in range, ";
        }

        if(this.knockedDown){
            str = str +" knocked down, ";
        }

        if(this.blindSpot){
            str = str +" in blindspot";
        }

        return str;
    }


    public void setClosest(boolean closest) {
        this.closest = closest;
    }

    public void setThreat(boolean threat) {
        this.threat = threat;
    }

    public void setFacing(boolean facing) {
        this.facing = facing;
    }

    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }

    public boolean isInFieldOfView() {
        return inFieldOfView;
    }

    public void setInFieldOfView(boolean inFieldOfView) {
        this.inFieldOfView = inFieldOfView;
    }

    public void setKnockedDown(boolean knockedDown) {
        this.knockedDown = knockedDown;
    }

    public void setBlindSpot(boolean blindSpot) {
        this.blindSpot = blindSpot;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}
