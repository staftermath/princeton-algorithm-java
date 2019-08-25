import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardTests {
    private int[][] tilesGoal = {{1,2,3}, {4,5,6}, {7,8,0}};
    private int[][] tilesGoalVerticalReverse = {{3, 2, 1}, {6, 5, 4}, {0, 8, 7}};
    private int[][] tilesNoLeftNeighbor = {{1, 2, 3}, {0, 4, 5}, {6, 7, 8}};
    private int[][] tilesNoRightNeighbor = {{1, 2, 3}, {5, 4, 0}, {6, 7, 8}};
    private int[][] tilesNoUpperNeighbor = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}};
    private int[][] tilesNoLowerNeighbor = {{1, 7, 2}, {3, 4, 5}, {6, 0, 8}};
    private int[][] tilesHasAllNeighbor = {{1, 2, 3}, {5, 0, 7}, {6, 4, 8}};
    private Board boardNoLeftNeighbor = new Board(tilesNoLeftNeighbor);
    private Board boardNoRightNeighbor = new Board(tilesNoRightNeighbor);
    private Board boardNoUpperNeighbor = new Board(tilesNoUpperNeighbor);
    private Board boardNoLowerNeighbor = new Board(tilesNoLowerNeighbor);
    private Board boardHasAllNeighbor = new Board(tilesHasAllNeighbor);
    private Board boardGoal = new Board(tilesGoal);
    private Board boardGoalVerticalReverse = new Board(tilesGoalVerticalReverse);

    @Test
    public void testHammingDistance() {
        Assert.assertEquals(this.boardGoal.hamming(), 0);
        Assert.assertEquals(this.boardGoalVerticalReverse.hamming(), 6);
    }

    @Test
    public void testManhattanDistance() {
        Assert.assertEquals(this.boardGoal.manhattan(), 0);
        Assert.assertEquals(this.boardGoalVerticalReverse.manhattan(), 12);
    }

    @Test
    public void testBoardEquals() {
        int[][] tilesGoalDuplicate = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board boardGoalDuplicate = new Board(tilesGoalDuplicate);
        Assert.assertEquals(this.boardGoal, this.boardGoal);
        Assert.assertEquals(this.boardGoal, boardGoalDuplicate);
        Assert.assertNotEquals(this.boardGoal, this.boardGoalVerticalReverse);
    }

    @Test
    public void testNeighbors() {
        /*
        TODO: add test for noRightNeighbors, noUpperNeighbors, noLowerNeighbors and hasAllNeighbors
         */
        ArrayList<Board> noLeftNeighbors = (ArrayList<Board>)this.boardNoLeftNeighbor.neighbors();
        ArrayList<Board> expected = new ArrayList<Board>();
        int[][] upperNeighbor = {{0, 2, 3}, {1, 4, 5}, {6, 7, 8}};
        int[][] lowerNeighbor = {{1, 2, 3}, {6, 4, 5}, {0, 7, 8}};
        int[][] rightNeighbor = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        Assert.assertEquals(noLeftNeighbors.size(), 3);
        Assert.assertEquals(noLeftNeighbors.get(0), new Board(rightNeighbor));
        Assert.assertEquals(noLeftNeighbors.get(1), new Board(upperNeighbor));
        Assert.assertEquals(noLeftNeighbors.get(2), new Board(lowerNeighbor));
    }

    @Test
    public void testIsSolvable() {
        Assert.assertTrue(boardGoal.isSolvable());
        Assert.assertFalse(boardNoRightNeighbor.isSolvable());
        Assert.assertTrue(boardHasAllNeighbor.isSolvable());
        int[][] tile4by4Solvable = {{1, 2, 3, 4}, {5, 6, 0, 8}, {9, 10, 7, 11}, {13, 14, 15, 12}};
        Board board4by4Solvable = new Board(tile4by4Solvable);
        Assert.assertTrue(board4by4Solvable.isSolvable());
        int[][] tile4by4Unsolvable = {{2, 1, 3, 4}, {5, 6, 0, 8}, {9, 10, 7, 11}, {13, 14, 15, 12}};
        Board board4by4Unsolvable = new Board(tile4by4Unsolvable);
        Assert.assertFalse(board4by4Unsolvable.isSolvable());
    }

}
