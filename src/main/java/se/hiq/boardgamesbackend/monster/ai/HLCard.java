package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hl_card")
public class HLCard extends Card{
    public boolean hasReaction;
    public boolean hasFailure;
    public boolean hasWound;
    public boolean trap;

    public HLCard(){
        this("AUTO-GEN HLCARD", false, false, false, false);
    }
    public HLCard(String title, boolean hasReaction, boolean hasFailure, boolean hasWound, boolean trap){

        this.title = title;
        this.hasReaction = hasReaction;
        this.hasFailure = hasFailure;
        this.hasWound = hasWound;
        this.trap = trap;
    }

    public boolean isHasReaction() {
        return hasReaction;
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
