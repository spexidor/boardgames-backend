package se.hiq.boardgamesbackend.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.ai.Direction;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

/**
 * Funny name of class. Haha.
 */

@RestController
@CrossOrigin
public class MonsterController {

    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/monster")
    public  @ResponseBody
    Iterable<Monster> getMonsterStatuses() {
        return monsterRepository.findAll();
    }

    @GetMapping("/monster/{id}")
    public  @ResponseBody
    Optional<Monster> getMonsterStatuses(@PathVariable long id) {
        return monsterRepository.findById(id);
    }

    @GetMapping("/monster/{id}/openMoves")
    public @ResponseBody
    ResponseEntity<List<Coordinate>> getMonsterOpenMoves(@PathVariable long id) {
        Optional<Monster> monster = monsterRepository.findById(id);
        if(monster.isPresent()) {
            return new ResponseEntity<>(monster.get().movementOptions(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/monster/{id}/specificMove")
    public @ResponseBody
    List<Coordinate> getMonsterMovesAwayFromThreat(@PathVariable long id, @RequestParam(value="direction", defaultValue="FORWARD") Direction direction, @RequestParam(value="length", defaultValue="0") int length, HttpServletResponse response) {
        Optional<Monster> monster = monsterRepository.findById(id);
        if(monster.isPresent()) {
            return monster.get().specificMove(direction, length);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PutMapping("/monster/{id}")
    public @ResponseBody
    Monster updateMonsterStatus(@PathVariable long id, @RequestBody Monster newMonsterStatus, HttpServletResponse response){
        System.out.println("---PUT monster request received, id=" +id +", body=" +newMonsterStatus);
        Optional<Monster> currentMonsterStatus = monsterRepository.findById(id);

        if(currentMonsterStatus.isPresent() && currentMonsterStatus.get().validUpdate(newMonsterStatus)) {
            Monster currentMonster = currentMonsterStatus.get();
            currentMonster.updateValues(newMonsterStatus);
            return monsterRepository.save(currentMonster);
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
