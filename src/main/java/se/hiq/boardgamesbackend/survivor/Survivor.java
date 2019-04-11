package se.hiq.boardgamesbackend.survivor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.MovementHelper;

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

    @ManyToOne(cascade=CascadeType.ALL)
    private Coordinate position;

    private int activationsLeft;
    private int movesLeft;

    private int movement;

    private int survival;
    private int insanity;

    /*
    private int armourHead;
    private int armourArms;
    private int armourTorso;
    private int armourWaist;
    private int armourLegs;
    */

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

    public int getSurvival() {
        return survival;
    }

    public int getInsanity() {
        return insanity;
    }

    /*
    public int getArmourHead() {
        return armourHead;
    }

    public int getArmourArms() {
        return armourArms;
    }

    public int getArmourTorso() {
        return armourTorso;
    }

    public int getArmourWaist() {
        return armourWaist;
    }

    public int getArmourLegs() {
        return armourLegs;
    }
    */

    public boolean validUpdate(Survivor newSurvivorState) {

        List<Coordinate> movementOptions = movementOptions();
        boolean match = false;

        for(Coordinate n: movementOptions){
            if(n.equals(newSurvivorState.getPosition()))
            {
                match = true;
                break;
            }
        }
        return match;
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
}
