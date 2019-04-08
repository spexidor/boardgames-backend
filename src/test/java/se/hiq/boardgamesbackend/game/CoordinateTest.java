package se.hiq.boardgamesbackend.game;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void newCoordinateTest(){
        Coordinate a1 = new Coordinate( 17, 17);
        Coordinate a2 = new Coordinate( 17, 17);

        assertNotNull(a1);
        assertEquals(true, a1.equals(a2));
    }
}
