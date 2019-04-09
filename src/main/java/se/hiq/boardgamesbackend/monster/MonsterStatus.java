package se.hiq.boardgamesbackend.monster;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPosition;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPositionList;
import se.hiq.boardgamesbackend.monster.types.TestLion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MonsterStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private MonsterPosition monsterPosition;

    public MonsterStatus(){
        this.id = 0L;
        this.monsterPosition = new MonsterPosition(0, 0);
        this.monsterStatline = new TestLion();
    }

    @Transient
    public Monster monsterStatline;

    public Long getId() {
        return id;
    }

    public MonsterPositionList getMovementOptions(Board board){
        MonsterPositionList monsterPositionList = new MonsterPositionList(this.monsterPosition);
        monsterPositionList.addMultipleSteps(3, board);

        return monsterPositionList;
    }

    public boolean validUpdate(MonsterStatus monsterStatus){

        MonsterPosition newPos = monsterStatus.getMonsterPosition();
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

    public List<Coordinate> calculateBaseCoordinates() {
        List<Coordinate> baseCoordinates = new ArrayList<>();
        int x = this.monsterPosition.getX();
        int y = this.monsterPosition.getY();

        for(int i=0;i<this.monsterStatline.width; i++){
            for(int j=0;j<this.monsterStatline.height; j++){
                baseCoordinates.add(new Coordinate(x+i, y+j));
            }
        }
        return  baseCoordinates;
    }
}
