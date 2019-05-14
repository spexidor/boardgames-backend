package se.hiq.boardgamesbackend.survivor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.board.MovementHelper;
import se.hiq.boardgamesbackend.survivor.gear.GearGrid;
import se.hiq.boardgamesbackend.survivor.gear.Hitlocation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Showdown showdown; //Parent showdown

    private String name;
    @Enumerated(EnumType.STRING)
    private SurvivorStatus status;

    private int activationsLeft;
    private int movesLeft;

    @ManyToOne//(cascade=CascadeType.ALL)
    private Coordinate position;

    private int movement;
    private int survival;
    private int bleed;
    private boolean priorityTarget;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Hitlocation> hitlocations;

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
        this.status = SurvivorStatus.STANDING;
        this.activationsLeft = 1;
        this.movesLeft = 1;
        this.position = position;
        this.movement = 5;
        this.gearGrid = new GearGrid();
        this.gearGrid.setSurvivor(this);
        this.hitlocations = createHitLocations();
        this.survival = 1;
        this.bleed = 0;
    }

    private List<Hitlocation> createHitLocations() {
        List<Hitlocation> hitlocations = new ArrayList<>();

        hitlocations.add(new Hitlocation(HitlocationType.HEAD, this.gearGrid.getGear(), this));
        hitlocations.add(new Hitlocation(HitlocationType.BODY, this.gearGrid.getGear(), this));
        hitlocations.add(new Hitlocation(HitlocationType.ARMS, this.gearGrid.getGear(), this));
        hitlocations.add(new Hitlocation(HitlocationType.LEGS, this.gearGrid.getGear(), this));
        hitlocations.add(new Hitlocation(HitlocationType.WAIST, this.gearGrid.getGear(), this));
        hitlocations.add(new Hitlocation(HitlocationType.BRAIN, this.gearGrid.getGear(), this));

        return hitlocations;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
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
    public GearGrid getGearGrid() { return gearGrid; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHitlocations(List<Hitlocation> hitlocations) { this.hitlocations = hitlocations; }
    public void setActivationsLeft(int activationsLeft) { this.activationsLeft = activationsLeft; }
    public void setMovement(int movement) { this.movement = movement; }
    public void setSurvival(int survival) { this.survival = survival; }
    public void setGearGrid(GearGrid gearGrid) { this.gearGrid = gearGrid; }

    public int getBleed() { return bleed; }
    public void setBleed(int bleed) { this.bleed = bleed; }

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

    public void updateState(Survivor newState){
        System.out.println("Updating values in survivor, position: " +this.position +", id: " +this.id);

        this.name = newState.name;
        this.status = newState.status;
        this.hitlocations = newState.hitlocations;

        this.activationsLeft = newState.activationsLeft;
        this.movesLeft = newState.movesLeft;
        this.position = newState.position;
        this.movement = newState.movement;
        this.survival = newState.survival;
        this.bleed = newState.bleed;
        //this.gearGrid = newState.gearGrid; //read only in json, update with own controller
    }

    public List<Hitlocation> getHitlocations() {
        return hitlocations;
    }

    public SurvivorStatus getStatus() {
        return status;
    }

    public void setStatus(SurvivorStatus status) {
        this.status = status;
    }

    public boolean isPriorityTarget() {
        return priorityTarget;
    }

    public void setPriorityTarget(boolean priorityTarget) {
        this.priorityTarget = priorityTarget;
    }
}
