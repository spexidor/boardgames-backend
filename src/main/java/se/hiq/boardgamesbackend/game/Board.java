package se.hiq.boardgamesbackend.game;

import java.util.List;

public class Board {
    private int width;
    private int height;
    private List<Terrain> terrainList;

    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
