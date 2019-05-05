package se.hiq.boardgamesbackend.monster.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Direction direction;
    private int length;
    private boolean fullMove;

    @JsonIgnore
    @OneToOne
    private CardEffect cardEffect;

    private Move() {
    }

    public Move(Direction direction, boolean fullMove, CardEffect cardEffect) {
        this.direction = direction;
        this.fullMove = fullMove;
        this.cardEffect = cardEffect;
    }

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public void setCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
    }

    public boolean isFullMove() {
        return fullMove;
    }

    public void setFullMove(boolean fullMove) {
        this.fullMove = fullMove;
    }
}
