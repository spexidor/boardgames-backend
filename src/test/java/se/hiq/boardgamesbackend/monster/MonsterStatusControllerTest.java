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
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPosition;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPositionList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonsterStatusControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getMonsterStatusTest() {
        MonsterStatus[] monsterStatuses = restTemplate.getForObject("/monsterStatus", MonsterStatus[].class);

        assertNotNull(monsterStatuses);
        assertTrue(monsterStatuses.length > 0);
    }

    @Test
    public void getMonsterStatusByIdTest() {
        MonsterStatus monsterStatuses = restTemplate.getForObject("/monsterStatus/10", MonsterStatus.class);

        assertNotNull(monsterStatuses);
    }

    @Test
    public void putInvalidMonsterStatus(){
        //get existing test
        MonsterStatus monsterStatus = restTemplate.getForObject("/monsterStatus/10", MonsterStatus.class);
        monsterStatus.setPosition(new MonsterPosition(-1, -1)); //invalid position

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MonsterStatus> requestEntity = new HttpEntity<>(monsterStatus, headers);
        HttpEntity<MonsterStatus> response = restTemplate.exchange("/monsterStatus/10", HttpMethod.PUT, requestEntity, MonsterStatus.class);
        assertEquals(400, ((ResponseEntity<MonsterStatus>) response).getStatusCode().value());
    }

    @Test
    public void putValidMonsterStatus(){
        //get existing test
        MonsterStatus monsterStatus = restTemplate.getForObject("/monsterStatus/10", MonsterStatus.class);
        MonsterPositionList monsterPositionList = monsterStatus.getMovementOptions(new Board());
        monsterStatus.setPosition(monsterPositionList.getMonsterPositionList().get(0)); //valid position

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MonsterStatus> requestEntity = new HttpEntity<>(monsterStatus, headers);
        HttpEntity<MonsterStatus> response = restTemplate.exchange("/monsterStatus/10", HttpMethod.PUT, requestEntity, MonsterStatus.class);
        assertEquals(200, ((ResponseEntity<MonsterStatus>) response).getStatusCode().value());
    }
}
