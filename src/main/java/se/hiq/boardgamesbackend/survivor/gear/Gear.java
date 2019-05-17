package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Gear {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private final String name;
    private final String type;

    @ManyToOne
    @JsonIgnore
    private GearGrid gearGrid; //Parent gearGrid

    private String description;

    protected Gear(){
        this("AUTO-GEN GEAR", "GEAR");
    }
    Gear(String name, String type){
        this.type = type;
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

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
