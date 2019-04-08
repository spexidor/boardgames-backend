package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.game.Board;

import java.util.ArrayList;
import java.util.List;

/**
 Helper class to handle a list of unique coordinates
 */
public class MonsterPositionList {
    private List<MonsterPosition> coordinateList;
    private List<MonsterPosition> tempMonsterPositionList;

    public MonsterPositionList(MonsterPosition position) {
        this.coordinateList = new ArrayList<MonsterPosition>();
        this.tempMonsterPositionList = new ArrayList<MonsterPosition>();

        this.coordinateList.add(position);
    }

    public List<MonsterPosition> getMonsterPositionList() {
        return coordinateList;
    }

    /**
     Add all possible movement options for a given movement and board
     */
    public void addMultipleSteps(int movement, Board board){
        for(int i=0; i<movement;i++){
            for(int n=0; n<this.coordinateList.size(); n++){
                MonsterPosition c = this.coordinateList.get(n);
                addAllDirections(c, board);
            }
            mergeLists();
        }
    }

    private void addAllDirections(MonsterPosition origin, Board board) throws RuntimeException{
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
    private void addLeft(MonsterPosition origin){
        MonsterPosition nc = new MonsterPosition(origin);
        nc.moveLeft();
        addIfUnique(nc);
    }
    private void addRight(MonsterPosition origin){
        MonsterPosition nc = new MonsterPosition(origin);
        nc.moveRight();
        addIfUnique(nc);
    }
    private void addUp(MonsterPosition origin){
        MonsterPosition nc = new MonsterPosition(origin);
        nc.moveUp();
        addIfUnique(nc);
    }
    private void addDown(MonsterPosition origin){
        MonsterPosition nc = new MonsterPosition(origin);
        nc.moveDown();
        addIfUnique(nc);
    }

    /**
     Adds newMonsterPosition to coordinateList if it isnt already there
     */
    private void addIfUnique(MonsterPosition newMonsterPosition){
        boolean uniqueAdd = true;
        for(MonsterPosition c:this.coordinateList){
            if(c.equals(newMonsterPosition)){
                uniqueAdd = false;
                break;
            }
        }
        if(uniqueAdd){ this.tempMonsterPositionList.add(newMonsterPosition); }
    }

    /**
     Merges all unique coordinates to coordinateList
     */
    private void mergeLists(){
        boolean uniqueAdd;
        for(MonsterPosition a:this.tempMonsterPositionList) {
            uniqueAdd = true;
            for (MonsterPosition c : this.coordinateList) {
                if (c.equals(a)) {
                    uniqueAdd = false;
                    break;
                }
            }
            if(uniqueAdd){ this.coordinateList.add(a); }
        }
        this.tempMonsterPositionList.clear();
    }

    public boolean hasMonsterPosition(MonsterPosition newPos) {
        boolean hasMonsterPosition = false;
        for(MonsterPosition n: coordinateList){
            if(n.equals(newPos))
            {
                hasMonsterPosition = true;
                break;
            }
        }
        return hasMonsterPosition;
    }
}
