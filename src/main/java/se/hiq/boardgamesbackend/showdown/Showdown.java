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

    private int turn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(mappedBy = "showdown", cascade=CascadeType.ALL)
    private Monster monster;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Survivor> survivors;

    //private Board board;

    public Showdown(){
        this("Default showdown constructor");
    }

    public Showdown(String description){
        this.description = description;
        this.gameStatus = GameStatus.RUNNING;
        this.monster = new Monster();
        this.monster.setShowdown(this);
        this.survivors = createSurvivors();
        this.turn = 0;
    }

    private List<Survivor> createSurvivors() {
        List<Survivor> survivors = new ArrayList<>();
        for(int n=0;n<2;n++) {
            Survivor survivor = new Survivor("Joe " +n, new Coordinate(n, 0));
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

    public int getTurn() { return turn; }
}
