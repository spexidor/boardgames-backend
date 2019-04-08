package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.Coordinate;
import se.hiq.boardgamesbackend.game.coordinates.CoordinateList;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MonsterStatusTest {

    @Test
    public void newMonsterStatusTest(){
        MonsterStatus monsterStatus = new MonsterStatus();
        MonsterStatus monsterStatusInvalid = new MonsterStatus();
        monsterStatusInvalid.setPosition(new Coordinate(-1, -1));

        Board testBoard = new Board(10, 10);
        CoordinateList movementOpts = monsterStatus.getMovementOptions(testBoard);

        assertNotNull(monsterStatus);
        assertEquals(10, movementOpts.getCoordinateList().size()); //10 possible spaces with move 3 from corner

        assertEquals(false, movementOpts.hasCoordinate(new Coordinate(-1, -1)));
        assertEquals(false, monsterStatus.validUpdate(monsterStatusInvalid));
    }
}
