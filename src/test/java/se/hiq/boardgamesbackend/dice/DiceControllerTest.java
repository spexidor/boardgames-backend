package se.hiq.boardgamesbackend.dice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHitsDefaultTest() {
        List<DiceResult> diceResultList = restTemplate.getForObject("/dice", List.class);

        assertNotNull(diceResultList);
        assertTrue(diceResultList.size() == 1);
    }

    @Test
    public void getHitsTest() {
        List<DiceResult> diceResultList = restTemplate.getForObject("/dice?numDice=5", List.class);

        assertNotNull(diceResultList);
        assertTrue(diceResultList.size() == 5);
        //assertTrue(diceResultList.get(0).getResult() == 9);
    }

    @Test
    public void getHitsOnlyTest() {
        List<DiceResult> diceResultList = restTemplate.getForObject("/dice?numDice=20&hitsOnly=true&toHitValue=11", List.class);

        assertNotNull(diceResultList);
        assertTrue(diceResultList.size()==0); //no hits on 11
    }
}