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

    @OneToOne
    @MapsId
    @JsonIgnore
    private Survivor survivor;

    @OneToMany(cascade= CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Gear> gear;

    public GearGrid(){

        this.gear = new ArrayList<>();

        Weapon fistAndTooth = new Weapon("Fist and tooth", 2, 8, 0);
        fistAndTooth.getAttackProfiles().get(0).setDeadly(true);
        fistAndTooth.setDescription("Deadly: +1 luck when wounding with this weapon");
        fistAndTooth.setGearGrid(this);

        Armour cloth = new Armour("Cloth", HitlocationType.WAIST, 1);
        cloth.setGearGrid(this);

        Weapon foundingStone = new Weapon("Founding stone", 2, 7, 1);
        foundingStone.setDescription("Spend ⚡ to Sling the stone. Archive this card for 1 automatic hit that inflicts a critical wound.");
        AttackProfile throwStone = new AttackProfile();
        throwStone.setActivationCost(new ActivationCost(true, false, true));
        throwStone.setSpeed(1);
        throwStone.setAlwaysCrits(true);
        throwStone.setAlwaysHits(true);
        throwStone.setInfiniteReach(true);
        throwStone.setUseName("Sling");
        foundingStone.getAttackProfiles().add(throwStone);
        foundingStone.setGearGrid(this);

        this.gear.add(fistAndTooth);
        this.gear.add(cloth);
        this.gear.add(foundingStone);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
