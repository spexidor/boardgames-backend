package se.hiq.boardgamesbackend.survivor;

import org.junit.Test;
import se.hiq.boardgamesbackend.showdown.Showdown;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SurvivorTest {
    @Test
    public void createSurvivorTest(){
        Survivor survivor = new Survivor("test");
        assertNotNull("Survivor null", survivor);
        //assertEquals(21, survivor.getMovementOptions(new Board()).size());

        Showdown showdown = new Showdown();
        List<Survivor> survivors = showdown.getSurvivors();
        Survivor survivor1 = survivors.get(0);
        survivor1.setMovesLeft(0);

        assertEquals(1, survivor1.movementOptions().size());
        assertTrue(survivor1.movementOptions().get(0).equals(survivor1.getPosition()));
    }
}
