package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.*;

@Entity
@Table(name = "attack_trigger")
public class Trigger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean afterDamage;
    private boolean afterHit;

    public Trigger() {
        this(false, false);
    }

    public Trigger(boolean afterDamage, boolean afterHit) {
        this.afterDamage = afterDamage;
        this.afterHit = afterHit;
    }

    public boolean isAfterDamage() {
        return afterDamage;
    }

    public boolean isAfterHit() {
        return afterHit;
    }
}
