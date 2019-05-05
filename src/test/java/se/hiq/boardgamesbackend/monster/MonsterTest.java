package se.hiq.boardgamesbackend.monster;

import org.junit.Test;
import se.hiq.boardgamesbackend.board.Board;
import se.hiq.boardgamesbackend.showdown.Showdown;
import se.hiq.boardgamesbackend.board.coordinates.Coordinate;
import se.hiq.boardgamesbackend.board.MovementHelper;
import se.hiq.boardgamesbackend.survivor.Survivor;

import java.util.List;

import static org.junit.Assert.*;

public class MonsterTest {

    @Test
    public void newMonsterStatusTest(){
        Showdown showdown1 = new Showdown("Monster Test 1");
        Showdown showdown2 = new Showdown("Monster Test 2");

        Monster monster = showdown1.getMonster();
        Monster monsterStatusInvalid = showdown2.getMonster();
        monsterStatusInvalid.setPosition(new Coordinate(-1, -1));

        Board testBoard = new Board();
        List<Coordinate> movementOpts = monster.movementOptions();

        assertNotNull(monster);
        assertEquals(57, movementOpts.size()); //25 possible spaces with move 3 from center

        assertEquals(false, MovementHelper.coordinateInList(movementOpts, new Coordinate(-1, -1)));
        //assertEquals(false, monster.validUpdate(monsterStatusInvalid));
        assertEquals("should cover 4 squares", 4, monster.getBaseCoordinates().size());
        assertEquals("blind to few spaces", 2, monster.getBlindspot().size());
        assertEquals("blind spot not right", monster.getPosition().getY()+2, monster.getBlindspot().get(0).getY());
    }

    @Test
    public void checkFacingTest(){
        Showdown showdown = new Showdown("Check facing test");
        Monster monster = showdown.getMonster();
        List<Survivor> survivors = showdown.getSurvivors();

        monster.setPosition(new Coordinate(1,1));
        survivors.get(0).setPosition(new Coordinate(1,0)); //Above
        survivors.get(1).setPosition(new Coordinate(0,1)); //to left
        survivors.get(2).setPosition(new Coordinate(1,3)); //below
        survivors.get(3).setPosition(new Coordinate(3,1)); //to right

        assertTrue(monster.facingSurvivor(survivors.get(0)));
        assertTrue(!monster.facingSurvivor(survivors.get(1)));
        assertTrue(!monster.facingSurvivor(survivors.get(2)));
        assertTrue(!monster.facingSurvivor(survivors.get(3)));

        monster.setFacing(Facing.DOWN);
        assertTrue(!monster.facingSurvivor(survivors.get(0)));
        assertTrue(monster.facingSurvivor(survivors.get(2)));
    }
}
