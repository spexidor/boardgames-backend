package se.hiq.boardgamesbackend.monster.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.monster.MonsterRepository;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.showdown.ShowdownRepository;
import se.hiq.boardgamesbackend.survivor.Survivor;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MonsterDeckController {

    @Autowired
    private ShowdownRepository showdownRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private AICardRepository cardRepository;

    @GetMapping("/monster/{id}/ai")
    public  @ResponseBody
    Deck getMonsterAiDeck(@PathVariable long id, HttpServletResponse response) {

        Optional<Monster> monster = monsterRepository.findById(id);
        if(monster.isPresent()) {
            return monster.get().getAiDeck();
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/monster/{id}/hl")
    public  @ResponseBody
    Deck getMonsterHlDeck(@PathVariable long id, HttpServletResponse response) {

        Optional<Monster> monster = monsterRepository.findById(id);
        if(monster.isPresent()) {
            return monster.get().getHlDeck();
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/monster/{monsterId}/ai/{cardId}/targets")
    public @ResponseBody
    List<Survivor> validTargets(@PathVariable long monsterId, @PathVariable long cardId, HttpServletResponse response) {

        Optional<Showdown> showdown = showdownRepository.findById(monsterId);

        if(showdown.isPresent()) {
            Optional<AICard> aiCard = cardRepository.findById(cardId);
            if(aiCard.isPresent()){
                TargetRule targetRule = aiCard.get().getTargetRule();
                System.out.println("*****   Finding open targets according to targetRule " +targetRule);

                List<Survivor> survivors = showdown.get().getSurvivors();
                return showdown.get().getMonster().targetOptions(survivors, targetRule);
            }
            else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }

        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
