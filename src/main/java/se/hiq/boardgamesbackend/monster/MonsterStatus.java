package se.hiq.boardgamesbackend.monster;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPosition;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPositionList;

import javax.persistence.*;

@Entity
public class MonsterStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MonsterPosition monsterPosition;

    public MonsterStatus(){
        this.id = 0L;
        this.monsterPosition = new MonsterPosition(0, 0);
    }

    public Long getId() {
        return id;
    }

    public MonsterPosition getPosition() {
        return monsterPosition;
    }

    public void setPosition(MonsterPosition monsterPosition) {
        this.monsterPosition = monsterPosition;
    }

    public MonsterPositionList getMovementOptions(Board board){
        MonsterPositionList monsterPositionList = new MonsterPositionList(this.monsterPosition);
        monsterPositionList.addMultipleSteps(3, board);

        return monsterPositionList;
    }

    public boolean validUpdate(MonsterStatus monsterStatus){

        MonsterPosition newPos = monsterStatus.getPosition();
        if(getMovementOptions(new Board()).hasMonsterPosition(newPos)){
            return true;
        }
        else {
            return false;
        }
    }

    public MonsterPosition getMonsterPosition() {
        return monsterPosition;
    }

    public void setMonsterPosition(MonsterPosition monsterPosition) {
        this.monsterPosition = monsterPosition;
    }
}
