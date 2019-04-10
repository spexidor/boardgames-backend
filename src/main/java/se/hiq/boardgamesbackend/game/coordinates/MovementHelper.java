package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.monster.Monster;
import se.hiq.boardgamesbackend.survivor.Survivor;

import java.util.List;

public class MovementHelper {
    public static List<Coordinate> getSurvivorMovement(Survivor survivor, List<Survivor> survivors, Monster monster) {

        CoordinateList movementOpts = new CoordinateList(survivor.getPosition());
        movementOpts.addSteps(survivor.getMovement());
        movementOpts.removeInvalidMovements(monster, survivors);
        return movementOpts.getCoordinateList();
    }
}
