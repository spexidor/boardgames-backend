package se.hiq.boardgamesbackend.survivor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Hitpoints {

    @Id
    private long id;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Survivor survivor;

    private int head;
    private int torso;
    private int arms;
    private int waist;
    private int legs;

    public Hitpoints() {
        this(0, 0, 0, 0, 0);
    }

    public Hitpoints(int head, int torso, int arms, int waist, int legs) {
        this.head = head;
        this.torso = torso;
        this.arms = arms;
        this.waist = waist;
        this.legs = legs;
    }

    public long getId() {
        return id;
    }

    public Survivor getSurvivor() {
        return survivor;
    }

    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTorso() {
        return torso;
    }

    public void setTorso(int torso) {
        this.torso = torso;
    }

    public int getArms() {
        return arms;
    }

    public void setArms(int arms) {
        this.arms = arms;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
}
