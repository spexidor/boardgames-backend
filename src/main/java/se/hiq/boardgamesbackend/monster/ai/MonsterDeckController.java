package se.hiq.boardgamesbackend.monster.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private AIDeckRepository deckRepository;

    @PutMapping("/monster/{id}/ai")
    public  @ResponseBody
    AIDeck updateMonsterAiDeck(@PathVariable long id, @RequestBody AIDeck updatedDeck, HttpServletResponse response) {
        System.out.println("---PUT deck request received, id=" +id +", body=" +updatedDeck);
        Optional<Monster> monster = monsterRepository.findById(id);
        System.out.println("---Existing monster loaded, present=" +monster.isPresent());

        if(monster.isPresent()) {
            System.out.println("---Deck id=" +monster.get().getAiDeck().getId());
            monster.get().setAiDeck(updatedDeck);
            System.out.println("---New deck set");

            //monster.get().getAiDeck().updateState(updatedDeck);
            return monsterRepository.save(monster.get()).getAiDeck();
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/monster/{id}/ai")
    public AIDeck getMonsterAiDeck(@PathVariable long id, HttpServletResponse response) {

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

    @PutMapping("/monster/{id}/hl")
    public  @ResponseBody
    HLDeck updateMonsterHLDeck(@PathVariable long id, @RequestBody HLDeck updatedDeck, HttpServletResponse response) {
        System.out.println("---PUT hl deck request received, id=" +id +", body=" +updatedDeck);
        Optional<Monster> monster = monsterRepository.findById(id);
        System.out.println("---Existing monster loaded, present=" +monster.isPresent());

        if(monster.isPresent()) {
            System.out.println("---Deck id=" +monster.get().getHlDeck().getId());
            monster.get().setHlDeck(updatedDeck);
            System.out.println("---New HL deck set");

            //monster.get().getAiDeck().updateState(updatedDeck);
            return monsterRepository.save(monster.get()).getHlDeck();
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
