package se.hiq.boardgamesbackend.survivor;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SurvivorTest {
    @Test
    public void createSurvivorTest(){
        Survivor survivor = new Survivor("test");
        assertNotNull("Survivor null", survivor);
        assertEquals(21, survivor.getMovementOptions(new Board()).size());
    }
}
