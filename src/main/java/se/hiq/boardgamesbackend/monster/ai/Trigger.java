package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
}
