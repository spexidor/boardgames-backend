package se.hiq.boardgamesbackend.monster.ai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.showdown.Showdown;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonsterDeckControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAICardsTest() {
        Deck deck = restTemplate.getForObject("/monster/100/ai", Deck.class);
        assertTrue("No deck found", deck == null); //no ai deck in test data
    }

    @Test
    public void updateAICardsTest(){
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

        AIDeck deck2 = restTemplate.getForObject(url, AIDeck.class);
        assertTrue("ai deck to short", deck2.getCardsInDeck().size()>3);
        assertTrue("wrong class", deck2.cardsInDeck.get(0) instanceof AICard);
        System.out.println("deck size: " +deck2.getCardsInDeck().size());

        AIDeck deck3 = response.getBody().getMonster().getAiDeck();
        Card removedCard = deck3.getCardsInDeck().remove(0);
        System.out.println("removing card " +removedCard +" of type " +removedCard.getClass());
        deck3.getCardsInDiscard().add(removedCard);
        System.out.println("deck size after get: " +deck3.getCardsInDeck().size());
        System.out.println("discard size: " +deck3.getCardsInDiscard().size());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Deck> requestEntity = new HttpEntity<>(deck3, headers);
        HttpEntity<Deck> response2 = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Deck.class);
        assertEquals("incorrect status code in response", 200, ((ResponseEntity<Deck>) response2).getStatusCodeValue());
        assertEquals("discard size incorrect", 1, response2.getBody().getCardsInDiscard().size());


    }
}
