package se.hiq.boardgamesbackend.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
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
    List<Coordinate> getMonsterOpenMoves(@PathVariable long id, HttpServletResponse response) {
        Optional<Monster> monsterStatus = monsterRepository.findById(id);
        if(monsterStatus.isPresent()) {
            return monsterStatus.get().getMovementOptions(new Board()).getCoordinateList();
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PutMapping("/monster/{id}")
    public @ResponseBody
    Monster updateMonsterStatus(@PathVariable long id, @RequestBody Monster newMonsterStatus, HttpServletResponse response){

        Optional<Monster> currentMonsterStatus = monsterRepository.findById(id);
        if(currentMonsterStatus.isPresent() && currentMonsterStatus.get().validUpdate(newMonsterStatus)) {
            return monsterRepository.save(newMonsterStatus);
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }


}