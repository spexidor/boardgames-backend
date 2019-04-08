package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.game.Board;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPosition;
import se.hiq.boardgamesbackend.game.coordinates.MonsterPositionList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MonsterStatusTest {

    @Test
    public void newMonsterStatusTest(){
        MonsterStatus monsterStatus = new MonsterStatus();
        MonsterStatus monsterStatusInvalid = new MonsterStatus();
        monsterStatusInvalid.setMonsterPosition(new MonsterPosition(-1, -1));

        Board testBoard = new Board();
        MonsterPositionList movementOpts = monsterStatus.getMovementOptions(testBoard);

        assertNotNull(monsterStatus);
        assertEquals(10, movementOpts.getMonsterPositionList().size()); //10 possible spaces with move 3 from corner

        assertEquals(false, movementOpts.hasMonsterPosition(new MonsterPosition(-1, -1)));
        assertEquals(false, monsterStatus.validUpdate(monsterStatusInvalid));
    }
}
