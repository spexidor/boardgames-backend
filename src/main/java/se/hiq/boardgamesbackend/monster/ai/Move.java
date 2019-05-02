package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Direction direction;
    private boolean fullMove;
    private int length;

    @JsonIgnore
    @OneToOne
    private TriggerEffect triggerEffect;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isFullMove() {
        return fullMove;
    }

    public void setFullMove(boolean fullMove) {
        this.fullMove = fullMove;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TriggerEffect getTriggerEffect() {
        return triggerEffect;
    }

    public void setTriggerEffect(TriggerEffect triggerEffect) {
        this.triggerEffect = triggerEffect;
    }
}
