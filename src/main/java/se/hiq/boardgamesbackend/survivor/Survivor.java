package se.hiq.boardgamesbackend.survivor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean isAlive;

    private int activationsLeft;
    private int movesLeft;

    private int survival;
    private int insanity;
    private int armourHead;
    private int armourArms;
    private int armourTorso;
    private int armourWaist;
    private int amourLegs;

    public Survivor(){
        this("By default constructor");
    }

    public Survivor(String s) {
        this.name = s;
        this.isAlive = true;
        this.activationsLeft = 1;
        this.movesLeft = 1;
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

    public int getAmourLegs() {
        return amourLegs;
    }
}
