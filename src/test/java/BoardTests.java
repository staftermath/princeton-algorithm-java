import org.junit.Assert;
import org.junit.Test;

public class BoardTests {
    @Test
    public void testHammingDistance() {
        int[][] goalTiles = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board board = new Board(goalTiles);
        Assert.assertEquals(board.hamming(), 0);
        int[][] tiles1 = {{3, 2, 1}, {6, 5, 4}, {0, 8, 7}};
        Board board1 = new Board(tiles1);
        Assert.assertEquals(board1.hamming(), 6);
    }

    @Test
    public void testManhattanDistance() {
        int[][] goalTiles = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board board = new Board(goalTiles);
        Assert.assertEquals(board.manhattan(), 0);
        int[][] tiles1 = {{3, 2, 1}, {6, 5, 4}, {0, 8, 7}};
        Board board1 = new Board(tiles1);
        Assert.assertEquals(board1.manhattan(), 12);
    }
}
