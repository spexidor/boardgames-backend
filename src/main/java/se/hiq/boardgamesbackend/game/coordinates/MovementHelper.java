package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.Survivor;

import java.util.ArrayList;
import java.util.List;

public class MovementHelper {
    public static List<Coordinate> getSurvivorMovement(Survivor survivor, List<Survivor> survivors, Monster monster) {

        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(new Coordinate(survivor.getPosition())); //current position
        if(survivor.getMovesLeft() > 0) {
            addSteps(coordinateList, survivor.getMovement(), monster, survivors);
            removeInvalidMovements(coordinateList, monster, survivors); //remove monster and all survivor coordinates
            coordinateList.add(new Coordinate(survivor.getPosition())); //... then add back own position
        }
        return coordinateList;
    }

    public static List<Coordinate> getMonsterMovement(Monster monster){

        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(new Coordinate(monster.getPosition()));
        //System.out.println(("Calculating moves for monster with move " +monster.getMonsterStatline().getMovement()));

        addSteps(coordinateList, monster.getStatline().getMovement());
        //removeCoordinate(coordinateList, monster.getPosition());

        return coordinateList;
    }

    public static void addSteps(List<Coordinate> coordinateList, int movement, Monster monster, List<Survivor> survivors){
        List<Coordinate> newCoordinates = new ArrayList<>();
        List<Coordinate> blockedCoordinates = monster.calculateBaseCoordinates();
        List<Coordinate> blockedCoordinatesSurvivors = new ArrayList<>();

        for(Survivor s: survivors){
            blockedCoordinatesSurvivors.add(s.getPosition());
        }
        mergeLists(blockedCoordinatesSurvivors, blockedCoordinates);

        for(int i=0; i<movement;i++){
            for(int n=0; n<coordinateList.size(); n++){
                Coordinate c = coordinateList.get(n);
                addAllDirections(newCoordinates, c);
                removeCoordinates(newCoordinates, blockedCoordinates);
            }
            mergeLists(newCoordinates, coordinateList);
        }
    }

    public static void addSteps(List<Coordinate> coordinateList, int movement){
        List<Coordinate> newCoordinates = new ArrayList<>();
        for(int i=0; i<movement;i++){
            for(int n=0; n<coordinateList.size(); n++){
                Coordinate c = coordinateList.get(n);
                addAllDirections(newCoordinates, c);
            }
            mergeLists(newCoordinates, coordinateList);
        }
    }

    /**
     * add all unique coordinates from newCoordinates to coordinateList, then clears it
     *
     * @param newCoordinates
     * @param coordinateList
     */
    private static void mergeLists(List<Coordinate> newCoordinates, List<Coordinate> coordinateList) {
        boolean uniqueAdd;
        for(Coordinate a:newCoordinates) {
            uniqueAdd = true;
            for (Coordinate c : coordinateList) {
                if (c.equals(a)) {
                    uniqueAdd = false;
                    break;
                }
            }
            if(uniqueAdd){
                coordinateList.add(a);
            }
        }
        newCoordinates.clear();
    }

    public static boolean coordinateInList(List<Coordinate> coordinateList, Coordinate newPos) {

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

    /*
    public static void addIfUnique(List<Coordinate> coordinateList, Coordinate newCoordinate){
        boolean uniqueAdd = true;
        for(Coordinate c:coordinateList){
            if(c.equals(newCoordinate)){
                uniqueAdd = false;
                break;
            }
        }
        if(uniqueAdd){
            coordinateList.add(newCoordinate);
        }
    }
    */

    private static void addAllDirections(List<Coordinate> coordinateList, Coordinate origin) throws RuntimeException{

        Board board = new Board();
        if(origin != null) {
            if (origin.getX() > 0) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveLeft();
                coordinateList.add(coordinate);
            }
            if (origin.getX() < board.getWidth()) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveRight();
                coordinateList.add(coordinate);
            }
            if (origin.getY() > 0) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveUp();
                coordinateList.add(coordinate);
            }
            if (origin.getY() < board.getHeight()) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveDown();
                coordinateList.add(coordinate);
            }
        }
        else{
            throw new RuntimeException("Origin Coordinate missing");
        }
    }

    public static void removeInvalidMovements(List<Coordinate> coordinateList, Monster monster, List<Survivor> otherSurvivors) {
        //Survivors
        if(otherSurvivors!=null) {
            for (Survivor n : otherSurvivors) {
                if (n != null) {
                    removeCoordinate(coordinateList, n.getPosition());
                }
            }
        }

        //Monster
        if(monster!=null) {
            removeCoordinates(coordinateList, monster.calculateBaseCoordinates());
        }
    }

    private static void removeCoordinate(List<Coordinate> coordinateList, Coordinate coordinateToRemove){
        for(Coordinate n: coordinateList){
            if(n.equals(coordinateToRemove))
            {
                coordinateList.remove(n);
                break;
            }
        }
    }

    private static void removeCoordinates(List<Coordinate> coordinateList, List<Coordinate> coordinatesToRemove){
        for(Coordinate n: coordinatesToRemove){
            removeCoordinate(coordinateList, n);
        }
    }

}
