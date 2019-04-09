package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MonsterStatusTest {

    @Test
    public void newMonsterStatusTest(){
        MonsterStatus monsterStatus = new MonsterStatus();
        MonsterStatus monsterStatusInvalid = new MonsterStatus();
        monsterStatusInvalid.setMonsterPosition(new Coordinate(-1, -1));

        Board testBoard = new Board();
        CoordinateList movementOpts = monsterStatus.getMovementOptions(testBoard);

        assertNotNull(monsterStatus);
        assertEquals(25, movementOpts.getCoordinateList().size()); //25 possible spaces with move 3 from center

        assertEquals(false, movementOpts.hasCoordinate(new Coordinate(-1, -1)));
        assertEquals(false, monsterStatus.validUpdate(monsterStatusInvalid));
    }
}
