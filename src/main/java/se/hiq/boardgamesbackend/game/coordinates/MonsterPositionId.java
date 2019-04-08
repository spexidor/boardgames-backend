package se.hiq.boardgamesbackend.game.coordinates;

import se.hiq.boardgamesbackend.game.Facing;
import java.io.Serializable;

public class MonsterPositionId implements Serializable {

    int x;
    int y;
    Facing facing;
}
