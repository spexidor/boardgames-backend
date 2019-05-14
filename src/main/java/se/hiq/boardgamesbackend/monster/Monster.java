package se.hiq.boardgamesbackend.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.hiq.boardgamesbackend.board.Board;
import se.hiq.boardgamesbackend.monster.ai.*;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.board.MovementHelper;
import se.hiq.boardgamesbackend.monster.types.TestLion;
import se.hiq.boardgamesbackend.survivor.Survivor;
import se.hiq.boardgamesbackend.survivor.SurvivorStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Monster {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    private Coordinate position;

    @Enumerated(EnumType.STRING)
    private Facing facing;

    private boolean activatedThisTurn;
    private int level;
    private Long lastWoundedBy;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Showdown showdown;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AIDeck aiDeck;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private HLDeck hlDeck;

    @Transient
    private MonsterStatline statline;

    public Monster(){

        int monsterLevel = 1;

        this.name = "White Lion";
        this.position = new Coordinate(6, 3);
        this.facing = Facing.UP;
        this.statline = new TestLion();
        this.activatedThisTurn = false;
        this.aiDeck = new AIDeck();
        this.aiDeck.setMonster(this);
        this.hlDeck = new HLDeck(monsterLevel);
        this.hlDeck.setMonster(this);
        this.level = monsterLevel;
        this.lastWoundedBy = -1L;
    }

    public Long getId() {
        return id;
    }

    private List<Coordinate> movementOptions(int maxLength){
        return MovementHelper.getMonsterMovement(this, maxLength);
    }

    public List<Coordinate> movementOptions(){
        return MovementHelper.getMonsterMovement(this);
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Facing getFacing() {
        return facing;
    }

    private Showdown getShowdown() {
        return showdown;
    }

    public void setShowdown(Showdown showdown) {
        this.showdown = showdown;
    }

    public void setFacing(Facing facing) { this.facing = facing; }

    public MonsterStatline getStatline() { return statline; }

    public boolean isActivatedThisTurn() { return activatedThisTurn; }

    public int getLevel() { return level; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Coordinate> getBlindspot() {
        List <Coordinate> blindspot= new ArrayList<>();
        for(int i =0; i<this.statline.width; i++){
            Coordinate blindSpotCoord;

            if(this.facing.equals(Facing.UP)) {
                blindSpotCoord =new Coordinate(this.position.getX()+i, this.position.getY()+this.statline.getHeight());
            }
            else if(this.facing.equals(Facing.DOWN)){
                blindSpotCoord =new Coordinate(this.position.getX()+i, this.position.getY()-1);
            }
            else if(this.facing.equals(Facing.LEFT)){
                blindSpotCoord =new Coordinate(this.position.getX()+this.statline.getHeight(), this.position.getY()+i);
            }
            else if(this.facing.equals(Facing.RIGHT)){
                blindSpotCoord =new Coordinate(this.position.getX()-1, this.position.getY()+i);
            }
            else{
                throw new RuntimeException("No matching Facing: " +this.facing);
            }
            blindspot.add(blindSpotCoord);
        }
        return blindspot;
    }

    public AIDeck getAiDeck() {
        return aiDeck;
    }

    public void setAiDeck(AIDeck aiDeck) {
        this.aiDeck = aiDeck;
    }

    public HLDeck getHlDeck() {
        return hlDeck;
    }

    public List<Coordinate> getBaseCoordinates() {
        int x = this.position.getX();
        int y = this.position.getY();

        return getBaseCoordinates(new Coordinate(x, y));
    }

    /*
     Base coordinates if monster is in c
     */
    private List<Coordinate> getBaseCoordinates(Coordinate c){
        List<Coordinate> baseCoordinates = new ArrayList<>();
        int x = c.getX();
        int y = c.getY();

        for(int i=0;i<this.statline.width; i++){
            for(int j=0;j<this.statline.height; j++){
                baseCoordinates.add(new Coordinate(x+i, y+j));
            }
        }
        return  baseCoordinates;
    }

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
        if(newMonsterStatus.lastWoundedBy != -1){
            System.out.println("new lastWoundedBy: " +newMonsterStatus.lastWoundedBy);
            this.lastWoundedBy = newMonsterStatus.lastWoundedBy;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActivatedThisTurn(boolean activatedThisTurn) {
        this.activatedThisTurn = activatedThisTurn;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean validUpdate(Monster newState){

        /*
        Coordinate newPos = newState.getPosition();
        if(MovementHelper.coordinateInList(MovementHelper.getMonsterMovement(this), newPos)){
            return true;
        }
        else {
            return false;
        }
        */
        return true;
    }

    public List<Survivor> targetOptions(List<Survivor> survivors, TargetRule targetRule){
        List<Survivor> validTargets = new ArrayList<>();

        Survivor priorityTarget = survivorWithPriorityTarget(survivors);
        if(priorityTarget != null){
            validTargets.add(priorityTarget);
        }
        else {
            for (int i = 0; i < targetRule.getTargetOrder().size(); i++) {

                Target targetOption = targetRule.getTargetOrder().get(i);
                for (Survivor s : survivors) {
                    if (targetOption.validTarget(this, survivors, s)) {
                        validTargets.add(s);
                    }
                }

                if (validTargets.size() > 1 && targetOption.isClosest()) {
                    List<Survivor> closestValidTargets = new ArrayList<>();
                    for (Survivor s : validTargets) {
                        if (MovementHelper.survivorClosestToMonster(this, validTargets, s)) {
                            closestValidTargets.add(s);
                        }
                    }
                    return closestValidTargets;
                } else if (validTargets.size() > 1 && targetOption.isRandom()) {
                    Random r = new Random();
                    int randomIndex = r.nextInt(validTargets.size());
                    System.out.println("Choosing random survivor, index= " + randomIndex);
                    Survivor randomSurvivor = validTargets.get(randomIndex);
                    validTargets.clear();
                    validTargets.add(randomSurvivor);
                    return validTargets;
                } else if (validTargets.size() > 0) { //target found
                    break;
                }
            }
        }
        return validTargets;
    }

    private Survivor survivorWithPriorityTarget(List<Survivor> survivors) {
        for (Survivor s: survivors){
            if(s.isPriorityTarget()){
                return s;
            }
        }
        return null;
    }

    public void setHlDeck(HLDeck hlDeck) {
        this.hlDeck = hlDeck;
    }

    public void setStatline(MonsterStatline statline) {
        this.statline = statline;
    }

    public boolean facingSurvivor(Survivor survivor) {
        if(facing.equals(Facing.UP)){
            System.out.println("Facing up. Survivor y pos: " +survivor.getPosition().getY() +", monster y pos: " +this.position.getY());
            return survivor.getPosition().getY() < this.position.getY();
        }
        else if(facing.equals(Facing.DOWN)){
            return survivor.getPosition().getY() > (this.position.getY()+this.statline.getHeight()-1);
        }
        else if(facing.equals(Facing.LEFT)){
            return survivor.getPosition().getX() < this.position.getX();
        }
        else if(facing.equals(Facing.RIGHT)){
            return survivor.getPosition().getX() > (this.position.getX()+this.statline.getHeight()-1);
        }
        else {
            throw new RuntimeException("Facing not set in monster " +this.id);
        }
    }

    public Long getLastWoundedBy() {
        return lastWoundedBy;
    }

    public void setLastWoundedBy(Long lastWoundedBy) {
        this.lastWoundedBy = lastWoundedBy;
    }

    private List<Coordinate> awayFromThreatsMovement(int length) {

        List<Coordinate> openMoves = this.movementOptions(length);
        List<Coordinate> furthestCoordinates = new ArrayList<>();

        int maxDist = 0;
        int tempDist = 0;

        for(Coordinate c: openMoves){
            //System.out.println("Checking distance between " +c +" and threats");
            for(Survivor s: this.getShowdown().getSurvivors()){
                if(s.getStatus().equals(SurvivorStatus.STANDING)){
                    //System.out.println(s +" is a threat, distance to monster in C is " +MovementHelper.distance(s.getPosition(), this.getBaseCoordinates(c)));
                    tempDist = tempDist + MovementHelper.distance(s.getPosition(), this.getBaseCoordinates(c));
                }
                //System.out.println("Total distance in " +c +" to threats is " +tempDist);
            }
            if(tempDist > maxDist){
                //System.out.println(tempDist +" is furthest, clearing array");
                maxDist = tempDist;

                furthestCoordinates = new ArrayList<>();
                furthestCoordinates.add(c);
            }
            else if(tempDist == maxDist){
                //System.out.println(tempDist +" is equally good, adding to array");
                furthestCoordinates.add(c);
            }
            tempDist = 0;
        }

        return furthestCoordinates;
    }

    public List<Coordinate> specificMove(Direction direction, int length) {
        if(direction.equals(Direction.AWAY_FROM_THREATS)){
            return awayFromThreatsMovement(length);
        }
        else if(direction.equals(Direction.FORWARD)){
            Coordinate coordinate = forwardMovement(length);
            List<Coordinate> coordinates = new ArrayList<>();
            coordinates.add(coordinate);
            return coordinates;
        }
        else if(direction.equals(Direction.BACKWARDS)){
            Coordinate coordinate = backwardMovement(length);
            List<Coordinate> coordinates = new ArrayList<>();
            coordinates.add(coordinate);
            return coordinates;
        }
        else if(direction.equals(Direction.CLOSEST_BOARD_EDGE)){

            int distanceToUpper = MovementHelper.distance(this.position, new Coordinate(this.position.getX(), 0));
            int distanceToLower = MovementHelper.distance(this.position, new Coordinate(this.position.getX(), Board.HEIGHT)) - this.statline.width;
            int distanceToLeft  = MovementHelper.distance(this.position, new Coordinate(0, this.position.getY()));
            int distanceToRight = MovementHelper.distance(this.position, new Coordinate(Board.WIDTH, this.position.getY())) - this.statline.width;

            int closestDistance = Math.min(Math.min(distanceToUpper, distanceToLower), Math.min(distanceToLeft, distanceToRight));

            List<Coordinate> coordinates = new ArrayList<>();
            if(distanceToUpper == closestDistance){
                Facing closestEdge = Facing.UP;
                Coordinate coordinate =  MovementHelper.coordinateInFacing(this.position, closestEdge, length);
                coordinates.add(coordinate);
            }
            if(distanceToLower == closestDistance){
                Facing closestEdge = Facing.DOWN;
                Coordinate coordinate =  MovementHelper.coordinateInFacing(this.position, closestEdge, length);
                coordinates.add(coordinate);
            }
            if(distanceToLeft == closestDistance){
                Facing closestEdge = Facing.LEFT;
                Coordinate coordinate =  MovementHelper.coordinateInFacing(this.position, closestEdge, length);
                coordinates.add(coordinate);
            }
            if(distanceToRight == closestDistance){
                Facing closestEdge = Facing.RIGHT;
                Coordinate coordinate =  MovementHelper.coordinateInFacing(this.position, closestEdge, length);
                coordinates.add(coordinate);
            }

            return coordinates;
        }
        else{
            throw new RuntimeException("Unknown direction: " +direction);
        }
    }

    private boolean closestDistance(int isThisCloest, int c1, int c2, int c3){
        return isThisCloest < c1 && isThisCloest < c2 && isThisCloest < c3;
    }

    private Coordinate backwardMovement(int length) {
        return MovementHelper.coordinateInFacing(this.position, MovementHelper.flipFacing(this.facing), length);
    }

    private Coordinate forwardMovement(int length) {
        return MovementHelper.coordinateInFacing(this.position, this.facing, length);
    }
}
