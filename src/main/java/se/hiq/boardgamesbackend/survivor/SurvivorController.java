package se.hiq.boardgamesbackend.survivor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class SurvivorController {

    @Autowired
    SurvivorRepository survivorRepository;

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
    CoordinateList coordinateList(@PathVariable long id, HttpServletResponse response) {
        Optional<Survivor> optionalSurvivor = survivorRepository.findById(id);
        if(optionalSurvivor.isPresent()) {
            return optionalSurvivor.get().getMovementOptions(new Board());
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
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
