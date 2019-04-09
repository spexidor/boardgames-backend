package se.hiq.boardgamesbackend.monster;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
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
    private Coordinate position;

    @Enumerated(EnumType.STRING)
    private Facing facing;

    public MonsterStatus(){
        this.id = 0L;
        this.position = new Coordinate(10, 6); //Center of board
        this.facing = Facing.UP;
        this.monsterStatline = new TestLion();
    }

    @Transient
    public Monster monsterStatline;

    public Long getId() {
        return id;
    }

    public CoordinateList getMovementOptions(Board board){
        CoordinateList positionList = new CoordinateList(this.position);
        positionList.addMultipleSteps(3, board);

        return positionList;
    }

    public boolean validUpdate(MonsterStatus monsterStatus){

        Coordinate newPos = monsterStatus.getMonsterPosition();
        if(getMovementOptions(new Board()).hasCoordinate(newPos)){
            return true;
        }
        else {
            return false;
        }
    }

    public Coordinate getMonsterPosition() {
        return position;
    }

    public void setMonsterPosition(Coordinate position) {
        this.position = position;
    }

    public List<Coordinate> calculateBaseCoordinates() {
        List<Coordinate> baseCoordinates = new ArrayList<>();
        int x = this.position.getX();
        int y = this.position.getY();

        for(int i=0;i<this.monsterStatline.width; i++){
            for(int j=0;j<this.monsterStatline.height; j++){
                baseCoordinates.add(new Coordinate(x+i, y+j));
            }
        }
        return  baseCoordinates;
    }
}
