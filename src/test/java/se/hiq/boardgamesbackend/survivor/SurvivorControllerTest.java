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
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

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
        CoordinateList survivorMovementOptions = survivor.getMovementOptions(new Board());
        survivor.setPosition(survivorMovementOptions.getCoordinateList().get(0)); //valid position

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Survivor> requestEntity = new HttpEntity<>(survivor, headers);
        HttpEntity<Survivor> response = restTemplate.exchange("/survivor/101", HttpMethod.PUT, requestEntity, Survivor.class);
        assertEquals(200, ((ResponseEntity<Survivor>) response).getStatusCode().value());
    }

    @Test
    public void getSurvivorOpenMoves(){
        CoordinateList openMoves = restTemplate.getForObject("/survivor/101/openMoves", CoordinateList.class);

        assertNotNull(openMoves);
        assertTrue(openMoves.getCoordinateList().size()>1);
        System.out.println("Open moves: " +openMoves.getCoordinateList().size());
    }
}
