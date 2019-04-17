package se.hiq.boardgamesbackend.survivor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.showdown.ShowdownRepository;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
                    return optionalSurvivor.get().movementOptions();
                }
            }
        }
        //System.out.println("-----unable to get movement opts for survivor " +id);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @PutMapping("/survivor/{id}")
    public @ResponseBody Survivor updateSurvivor(@PathVariable Long id, @RequestBody Survivor newSurvivorState, HttpServletResponse response) {
        System.out.println("---PUT survivor request received, id=" +id +", body=" +newSurvivorState);
        Optional<Survivor> currentSurvivorState = survivorRepository.findById(id);
        System.out.println("---Existing survivor loaded, present=" +currentSurvivorState.isPresent());

        if(currentSurvivorState.isPresent() && currentSurvivorState.get().validUpdate(newSurvivorState)) {

            System.out.println("---New survivor state is valid");

            /**
             * Survivor in request (newSurvivorState) is not valid input in survivorRepository
             * because the link to parent showdown is missing in it.
             */
            newSurvivorState.setShowdown(currentSurvivorState.get().getShowdown());
            return survivorRepository.save(newSurvivorState);
        }
        else{
            System.out.println("---New survivor state is not valid");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
