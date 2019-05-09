package se.hiq.boardgamesbackend.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@Entity
public class Monster {

    @Id
    private Long id;

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
    //@JsonIgnore
    private AIDeck aiDeck;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //@JsonIgnore
    private HLDeck hlDeck;

    @Transient
    private MonsterStatline statline;

    public Monster(){

        int monsterLevel = 1;

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

    public List<Coordinate> movementOptions(int maxLength){
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

    public Showdown getShowdown() {
        return showdown;
    }

    public void setShowdown(Showdown showdown) {
        this.showdown = showdown;
    }

    public void setFacing(Facing facing) { this.facing = facing; }

    public MonsterStatline getStatline() { return statline; }

    public boolean isActivatedThisTurn() { return activatedThisTurn; }

    public int getLevel() { return level; }

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

        for(int i=0; i<targetRule.getTargetOrder().size(); i++){

            Target targetOption = targetRule.getTargetOrder().get(i);
            for(Survivor s: survivors){
                if(targetOption.validTarget(this, survivors, s)){
                    validTargets.add(s);
                }
            }

            if(validTargets.size() > 1 && targetOption.isClosest() ){
                List<Survivor> closestValidTargets = new ArrayList<>();
                for(Survivor s: validTargets){
                    if(MovementHelper.survivorClosestToMonster(this, validTargets, s)){
                        closestValidTargets.add(s);
                    }
                }
                return closestValidTargets;
            }
            else if(validTargets.size() > 0) { //target found
                break;
            }
        }
        return validTargets;
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

    public List<Coordinate> awayFromThreatsMovement(int length) {

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
            //TODO: implement
            return null;
        }
        else if(direction.equals(Direction.BACKWARDS)){
            //TODO: implement
            return null;
        }
        else{
            throw new RuntimeException("Unknown direction: " +direction);
        }
    }
}
