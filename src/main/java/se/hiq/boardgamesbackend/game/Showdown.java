package se.hiq.boardgamesbackend.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.hiq.boardgamesbackend.monster.MonsterStatus;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.persistence.*;
import java.util.List;

@Entity
public class Showdown {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(cascade=CascadeType.ALL)
    private MonsterStatus monsterStatus;
    //private List<Survivor> survivors;
    //private Board board;

    public Showdown(){
        this("Default showdown constructor");
    }

    public Showdown(String description){
        this.id = 0L;
        this.description = description;
        this.monsterStatus = new MonsterStatus();
        this.gameStatus = GameStatus.RUNNING;
    }

    public void validate(){
        if(this.monsterStatus == null){
            this.monsterStatus = new MonsterStatus();
        }
    }

    public MonsterStatus getMonsterStatus() {
        return monsterStatus;
    }

    public Long getId() {
        if (id == null)
            return 0L;
        else {
            return id;
        }
    }

    public void cleanId(){
        this.id = 0L;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(GameStatus gameStatus){
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
