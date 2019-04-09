package se.hiq.boardgamesbackend.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class MonsterStatusController {

    @Autowired
    private MonsterStatusRepository monsterStatusRepository;

    @GetMapping("/monsterStatus")
    public  @ResponseBody
    Iterable<MonsterStatus> getMonsterStatuses() {
        return monsterStatusRepository.findAll();
    }

    @GetMapping("/monsterStatus/{id}")
    public  @ResponseBody
    Optional<MonsterStatus> getMonsterStatuses(@PathVariable long id) {
        return monsterStatusRepository.findById(id);
    }

    @GetMapping("/monsterStatus/{id}/openMoves")
    public @ResponseBody
    CoordinateList coordinateList(@PathVariable long id, HttpServletResponse response) {
        Optional<MonsterStatus> monsterStatus = monsterStatusRepository.findById(id);
        if(monsterStatus.isPresent()) {
            return monsterStatus.get().getMovementOptions(new Board());
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PutMapping("/monsterStatus/{id}")
    public @ResponseBody
    MonsterStatus updateMonsterStatus(@PathVariable long id, @RequestBody MonsterStatus newMonsterStatus, HttpServletResponse response){

        Optional<MonsterStatus> currentMonsterStatus = monsterStatusRepository.findById(id);
        if(currentMonsterStatus.isPresent() && currentMonsterStatus.get().validUpdate(newMonsterStatus)) {
            return monsterStatusRepository.save(newMonsterStatus);
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }


}
