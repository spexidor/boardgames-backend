package se.hiq.boardgamesbackend.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
import se.hiq.boardgamesbackend.monster.types.TestLion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Monster {

    @Id
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private Coordinate position;

    @Enumerated(EnumType.STRING)
    private Facing facing;

    public Monster(){
        this.position = new Coordinate(10, 6); //Center of board
        this.facing = Facing.UP;
        this.monsterStatline = new TestLion();
    }

    @OneToOne
    @MapsId
    @JsonIgnore
    private Showdown showdown;

    @Transient
    public MonsterStatline monsterStatline;

    public Long getId() {
        return id;
    }

    public CoordinateList getMovementOptions(Board board){
        CoordinateList positionList = new CoordinateList(this.position);
        positionList.addSteps(3);

        return positionList;
    }

    public boolean validUpdate(Monster monster){

        Coordinate newPos = monster.getPosition();
        if(getMovementOptions(new Board()).hasCoordinate(newPos)){
            return true;
        }
        else {
            return false;
        }
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

    public Coordinate getPosition() {
        return position;
    }

    public Facing getFacing() {
        return facing;
    }

    public Showdown getShowdown() {
        return showdown;
    }

    public void setShowdown(Showdown showdown) {
        this.showdown = showdown;
    }
}
