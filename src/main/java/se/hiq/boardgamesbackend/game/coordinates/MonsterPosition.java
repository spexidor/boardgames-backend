package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.persistence.*;

@Entity @IdClass(MonsterPositionId.class)
public class MonsterPosition {

    @Id
    @Enumerated(EnumType.STRING)
    private Facing facing;

    @Id
    private int x;

    @Id
    private int y;

    public MonsterPosition(){
        this(0, 0);
    }

    public MonsterPosition(int x, int y){
        this(x, y, Facing.UP);
    }

    public MonsterPosition(int x, int y, Facing facing){
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public MonsterPosition(MonsterPosition oldPosition){
        this(oldPosition.x, oldPosition.y, oldPosition.getFacing());
    }

    public Facing getFacing() {
        return facing;
    }

    @Override
    public boolean equals(Object a){
        if (a instanceof MonsterPosition){
            MonsterPosition ac = (MonsterPosition) a;
            return ac.getX() == this.getX() && ac.getY() == this.getY() ? true:false;
            //return ac.getX() == this.getX() && ac.getY() == this.getY() && ac.facing == this.facing? true:false;
        }
        else return false;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public void moveUp(){
        this.facing = Facing.UP;
        this.y--;
    }
    public void moveDown(){
        this.facing = Facing.DOWN;
        this.y++;
    }
    public void moveLeft(){
        this.facing = Facing.LEFT;
        this.x--;
    }
    public void moveRight(){
        this.facing = Facing.RIGHT;
        this.x++;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Coordinate getPosition(){
        return new Coordinate(x, y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
