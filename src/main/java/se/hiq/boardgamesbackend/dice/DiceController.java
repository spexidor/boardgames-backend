package se.hiq.boardgamesbackend.dice;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DiceController {

    @GetMapping("/dice")
    public  @ResponseBody
    List<DiceResult> getHits(@RequestParam(value="numDice", defaultValue="1") int numDice, @RequestParam(value="diceSides", defaultValue="10") int diceSides, @RequestParam(value="toHitValue", defaultValue="1") int toHitValue, @RequestParam(value="hitsOnly", defaultValue="false") boolean hitsOnly) {

        assert (numDice > 0);
        assert (diceSides > 0);

        List<DiceResult> diceResults = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            diceResults.add(new DiceResult(diceSides, toHitValue));
        }

        if (hitsOnly) {
            List<DiceResult> diceResultsHits = new ArrayList<>();
            for (int i = 0; i < diceResults.size(); i++) {
                if (diceResults.get(i).isHit()) {
                    diceResultsHits.add(diceResults.get(i));
                }
            }
            return diceResultsHits;
        } else {
            return diceResults;
        }
    }
}
