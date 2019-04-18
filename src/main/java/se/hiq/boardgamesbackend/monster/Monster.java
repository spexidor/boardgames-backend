package se.hiq.boardgamesbackend.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.hiq.boardgamesbackend.game.Facing;
import se.hiq.boardgamesbackend.monster.ai.AIDeck;
import se.hiq.boardgamesbackend.monster.ai.HLDeck;
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

    private boolean activatedThisTurn;

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
    public MonsterStatline statline;

    public Monster(){
        this.position = new Coordinate(2, 1);
        this.facing = Facing.UP;
        this.statline = new TestLion();
        this.activatedThisTurn = false;
        this.aiDeck = new AIDeck();
        this.aiDeck.setMonster(this);
        this.hlDeck = new HLDeck();
        this.hlDeck.setMonster(this);
    }

    public Long getId() {
        return id;
    }

    public List<Coordinate> movementOptions(){
        return MovementHelper.getMonsterMovement(this);
    }

    public void setMonsterPosition(Coordinate position) {
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

    public MonsterStatline getStatline() { return statline; }

    public boolean isActivatedThisTurn() { return activatedThisTurn; }

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

    public HLDeck getHlDeck() {
        return hlDeck;
    }

    public List<Coordinate> getBaseCoordinates() {
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

    public boolean validUpdate(Monster newState){

        Coordinate newPos = newState.getPosition();
        if(MovementHelper.coordinateInList(MovementHelper.getMonsterMovement(this), newPos)){
            return true;
        }
        else {
            return false;
        }
    }
}
