package se.hiq.boardgamesbackend.monster;

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

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonsterControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getMonsterStatusTest() {
        Monster[] monsters = restTemplate.getForObject("/monster", Monster[].class);

        assertNotNull(monsters);
        assertTrue(monsters.length > 0);
    }

    @Test
    public void getMonsterStatusByIdTest() {
        Monster monster = restTemplate.getForObject("/monster/100", Monster.class);

        assertNotNull(monster);
    }

    @Test
    public void putValidMonsterStatus(){
        //get existing test
        Monster monster = restTemplate.getForObject("/monster/100", Monster.class);
        List<Coordinate> monsterPositionList = monster.movementOptions();
        monster.setPosition(monsterPositionList.get(0)); //valid position

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Monster> requestEntity = new HttpEntity<>(monster, headers);
        ResponseEntity<Monster> response = restTemplate.exchange("/monster/100", HttpMethod.PUT, requestEntity, Monster.class);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getOpenMoves(){
        List<Coordinate> openMoves = restTemplate.getForObject("/monster/100/openMoves", List.class);

        assertEquals(61, openMoves.size());

        ResponseEntity response = restTemplate.getForEntity("/monster/999/openMoves", List.class);
        assertEquals("Error 404 expected", 404, response.getStatusCodeValue());
    }
}
