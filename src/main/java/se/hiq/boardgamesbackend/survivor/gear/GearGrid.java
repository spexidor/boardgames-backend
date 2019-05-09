package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToOne
    @MapsId
    @JsonIgnore
    private Survivor survivor;

    @OneToMany(cascade= CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Gear> gear;

    public GearGrid(){

        this.gear = new ArrayList<>();
        this.gear.add(new Weapon("Fist and tooth", 2, 7, 0));
        this.gear.add(new Armour("Cloth", HitlocationType.WAIST, 1));
        this.gear.add(new Weapon("Founding stone", 2, 6, 1));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    //public int getMAX_GEAR() { return MAX_GEAR; }

    public Survivor getSurvivor() { return survivor; }

    public void setSurvivor(Survivor survivor) { this.survivor = survivor; }

    public List<Gear> getGear() { return gear; }



    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getMAX_GEAR() {
        return WIDTH * HEIGHT + 1;
    }

    public void setGear(List<Gear> gear) {
        this.gear = gear;
    }
}
