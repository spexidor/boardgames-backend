package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GearGrid {

    @Id
    private Long id;

    private final int WIDTH=3;
    private final int HEIGHT= 3;
    private final int MAX_GEAR = WIDTH*HEIGHT+1;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Survivor survivor;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Gear> gearList;

    public GearGrid(){

        this.gearList = new ArrayList<>();
        this.gearList.add(new Weapon("Fist and tooth", 2, 7, 1));
        this.gearList.add(new Armour("Cloth", 0, 0, 0, 1, 0));
        this.gearList.add(new Weapon("Founding stone", 2, 6, 2));
    }

    public Long getId() { return id; }

    public int getMAX_GEAR() { return MAX_GEAR; }

    public Survivor getSurvivor() { return survivor; }

    public void setSurvivor(Survivor survivor) { this.survivor = survivor; }

    public List<Gear> getGearList() { return gearList; }
}
