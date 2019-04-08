package se.hiq.boardgamesbackend.monster;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class MonsterStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    private Coordinate position;
    @Enumerated(EnumType.STRING)
    private Facing facing;

    @Transient
    private Monster monsterType;

    public MonsterStatus(){
        this.id = 0L;
        this.position = new Coordinate(0, 0);
        this.facing = Facing.UP;
        //this.monsterType = new TestLion();
    }

    public Long getId() {
        return id;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Facing getFacing() {
        return facing;
    }

    public Monster getMonsterType() {
        return monsterType;
    }

    public CoordinateList getMovementOptions(Board board){
        CoordinateList coordinates = new CoordinateList(this.position);
        coordinates.addMultipleSteps(3, board);

        return coordinates;
    }

    public boolean validUpdate(MonsterStatus monsterStatus){

        Coordinate newPos = monsterStatus.getPosition();
        if(getMovementOptions(new Board(10, 10)).hasCoordinate(newPos)){
            return true;
        }
        else {
            return false;
        }
    }

}
