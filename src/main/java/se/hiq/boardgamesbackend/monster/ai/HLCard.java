package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hl_card")
public class HLCard extends Card{
    public boolean hasReflex;
    public boolean hasFailure;
    public boolean hasWound;
    public boolean trap;

    private HLCard(){
        this("AUTO-GEN HLCARD", false, false, false, false);
    }
    public HLCard(String title, boolean hasReflex, boolean hasFailure, boolean hasWound, boolean trap){

        this.title = title;
        this.hasReflex = hasReflex;
        this.hasFailure = hasFailure;
        this.hasWound = hasWound;
        this.trap = trap;
    }

    public boolean isHasReflex() {
        return hasReflex;
    }

    public boolean isHasFailure() {
        return hasFailure;
    }

    public boolean isHasWound() {
        return hasWound;
    }

    public boolean isTrap() {
        return trap;
    }
}
