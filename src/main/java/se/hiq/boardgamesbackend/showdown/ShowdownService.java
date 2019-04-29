package se.hiq.boardgamesbackend.showdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowdownService {

    @Autowired
    ShowdownRepository showdownRepository;

    public Iterable<Showdown> findAll(){
        return showdownRepository.findAll();
    }

    public Optional<Showdown> findById(Long id){
        return showdownRepository.findById(id);
    }

    public Showdown save(Showdown showdown){ return showdownRepository.save(showdown);
    }

    public Showdown getLatest() {
        return showdownRepository.findLatest();
    }
}
