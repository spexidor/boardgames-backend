package se.hiq.boardgamesbackend.survivor;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.persistence.*;
import java.util.List;

@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
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
        return true;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public List<Coordinate> getMovementOptions(Board board, List<Survivor> otherSurvivors, Monster monster) {
        CoordinateList movementOpts = new CoordinateList(this.position);
        movementOpts.addSteps(this.movement, new Board());
        movementOpts.removeInvalidMovements(monster, otherSurvivors);
        return movementOpts.getCoordinateList();
    }

    public int getMovement() {
        return movement;
    }

    public Showdown getShowdown() {
        return showdown;
    }
}
