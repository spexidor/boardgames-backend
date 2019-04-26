package se.hiq.boardgamesbackend.survivor.gear;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hitlocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JsonIgnore
    private Survivor survivor;

    @Enumerated(EnumType.STRING)
    private HitlocationType type;

    private boolean lightInjury;
    private boolean heavyInjury;
    private int hitpoints;

    private Hitlocation() { }

    public Hitlocation(HitlocationType type) {
        this.type = type;
    }

    public Hitlocation(HitlocationType type, List<Gear> gearList, Survivor survivor){

        this.type = type;
        if(type == HitlocationType.BRAIN){
            this.lightInjury = true;
        }
        for(Gear n: gearList){
            if(n instanceof Armour){
                if(type == ((Armour) n).getType()){
                 this.hitpoints+=((Armour) n).getHitpoints();
                }
            }
        }
        this.lightInjury = true; //TODO: remove this
        this.heavyInjury = true; //TODO: remove this
        this.survivor = survivor;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) { this.id = id; }

    public Survivor getSurvivor() {
        return survivor;
    }
    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
    }

    public HitlocationType getType() {
        return type;
    }
    public void setType(HitlocationType type) {
        this.type = type;
    }

    public boolean isLightInjury() {
        return lightInjury;
    }
    public void setLightInjury(boolean lightInjury) {
        this.lightInjury = lightInjury;
    }

    public boolean isHeavyInjury() {
        return heavyInjury;
    }
    public void setHeavyInjury(boolean heavyInjury) {
        this.heavyInjury = heavyInjury;
    }

    public int getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

}
