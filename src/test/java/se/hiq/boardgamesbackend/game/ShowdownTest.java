package se.hiq.boardgamesbackend.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShowdownTest {

    @Test
    public void newShowdown() {
        Showdown showdown = new Showdown();

        assertNotNull(showdown);
        assertNotNull("Unable to get description", showdown.getDescription()); //default no description
        assertEquals("Id not init", (long) 0, (long) showdown.getId());
    }
}
