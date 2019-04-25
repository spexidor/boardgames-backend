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


        ResponseEntity<Showdown> response2 = restTemplate.postForEntity("/showdown", "post from postShowdownTest_2", Showdown.class);
        assertEquals(200, response2.getStatusCode().value());
        System.out.println("post 2 showdown id: " +response2.getBody().getId());

    }

    @Test
    public void putShowdownNoIdExistTest(){ //need id in input when updating showdown
        Showdown showdown = new Showdown("put test");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Showdown> requestEntity = new HttpEntity<>(showdown, headers);
        HttpEntity<Showdown> response = restTemplate.exchange("/showdown/999", HttpMethod.PUT, requestEntity, Showdown.class);

        assertEquals(404, ((ResponseEntity<Showdown>) response).getStatusCodeValue());
    }

}
