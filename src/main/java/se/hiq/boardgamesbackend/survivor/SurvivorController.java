package se.hiq.boardgamesbackend.survivor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.ShowdownRepository;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class SurvivorController {

    @Autowired
    SurvivorRepository survivorRepository;

    @Autowired
    ShowdownRepository showdownRepository;

    @GetMapping("/survivor")
    public  @ResponseBody
    Iterable<Survivor> getSurvivor() {
        return survivorRepository.findAll();
    }

    @GetMapping("/survivor/{id}")
    public @ResponseBody Optional<Survivor> getSurvivorById(@PathVariable Long id){
        return survivorRepository.findById(id);
    }

    @GetMapping("/survivor/{id}/openMoves")
    public @ResponseBody
    List<Coordinate> coordinateList(@PathVariable long id, HttpServletResponse response) {
        Optional<Survivor> optionalSurvivor = survivorRepository.findById(id);

        if(optionalSurvivor.isPresent()) {
            if(optionalSurvivor.get().getShowdown()!=null) {
                Optional<Showdown> showdown = showdownRepository.findById(optionalSurvivor.get().getShowdown().getId());
                if(showdown.get().getId()!=null){
                    List<Survivor> survivors = showdown.get().getSurvivors();
                    //System.out.println("-----num survivors: " +survivors.size() +" in showdown " +showdown.get().getId());
                    Monster monster = showdown.get().getMonster();
                    return optionalSurvivor.get().getMovementOptions(new Board(), survivors, monster);
                }
            }
        }
        //System.out.println("-----unable to get movement opts for survivor " +id);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @PutMapping("/survivor/{id}")
    public @ResponseBody Survivor updateSurvivor(@PathVariable Long id, @RequestBody Survivor newSurvivorState, HttpServletResponse response) {
        Optional<Survivor> currentSurvivorState = survivorRepository.findById(id);
        if(currentSurvivorState.isPresent() && currentSurvivorState.get().validUpdate(newSurvivorState)) {
            return survivorRepository.save(newSurvivorState);
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
