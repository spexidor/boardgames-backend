package se.hiq.boardgamesbackend.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.MovementHelper;
import se.hiq.boardgamesbackend.monster.types.TestLion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Monster {

    @Id
    private Long id;

    @ManyToOne//(cascade=CascadeType.ALL)
    private Coordinate position;

    @Enumerated(EnumType.STRING)
    private Facing facing;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Showdown showdown;

    @Transient
    public MonsterStatline statline;

    public Monster(){
        this.position = new Coordinate(6, 4); //Center of board
        this.facing = Facing.UP;
        this.statline = new TestLion();
    }

    public Long getId() {
        return id;
    }

    public List<Coordinate> movementOptions(){
        return MovementHelper.getMonsterMovement(this);
    }

    public boolean validUpdate(Monster newState){

        Coordinate newPos = newState.getPosition();
        if(MovementHelper.coordinateInList(MovementHelper.getMonsterMovement(this), newPos)){
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

        for(int i=0;i<this.statline.width; i++){
            for(int j=0;j<this.statline.height; j++){
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

    public MonsterStatline getStatline() { return statline; }

    /*
    public void fillNullValues(Monster monster) {
        System.out.println("Checking null values in monster, position: " +this.position +", facing: " +this.facing +", statline: " +this.statline);
        if(this.position == null){
            System.out.println("position null");
            this.position = monster.position;
        }
        if(this.facing == null){
            System.out.println("facing null");
            this.facing = monster.facing;
        }
        if(this.statline == null){
            System.out.println("statline null");
            this.statline = monster.statline;
        }
        if(this.showdown == null){
            System.out.println("showdown null");
            this.showdown = monster.showdown;
        }

    }
    */

    public void updateValues(Monster newMonsterStatus) {
        System.out.println("Updating values in monster, position: " +this.position +", facing: " +this.facing +", statline: " +this.statline);
        if(newMonsterStatus.position != null){
            System.out.println("new position: " +newMonsterStatus.position);
            this.position = newMonsterStatus.position;
        }
        if(newMonsterStatus.facing != null){
            System.out.println("new facing: " +newMonsterStatus.facing);
            this.facing = newMonsterStatus.facing;
        }
        if(newMonsterStatus.statline != null){
            System.out.println("new statline: " +newMonsterStatus.statline);
            this.statline = newMonsterStatus.statline;
        }
    }
}
