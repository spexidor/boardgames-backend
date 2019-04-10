package se.hiq.boardgamesbackend.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.hiq.boardgamesbackend.monster.Monster;

import javax.servlet.http.HttpServletResponse;
import java.time.Month;
import java.util.Optional;


@RestController
public class ShowdownController {

    @Autowired
    private ShowdownService showdownService;

    @GetMapping("/showdown")
    public  @ResponseBody Iterable<Showdown> getShowdowns() {
        return showdownService.findAll();
    }

    @GetMapping("/showdown/template")
    public  @ResponseBody Showdown getShowdownTemplate() {
        return new Showdown("Sample showdown");
    }

    @GetMapping("/showdown/{id}")
    public Optional<Showdown> getShowdownById(@PathVariable Long id){
        return showdownService.findById(id);
    }

    @PostMapping("/showdown")
    public Showdown createShowdown(@RequestBody String name){
        Showdown showdown = new Showdown(name);

        return showdownService.save(showdown);
    }

    @PutMapping("/showdown/{id}")
    public Showdown updatehowdown(@RequestBody Showdown showdown, @PathVariable Long id, HttpServletResponse response){
        if(id == null || id == 0 ){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        else if(!showdownService.findById(id).isPresent()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        else {
            return showdownService.save(showdown);
        }
    }
}
