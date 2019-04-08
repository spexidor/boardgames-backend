package se.hiq.boardgamesbackend.survivor;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SurvivorTest {
    @Test
    public void createSurvivorTest(){
        Survivor survivor = new Survivor("test");
        assertNotNull(survivor);
    }
}
