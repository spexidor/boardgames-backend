package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.Showdown;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

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
        CoordinateList movementOpts = monster.getMovementOptions(testBoard);

        assertNotNull(monster);
        assertEquals(25, movementOpts.getCoordinateList().size()); //25 possible spaces with move 3 from center

        assertEquals(false, movementOpts.hasCoordinate(new Coordinate(-1, -1)));
        assertEquals(false, monster.validUpdate(monsterStatusInvalid));
        assertEquals("should cover 4 squares", 4, monster.calculateBaseCoordinates().size());
    }
}
