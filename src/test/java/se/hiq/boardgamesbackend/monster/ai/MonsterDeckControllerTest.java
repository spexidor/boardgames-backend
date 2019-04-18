package se.hiq.boardgamesbackend.monster.ai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.showdown.Showdown;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonsterDeckControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAICardsTest(){
        Deck deck = restTemplate.getForObject("/monster/100/ai", Deck.class);

        assertTrue("No deck found", deck==null); //no ai deck in test data

        /*
        ResponseEntity<Showdown> response = restTemplate.postForEntity("/showdown", "post from getAICardsTest", Showdown.class);
        String description = response.getBody().getDescription();
        System.out.println("showdown id: " +response.getBody().getId());
        System.out.println("description: " +description);
        System.out.println("monster: " +response.getBody().getMonster());
        System.out.println("monster id: " +response.getBody().getMonster().getId());
        System.out.println("monster facing: " +response.getBody().getMonster().getFacing());
        System.out.println("monster pos: " +response.getBody().getMonster().getPosition());

        String url = "/monster/" +response.getBody().getId() +"/ai";
        System.out.println("url: " +url);

        Optional<Deck> deck2 = restTemplate.getForObject(url, Optional.class);
        assertTrue("no ai deck found", deck2.isPresent());
        assertTrue("ai deck to short", deck2.get().cardsInDeck.size()>10);
        */
    }
}
