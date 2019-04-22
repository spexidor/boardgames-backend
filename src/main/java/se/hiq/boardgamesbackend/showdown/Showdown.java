package se.hiq.boardgamesbackend.showdown;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.hiq.boardgamesbackend.game.GameStatus;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Showdown {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    private Integer turn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(mappedBy = "showdown", cascade=CascadeType.ALL)
    private Monster monster;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Survivor> survivors;

    //private Board board;

    public Showdown(){
        this("AUTO-GEN");
    }

    public Showdown(String description){
        this.description = description;
        this.gameStatus = GameStatus.RUNNING;
        this.monster = new Monster();
        this.monster.setShowdown(this);
        this.survivors = createSurvivors();
        this.turn = 1;
    }

    private List<Survivor> createSurvivors() {
        List<Survivor> survivors = new ArrayList<>();
        for(int n=0;n<4;n++) {
            String name = "";
            if(n==0){
                name = "Allister";
            }
            if(n==1){
                name = "Lucy";
            }
            if(n==2){
                name = "Erza";
            }
            if(n==3){
                name = "Zachary";
            }
            Survivor survivor = new Survivor(name, new Coordinate(n+5, 0));
            survivor.setShowdown(this);
            survivors.add(survivor);

        }
        return survivors;
    }

    /*
    public void validate(){
        if(this.monster == null){
            this.monster = new Monster();
        }
    }
    */

    public Monster getMonster() { return monster; }

    public Long getId() { return id; }

    public String getDescription() {
        return description;
    }

    public void setStatus(GameStatus gameStatus){
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Survivor> getSurvivors() {
        return survivors;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setSurvivors(List<Survivor> survivors) {
        this.survivors = survivors;
    }

    public Integer getTurn() { return turn; }

    public void updateState(Showdown newShowdownState) {
        if(newShowdownState.turn != null){
            System.out.println("new turn: " +newShowdownState.turn);
            this.turn = newShowdownState.turn;
        }
        if(newShowdownState.description != null && !newShowdownState.description.equals("") && !newShowdownState.description.equals("AUTO-GEN")){
            System.out.println("new desc: " +newShowdownState.description);
            this.description = newShowdownState.description;
        }
        if(newShowdownState.gameStatus != null){
            System.out.println("new status: " +newShowdownState.gameStatus);
            this.gameStatus = newShowdownState.gameStatus;
        }
    }
}
