package se.hiq.boardgamesbackend.showdown;


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
import se.hiq.boardgamesbackend.monster.ai.AICard;
import se.hiq.boardgamesbackend.monster.ai.AIDeck;
import se.hiq.boardgamesbackend.monster.ai.Card;


import static org.junit.Assert.*;
import java.util.Set;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowdownControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getShowdownTest() {
        Showdown[] showdown = restTemplate.getForObject("/showdown", Showdown[].class);
        System.out.println("Loaded showdowns");
        System.out.println("Showdown length: " +showdown.length);
        System.out.println("First showdown, description=" +showdown[0].getDescription());

        assertNotNull(showdown);
    }

    @Test
    public void getLatestShowdownTest() {
        Showdown showdown = restTemplate.getForObject("/showdown/latest", Showdown.class);
        System.out.println("Loaded showdown");
        System.out.println("Showdown, description=" +showdown.getDescription());

        assertNotNull(showdown);
    }

    @Test
    public void getShowdownByIdTest() {
        Showdown showdown = restTemplate.getForObject("/showdown/100", Showdown.class);
        System.out.println("Loaded showdown");
        System.out.println("Description=" +showdown.getDescription());

        assertNotNull(showdown);
    }

    @Test
    public void postShowdownTest(){
        ResponseEntity<Showdown> response = restTemplate.postForEntity("/showdown", "post from postShowdownTest_1", Showdown.class);

        assertEquals(200, response.getStatusCode().value());
        System.out.println("post 1 showdown id: " +response.getBody().getId());
        assertEquals(4, response.getBody().getSurvivors().size());
        assertNotNull(response.getBody().getSurvivors().get(0).getHitlocations());
        assertTrue(response.getBody().getSurvivors().get(0).getHitlocations().size() > 0);

        AIDeck aiDeck = response.getBody().getMonster().getAiDeck();
        assertTrue("no aiDeck in response", aiDeck != null);
        System.out.println("Ai deck length: " +aiDeck.getCardsInDeck().size());

        int[] deckOrderNums = new int[aiDeck.getCardsInDeck().size()];
        int n =0;
        for(Card aiCard:aiDeck.getCardsInDeck()){
            System.out.println("Card: " +aiCard.getTitle() +", order: " +aiCard.getOrderInDeck());
            deckOrderNums[n] = aiCard.getOrderInDeck();
            n++;
        }
        assertTrue("not shuffled properly", distinctValues(deckOrderNums));


        //second post
        ResponseEntity<Showdown> response2 = restTemplate.postForEntity("/showdown", "post from postShowdownTest_2", Showdown.class);
        assertEquals(200, response2.getStatusCode().value());
        System.out.println("post 2 showdown id: " +response2.getBody().getId());

    }

    public static boolean distinctValues(int[] arr){
        Set<Integer> foundNumbers = new HashSet<Integer>();
        for (int num : arr) {
            if(foundNumbers.contains(num)){
                return false;
            }
            foundNumbers.add(num);
        }
        return true;
    }


    @Test
    public void putShowdownNoIdExistTest(){ //need id in input when updating showdown
        Showdown showdown = new Showdown("put test");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Showdown> requestEntity = new HttpEntity<>(showdown, headers);
        ResponseEntity<Showdown> response = restTemplate.exchange("/showdown/999", HttpMethod.PUT, requestEntity, Showdown.class);

        assertEquals(404, response.getStatusCodeValue());
    }
}
