package se.hiq.boardgamesbackend.survivor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.MovementHelper;
import se.hiq.boardgamesbackend.survivor.gear.GearGrid;
import se.hiq.boardgamesbackend.survivor.gear.Hitpoints;

import javax.persistence.*;
import java.util.List;

@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Showdown showdown; //Parent showdown

    private String name;
    private boolean isAlive;
    private boolean knockedDown;
    private boolean lightInjury;
    private boolean heavyInjury;

    @OneToOne(mappedBy = "survivor", cascade=CascadeType.ALL)
    private Hitpoints hitpoints;

    @ManyToOne//(cascade=CascadeType.ALL)
    private Coordinate position;

    private int activationsLeft;
    private int movesLeft;

    private int movement;

    private int survival;
    private int insanity;

    @OneToOne(mappedBy = "survivor", cascade=CascadeType.ALL)
    private GearGrid gearGrid;

    public Survivor(){
        this("By default constructor");
    }

    public Survivor(String s) {
       this(s, new Coordinate(0,0));
    }

    public Survivor(String s, Coordinate position) {
        this.name = s;
        this.isAlive = true;
        this.activationsLeft = 1;
        this.movesLeft = 1;
        this.position = position;
        this.movement = 5;
        this.gearGrid = new GearGrid();
        this.gearGrid.setSurvivor(this);
        this.hitpoints = new Hitpoints(this.gearGrid.getGear());
        this.hitpoints.setSurvivor(this);
        this.knockedDown = false;
        this.survival = 1;
        this.insanity = 0;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public int getActivationsLeft() {
        return activationsLeft;
    }
    public int getMovesLeft() {
        return movesLeft;
    }
    public void setMovesLeft(int movesLeft) { this.movesLeft = movesLeft; }
    public int getSurvival() {
        return survival;
    }
    public int getInsanity() {
        return insanity;
    }
    public GearGrid getGearGrid() { return gearGrid; }


    public boolean validUpdate(Survivor newSurvivorState) {

        /*
        System.out.println("Checking survivor valid state...");
        List<Coordinate> movementOptions = movementOptions();
        boolean match = false;

        for(Coordinate n: movementOptions){
            if(n.equals(newSurvivorState.getPosition()))
            {
                System.out.println("Valid move found...");
                match = true;
                break;
            }
        }
        System.out.println("Valid state: " +match);
        return match;
        */
        return true;
    }

    public Coordinate getPosition() {
        return position;
    }
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    public List<Coordinate> movementOptions() {
        return MovementHelper.getSurvivorMovement(this, showdown.getSurvivors(), showdown.getMonster());
    }

    public int getMovement() {
        return movement;
    }
    public Showdown getShowdown() {
        return showdown;
    }
    public void setShowdown(Showdown showdown) {
        this.showdown = showdown;
    }
    public boolean isKnockedDown() {
        return knockedDown;
    }

    public void updateState(Survivor newState){
        System.out.println("Updating values in survivor, position: " +this.position +", id: " +this.id);

        this.isAlive = newState.isAlive;
        this.activationsLeft = newState.activationsLeft;
        this.movesLeft = newState.movesLeft;
        this.position = newState.position;
        this.movement = newState.movement;
        this.knockedDown = newState.knockedDown;
        this.survival = newState.survival;
        this.insanity = newState.insanity;
        this.lightInjury = newState.lightInjury;
        this.heavyInjury = newState.heavyInjury;
    }

    public boolean isLightInjury() {
        return lightInjury;
    }

    public boolean isHeavyInjury() {
        return heavyInjury;
    }

    public Hitpoints getHitpoints() {
        return hitpoints;
    }
}
