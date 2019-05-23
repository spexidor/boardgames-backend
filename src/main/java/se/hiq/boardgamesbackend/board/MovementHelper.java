package se.hiq.boardgamesbackend.board;

import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.Facing;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.monster.ai.Token;
import se.hiq.boardgamesbackend.survivor.Survivor;
import se.hiq.boardgamesbackend.survivor.SurvivorStatus;

import java.util.ArrayList;
import java.util.List;

public class MovementHelper {
    public static List<Coordinate> getSurvivorMovement(Survivor survivor, List<Survivor> survivors, Monster monster) {

        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(new Coordinate(survivor.getPosition())); //current position
        if(survivor.getMovesLeft() > 0 && !deadOrKnockedDown(survivor)) {
            addSteps(coordinateList, survivor.getMovement(), monster, survivors);
            removeInvalidMovements(coordinateList, monster, survivors); //remove monster and all survivor coordinates
            coordinateList.add(new Coordinate(survivor.getPosition())); //... then add back own position
        }
        return coordinateList;
    }

    private static boolean deadOrKnockedDown(Survivor survivor){
        return (survivor.getStatus() == SurvivorStatus.DEAD || survivor.getStatus() == SurvivorStatus.KNOCKED_DOWN);
    }

    public static List<Coordinate> getMonsterMovement(Monster monster){

        int negativeMovementTokens = 0;
        if(monster.getNegativeTokens() != null){
            for(Token n: monster.getNegativeTokens()){
                if(n.equals(Token.MOVEMENT)){
                    negativeMovementTokens++;
                    System.out.println("Monster movement reduced due to negative token");
                }
            }
        }
        return getMonsterMovement(monster, monster.getStatline().getMovement()-negativeMovementTokens);
    }

    public static List<Coordinate> getMonsterMovement(Monster monster, int maxLength) {

        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(new Coordinate(monster.getPosition()));

        addSteps(coordinateList, maxLength);
        return coordinateList;
    }


    private static void addSteps(List<Coordinate> coordinateList, int movement, Monster monster, List<Survivor> survivors){
        List<Coordinate> newCoordinates = new ArrayList<>();
        List<Coordinate> blockedCoordinates = monster.getBaseCoordinates();
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

    private static void addSteps(List<Coordinate> coordinateList, int movement){
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

        if(origin != null) {
            if (origin.getX() > 0) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveLeft();
                coordinateList.add(coordinate);
            }
            if (origin.getX() < Board.WIDTH) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveRight();
                coordinateList.add(coordinate);
            }
            if (origin.getY() > 0) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveUp();
                coordinateList.add(coordinate);
            }
            if (origin.getY() < Board.HEIGHT) {
                Coordinate coordinate = new Coordinate(origin.getX(), origin.getY());
                coordinate.moveDown();
                coordinateList.add(coordinate);
            }
        }
        else{
            throw new RuntimeException("Origin Coordinate missing");
        }
    }

    private static void removeInvalidMovements(List<Coordinate> coordinateList, Monster monster, List<Survivor> otherSurvivors) {
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
            removeCoordinates(coordinateList, monster.getBaseCoordinates());
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

    /**
     **   Returns closest distance between one and several coordinates
     **/
    public static int distance(Coordinate coordinate, List<Coordinate> coordinates){
        int min = Integer.MAX_VALUE;
        for (Coordinate c: coordinates){
            int tempDist = distance(c, coordinate);
            if(tempDist < min){
                min = tempDist;
            }
        }
        return min;
    }

    public static int distance(Coordinate c1, Coordinate c2){
        return Math.abs(c1.getX()-c2.getX())+Math.abs(c1.getY()-c2.getY());
    }

    public static boolean survivorClosestToMonster(Monster monster, List<Survivor> survivors, Survivor chosenSurvivor) {

        //Get distance between choseSurvivor and Monster
        int distance = distance(chosenSurvivor.getPosition(), monster.getBaseCoordinates());

        //Check all survivors. If any distance is shorter, chosen survivor is not closest
        for(Survivor s: survivors){
            if(MovementHelper.distance(s.getPosition(), monster.getBaseCoordinates()) < distance){
                return false;
            }
        }
        return true;
    }

    public static Coordinate coordinateInFacing(Coordinate coordinate, Facing facing, int length) throws  RuntimeException{
        if(facing.equals(Facing.UP)){
            return new Coordinate(coordinate.getX(), Math.max(coordinate.getY()-length, 0));
        }
        else if(facing.equals(Facing.DOWN)){
            return new Coordinate(coordinate.getX(), Math.min(coordinate.getY()+length, Board.HEIGHT));
        }
        else if(facing.equals(Facing.RIGHT)){
            return new Coordinate(Math.min(coordinate.getX()+length, Board.WIDTH), coordinate.getY());
        }
        else if(facing.equals(Facing.LEFT)){
            return new Coordinate(Math.max(coordinate.getX()-length, 0), coordinate.getY());
        }
        throw new RuntimeException("coordinateInFacing: Unexpected facing " +facing);
    }

    public static Facing flipFacing(Facing facing){
        if(facing.equals(Facing.UP)){
            return Facing.DOWN;
        }
        else if(facing.equals(Facing.DOWN)){
            return Facing.UP;
        }
        else if(facing.equals(Facing.RIGHT)){
            return Facing.LEFT;
        }
        else if(facing.equals(Facing.LEFT)){
            return Facing.RIGHT;
        }
        throw new RuntimeException("invertedFacing: Unexpected facing " +facing);
    }
}
