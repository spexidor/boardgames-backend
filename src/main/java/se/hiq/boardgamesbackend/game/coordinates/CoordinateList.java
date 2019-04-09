package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.Survivor;

import java.util.ArrayList;
import java.util.List;

/**
Helper class to handle a list of unique coordinates
 */
public class CoordinateList {
    private List<Coordinate> coordinateList;
    private List<Coordinate> tempCoordinateList;

    public CoordinateList(){
        this(new Coordinate(0,0));
    }

    public CoordinateList(Coordinate position) {
        this.coordinateList = new ArrayList<Coordinate>();
        this.tempCoordinateList = new ArrayList<Coordinate>();

        this.coordinateList.add(position);
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    /**
    Add all possible movement options for a given movement and board
     */
    public void addSteps(int movement, Board board){
        for(int i=0; i<movement;i++){
            for(int n=0; n<this.coordinateList.size(); n++){
                Coordinate c = this.coordinateList.get(n);
                addAllDirections(c, board);
            }
            mergeLists();
        }
    }

    private void addAllDirections(Coordinate origin, Board board) throws RuntimeException{
        if(origin != null) {
            if (origin.getX() > 0) {
                addLeft(origin);
            }
            if (origin.getX() < board.getWidth()) {
                addRight(origin);
            }
            if (origin.getY() > 0) {
                addUp(origin);
            }
            if (origin.getY() < board.getHeight()) {
                addDown(origin);
            }
        }
        else{
            throw new RuntimeException("Origin Coordinate missing");
        }
    }

    /**
    Adds a coordinate to coordinateList relative to origin coordinate
     */
    private void addLeft(Coordinate origin){
        Coordinate nc = new Coordinate(origin);
        nc.moveLeft();
        addIfUnique(nc);
    }
    private void addRight(Coordinate origin){
        Coordinate nc = new Coordinate(origin);
        nc.moveRight();
        addIfUnique(nc);
    }
    private void addUp(Coordinate origin){
        Coordinate nc = new Coordinate(origin);
        nc.moveUp();
        addIfUnique(nc);
    }
    private void addDown(Coordinate origin){
        Coordinate nc = new Coordinate(origin);
        nc.moveDown();
        addIfUnique(nc);
    }

    /**
    Adds newCoordinate to coordinateList if it isnt already there
     */
    private void addIfUnique(Coordinate newCoordinate){
        boolean uniqueAdd = true;
        for(Coordinate c:this.coordinateList){
            if(c.equals(newCoordinate)){
                uniqueAdd = false;
                break;
            }
        }
        if(uniqueAdd){ this.tempCoordinateList.add(newCoordinate); }
    }

    /**
    Merges all unique coordinates to coordinateList
     */
    private void mergeLists(){
        boolean uniqueAdd;
        for(Coordinate a:this.tempCoordinateList) {
            uniqueAdd = true;
            for (Coordinate c : this.coordinateList) {
                if (c.equals(a)) {
                    uniqueAdd = false;
                    break;
                }
            }
            if(uniqueAdd){ this.coordinateList.add(a); }
        }
        this.tempCoordinateList.clear();
    }

    public boolean hasCoordinate(Coordinate newPos) {
        boolean hasCoordinate = false;
        for(Coordinate n: coordinateList){
            if(n.equals(newPos))
            {
                hasCoordinate = true;
                break;
            }
        }
        return hasCoordinate;
    }

    public void removeInvalidMovements(Monster monster, List<Survivor> otherSurvivors) {
        //Survivors
        if(otherSurvivors!=null) {
            for (Survivor n : otherSurvivors) {
                if (n != null) {
                    removeCoordinate(n.getPosition());
                }
            }
        }

        //Monster
        if(monster!=null) {
            removeCoordinates(monster.calculateBaseCoordinates());
        }
    }

    private void removeCoordinate(Coordinate coordinateToRemove){
        for(Coordinate n: coordinateList){
            if(n.equals(coordinateToRemove))
            {
                coordinateList.remove(n);
                break;
            }
        }
    }

    private void removeCoordinates(List<Coordinate> coordinatesToRemove){
        for(Coordinate n: coordinatesToRemove){
            removeCoordinate(n);
        }
    }
}
