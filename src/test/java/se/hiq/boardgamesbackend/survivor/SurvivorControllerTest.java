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
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.survivor.injury.Injury;

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
    public void getSurvivorByIdTest() {
        Survivor survivor = restTemplate.getForObject("/survivor/101", Survivor.class);

        assertNotNull("survivor null", survivor);
    }

    @Test
    public void getSurvivorOpenMoves(){
        Survivor survivor = restTemplate.getForObject("/survivor/102", Survivor.class);
        List<Coordinate> openMoves = restTemplate.getForObject("/survivor/102/openMoves", List.class);

        System.out.println("Open moves: " +openMoves.size());
        for(int n=0; n<openMoves.size(); n++){
            System.out.print(openMoves.get(n) +", ");
        }
        System.out.println("Movement: " +survivor.getMovement());
        System.out.println("Position: " +survivor.getPosition());

        assertNotNull("No open moves returned", openMoves);
        assertEquals("Wrong number of moves", 31, openMoves.size()); //move 5 from (1,1), 1 square blocked
    }

    @Test
    public void updateSurvivorTest(){

        Survivor survivor = restTemplate.getForObject("/survivor/101", Survivor.class);
        List<Coordinate> coordinateList = restTemplate.getForObject("/survivor/101/openMoves", List.class);
        System.out.println("survivor current pos: " +survivor.getPosition() +", moving to " +coordinateList.get(0));
        survivor.setPosition(new Coordinate(0, 0));


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Survivor> requestEntity = new HttpEntity<>(survivor, headers);
        HttpEntity<Survivor> response = restTemplate.exchange("/survivor/101", HttpMethod.PUT, requestEntity, Survivor.class);
        assertEquals(200, ((ResponseEntity<Survivor>) response).getStatusCode().value());
    }

    @Test
    public void getSevereInjuryTest(){
        Injury headInjury = restTemplate.getForObject("/survivor/injury?table=head", Injury.class);
        assertNotNull("null injury returned", headInjury);
        assertEquals("Head", headInjury.getLocation());
    }

    @Test
    public void getSevereInjuryBodyTest(){
        Injury bodyInjury = restTemplate.getForObject("/survivor/injury?table=body", Injury.class);
        System.out.println(bodyInjury.getTitle());
        assertNotNull("null injury returned", bodyInjury);
        assertEquals("Body", bodyInjury.getLocation());
    }

    @Test
    public void updatePostedSurvivorTest(){
        ResponseEntity<Showdown> response = restTemplate.postForEntity("/showdown", "post from updatePostedSurvivorTest", Showdown.class);
        assertEquals(200, response.getStatusCode().value());
        Long id = response.getBody().getId();
        System.out.println("post 1 showdown id: " +id);

        Survivor survivor = response.getBody().getSurvivors().get(0);
        System.out.println("Request hitlocation 0: " +survivor.getHitlocations().get(0).getType());
        System.out.println("Survivor id:: " +survivor.getId());
        assertNotNull(survivor.getId());
        survivor.getHitlocations().get(0).setLightInjury(true);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Survivor> requestEntity = new HttpEntity<>(survivor, headers);
        HttpEntity<Survivor> response2 = restTemplate.exchange("/survivor/"+survivor.getId(), HttpMethod.PUT, requestEntity, Survivor.class);
        assertEquals(200, ((ResponseEntity<Survivor>) response2).getStatusCode().value());
        System.out.println("Response hitlocation 0: " +response2.getBody().getHitlocations().get(0).getType());
        assertTrue(response2.getBody().getHitlocations().get(0).isLightInjury());
    }
}
