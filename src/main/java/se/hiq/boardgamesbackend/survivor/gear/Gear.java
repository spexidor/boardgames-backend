package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.*;

@Entity
public class Gear {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private GearGrid gearGrid; //Parent gearGrid

    private Gear(){
        this("AUTO-GEN GEAR");
    }
    public Gear(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public GearGrid getGearGrid() {
        return gearGrid;
    }

    public void setGearGrid(GearGrid gearGrid) {
        this.gearGrid = gearGrid;
    }
}
