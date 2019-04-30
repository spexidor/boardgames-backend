package se.hiq.boardgamesbackend.survivor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.dice.DiceResult;
import se.hiq.boardgamesbackend.showdown.ShowdownService;
import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.showdown.ShowdownRepository;
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.injury.Injury;
import se.hiq.boardgamesbackend.survivor.injury.InjuryTable;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SurvivorController {

    @Autowired
    SurvivorRepository survivorRepository;

    @Autowired
    private ShowdownService showdownService;

    @GetMapping("/survivor")
    public  @ResponseBody
    Iterable<Survivor> getSurvivor() {
        return survivorRepository.findAll();
    }

    @GetMapping("/survivor/{id}")
    public @ResponseBody Optional<Survivor> getSurvivorById(@PathVariable Long id){
        return survivorRepository.findById(id);
    }

    @DeleteMapping("/survivor/{id}")
    public void deleteSurvivorById(@PathVariable Long id, HttpServletResponse response){
        Optional<Survivor> survivor = survivorRepository.findById(id);
        if(survivor.isPresent()){

            Optional<Showdown> showdown = showdownService.findById(survivor.get().getShowdown().getId());
            showdown.get().removeSurvivorById(id);

            showdownService.save(showdown.get());
            survivorRepository.deleteById(id);
        }
        else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/survivor/{id}/openMoves")
    public @ResponseBody
    List<Coordinate> coordinateList(@PathVariable long id, HttpServletResponse response) {
        Optional<Survivor> optionalSurvivor = survivorRepository.findById(id);

        if(optionalSurvivor.isPresent()) {
            if(optionalSurvivor.get().getShowdown()!=null) {
                Optional<Showdown> showdown = showdownService.findById(optionalSurvivor.get().getShowdown().getId());
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

            currentSurvivorState.get().updateState(newSurvivorState);

            return survivorRepository.save(currentSurvivorState.get());
        }
        else{
            System.out.println("---New survivor state is not valid");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @GetMapping("/survivor/injury")
    public  @ResponseBody
    Injury getSevereInjury(@RequestParam(value="table", defaultValue="head") String table) {

        assert( table.toUpperCase().equals("HEAD") || table.toUpperCase().equals("BODY")  ||
                table.toUpperCase().equals("ARMS")  || table.toUpperCase().equals("LEGS")  ||
                table.toUpperCase().equals("WAIST")  || table.toUpperCase().equals("BRAIN"));

        if(table.equals("head")) {
            return InjuryTable.randomHeadResult();
        }
        else if(table.equals("body")){
            return InjuryTable.randomBodyResult();
        }
        else if(table.equals("legs")){
            return InjuryTable.randomLegsResult();
        }
        else if(table.equals("arms")){
            return InjuryTable.randomArmsResult();
        }
        else if(table.equals("waist")){
            return InjuryTable.randomWaistResult();
        }
        else if(table.equals("brain")){
            return InjuryTable.randomBrainResult();
        }
        else{
            return null;
        }
    }

    @GetMapping("/survivor/hitlocation")
    public  @ResponseBody
    List<HitlocationType> getHitlocation(@RequestParam(value="numDice", defaultValue="1") int numDice) {

        List<HitlocationType> hits = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            int roll = DiceResult.roll(6);
            switch(roll){
                case 1: hits.add(HitlocationType.HEAD);
                    break;
                case 2: hits.add(HitlocationType.ARMS);
                    break;
                case 3: hits.add(HitlocationType.LEGS);
                    break;
                case 4: hits.add(HitlocationType.BODY);
                    break;
                case 5: hits.add(HitlocationType.BODY);
                    break;
                case 6: hits.add(HitlocationType.WAIST);
                    break;
            }
        }
        return hits;
    }
}
