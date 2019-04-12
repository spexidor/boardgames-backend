package se.hiq.boardgamesbackend.showdown;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ShowdownTest {

    @Autowired
    ShowdownRepository showdownRepository;

    @Test
    public void newShowdown() {
        Showdown showdown = new Showdown();

        assertNotNull(showdown);
        assertNotNull("Unable to get description", showdown.getDescription()); //default no description

        if(showdownRepository!=null) {
            Showdown showdownResponse = showdownRepository.save(showdown);
            assertNotNull("id not filled when saving", showdownResponse.getId());
            System.out.println("New showdown posted, id=" +showdownResponse.getId());
        }
        else{
            System.out.println("showdownRepository not init");
        }

    }
}
