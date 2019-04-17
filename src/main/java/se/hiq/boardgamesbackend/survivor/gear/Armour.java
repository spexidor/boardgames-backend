package se.hiq.boardgamesbackend.survivor.gear;

import javax.persistence.Entity;

@Entity
public class Armour extends Gear {

    private int head;
    private int arms;
    private int torso;
    private int waist;
    private int legs;

    public Armour(){
        this("AUTO-GEN ARMOUR", 0, 0, 0, 0, 0);
    }

    public Armour(String name, int head, int arms, int torso, int waist, int legs){
     super(name);
     this.head = head;
     this.arms = arms;
     this.torso = torso;
     this.waist = waist;
     this.legs = legs;
    }

    public int getHead() {
        return head;
    }

    public int getArms() {
        return arms;
    }

    public int getTorso() {
        return torso;
    }

    public int getWaist() {
        return waist;
    }

    public int getLegs() {
        return legs;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setArms(int arms) {
        this.arms = arms;
    }

    public void setTorso(int torso) {
        this.torso = torso;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
}
