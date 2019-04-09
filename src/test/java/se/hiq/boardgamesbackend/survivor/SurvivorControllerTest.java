package se.hiq.boardgamesbackend.survivor;

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
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurvivorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getSurvivorsTest() {
        Survivor[] survivors = restTemplate.getForObject("/survivor", Survivor[].class);

        assertNotNull(survivors);
        assertTrue(survivors.length > 0);
    }

    @Test
    public void updateSurvivorTest(){

        Survivor survivor = restTemplate.getForObject("/survivor/101", Survivor.class);
        List<Coordinate> survivorMovementOptions = survivor.getMovementOptions(new Board());
        survivor.setPosition(survivorMovementOptions.get(0)); //valid position

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Survivor> requestEntity = new HttpEntity<>(survivor, headers);
        HttpEntity<Survivor> response = restTemplate.exchange("/survivor/101", HttpMethod.PUT, requestEntity, Survivor.class);
        assertEquals(200, ((ResponseEntity<Survivor>) response).getStatusCode().value());
    }

    @Test
    public void getSurvivorOpenMoves(){
        Survivor survivor = restTemplate.getForObject("/survivor/101", Survivor.class);
        List<Coordinate> openMoves = restTemplate.getForObject("/survivor/101/openMoves", List.class);

        System.out.println("Open moves: " +openMoves.size());
        assertNotNull("No open moves returned", openMoves);
        //TODO: add assertion
        //assertEquals("Wrong number of moves", 25, openMoves.size()); //move 5 from (0,1), 1 square blocked

        System.out.println("Movement: " +survivor.getMovement());
        System.out.println("Position: " +survivor.getPosition());
    }
}
