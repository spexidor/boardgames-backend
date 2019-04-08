package se.hiq.boardgamesbackend.game;


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


import static org.junit.Assert.*;

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
        assertTrue(showdown.length > 0);
    }

    @Test
    public void getShowdownByIdTest() {
        Showdown showdown = restTemplate.getForObject("/showdown/11", Showdown.class);
        System.out.println("Loaded showdown");
        System.out.println("Description=" +showdown.getDescription());

        assertNotNull(showdown);
    }

    @Test
    public void postShowdownTest(){
        ResponseEntity<Showdown> response = restTemplate.postForEntity("/showdown", new Showdown("post test"), Showdown.class);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getMonsterStatus().getId() != null);
        assertEquals(4, response.getBody().getSurvivors().size());

        System.out.println("monster status id: " +response.getBody().getMonsterStatus().getId());
    }

    @Test
    public void postShowdownWithIdTest(){
        Showdown[] showdowns = restTemplate.getForObject("/showdown", Showdown[].class);
        Showdown showdown = showdowns[0];

        ResponseEntity<Showdown> response = restTemplate.postForEntity("/showdown", showdown, Showdown.class);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().getId() != showdown.getId());
    }

    @Test
    public void putShowdownNoIdExistTest(){ //need id in input when updating showdown
        Showdown showdown = new Showdown("put test");
        showdown.cleanId();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Showdown> requestEntity = new HttpEntity<>(showdown, headers);
        HttpEntity<Showdown> response = restTemplate.exchange("/showdown/999", HttpMethod.PUT, requestEntity, Showdown.class);

        assertEquals(404, ((ResponseEntity<Showdown>) response).getStatusCodeValue());
    }

}
