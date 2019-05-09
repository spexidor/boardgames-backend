package se.hiq.boardgamesbackend.board.coordinates;

import javax.persistence.*;

@Entity @IdClass(CoordinateId.class)
public class Coordinate {

    @Id
    private int x;
    @Id
    private int y;

    public Coordinate(){
        this(0, 0);
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate anotherCoordinate){
        this.x = anotherCoordinate.getX();
        this.y = anotherCoordinate.getY();
    }

    @Override
    public boolean equals(Object a){
        if (a instanceof Coordinate){
            Coordinate ac = (Coordinate) a;
            return ac.x == this.x && ac.y == this.y;
        }
            else return false;
    }

    @Override
    public String toString(){
        return "(" +x +", " +y +")";
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
