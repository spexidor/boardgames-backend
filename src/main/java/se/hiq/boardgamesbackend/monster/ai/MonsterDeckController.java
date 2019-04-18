package se.hiq.boardgamesbackend.monster.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.monster.MonsterRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MonsterDeckController {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private MonsterRepository monsterRepository;

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
}
