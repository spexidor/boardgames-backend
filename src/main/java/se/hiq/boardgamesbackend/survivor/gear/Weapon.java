package se.hiq.boardgamesbackend.survivor.gear;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
class Weapon extends Gear {

    @OneToMany(cascade = CascadeType.ALL)
    private List<AttackProfile> attackProfiles;

    private Weapon(){
        this("AUTO-GEN WEAPON", 1,2,10);
    }
    public Weapon(String name, int speed, int toHitValue, int strengthBonus){
        super(name, "WEAPON");

        attackProfiles = new ArrayList<>();
        attackProfiles.add(new AttackProfile(speed, toHitValue, strengthBonus));
    }

    public List<AttackProfile> getAttackProfiles() {
        return attackProfiles;
    }

    public void setAttackProfiles(List<AttackProfile> attackProfiles) {
        this.attackProfiles = attackProfiles;
    }
}
