package se.hiq.boardgamesbackend.game.coordinates;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity @IdClass(CoordinateId.class)
public class Coordinate {

    @Id
    private int x;
    @Id
    private int y;

    public Coordinate(){
        this.x = 0;
        this.y = 0;
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate anotherCoordinate){
        this.x = anotherCoordinate.getX();
        this.y = anotherCoordinate.getY();
    }

    public boolean equals(Coordinate a){
        return (this.x == a.x && this.y == a.y) ? true:false;
    }

    public void moveUp(){
        this.y--;
    }
    public void moveDown(){
        this.y++;
    }
    public void moveLeft(){
        this.x--;
    }
    public void moveRight(){
        this.x++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
