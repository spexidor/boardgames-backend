package se.hiq.boardgamesbackend.showdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RestController
@CrossOrigin
@PropertySource(value = "classpath:buildNumber.properties")
public class ShowdownController {

    @Autowired
    private ShowdownService showdownService;

    @Value("${version}")
    private String ver;

    @GetMapping("/showdown/version")
    public @ResponseBody String getVersion() {
        return ver;
    }

    @GetMapping("/showdown")
    public  @ResponseBody Iterable<Showdown> getShowdowns() {
        return showdownService.findAll();
    }

    @GetMapping("/showdown/latest")
    public  @ResponseBody Showdown getLatestShowdown() {
        return showdownService.getLatest();
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
    public Showdown updatehowdown(@RequestBody Showdown newShowdownState, @PathVariable Long id, HttpServletResponse response){
        System.out.println("---PUT showdown request received, id=" +id +", body=" +newShowdownState);
        if(id == null || id == 0 ){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        else if(showdownService.findById(id).isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        else {
            Optional<Showdown> optionalShowdown = showdownService.findById(id);
            Showdown currentShowdown = optionalShowdown.get();
            currentShowdown.updateState(newShowdownState);
            return showdownService.save(currentShowdown);
        }
    }
}
