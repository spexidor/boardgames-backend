package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.MovementHelper;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MonsterTest {

    @Test
    public void newMonsterStatusTest(){
        Showdown showdown1 = new Showdown();
        Showdown showdown2 = new Showdown();

        Monster monster = showdown1.getMonster();
        Monster monsterStatusInvalid = showdown2.getMonster();
        monsterStatusInvalid.setMonsterPosition(new Coordinate(-1, -1));

        Board testBoard = new Board();
        List<Coordinate> movementOpts = monster.movementOptions();

        assertNotNull(monster);
        assertEquals(12, movementOpts.size()); //25 possible spaces with move 3 from center

        assertEquals(false, MovementHelper.coordinateInList(movementOpts, new Coordinate(-1, -1)));
        assertEquals(false, monster.validUpdate(monsterStatusInvalid));
        assertEquals("should cover 4 squares", 4, monster.calculateBaseCoordinates().size());
    }
}
