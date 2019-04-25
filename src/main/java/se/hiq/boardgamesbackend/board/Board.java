package se.hiq.boardgamesbackend.board;

/**
 * Default size 22x18
 * Top left corner: (0,0)
 * Top left center: (10, 6)
 */
public class Board {
    private int width;
    private int height;
    //private List<Terrain> terrainList;

    public  Board(){
        this(22, 18);
    }
    public Board(int width, int height) throws RuntimeException
    {
        if(width > 0 && height > 0) {
            this.width = width;
            this.height = height;
        }
        else{
            throw new RuntimeException("Positive integers expected, height: " +height +", width: " +width);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
