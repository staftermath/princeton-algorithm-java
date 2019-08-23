import cucumber.api.java.it.Ma;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    int[][] tiles = null;
    Board(int[][] tiles) {
        if (tiles.length == 0 || tiles[0].length == 0) {
            System.out.println("Tiles are empty.");
            System.exit(1);
        }
        if (tiles.length != tiles[0].length) {
            System.out.println("Tiles must be square.");
            System.exit(1);
        }
        this.tiles = tiles;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int tileAt(int row, int col) {
        return this.tiles[row][col];
    }

    public int hamming() {
        int distance = 0;
        int maxDistance = this.tiles.length*this.tiles.length;
        for (int i=0; i<maxDistance-1; i++) {
            if (this.tiles[i/this.tiles.length][i%this.tiles.length] != i+1) distance += 1;
        }
        if (this.tiles[this.tiles.length-1][this.tiles.length-1] != 0) distance += 1;
        return distance;
    }

    public int manhattan() {
        int distance = 0;
        int maxDistance = this.tiles.length*this.tiles.length;
        int thisTile, row, col;
        for (int i=0; i<maxDistance; i++) {
            row = i/this.tiles.length;
            col = i%this.tiles.length;
            thisTile = this.tiles[row][col];
            if (thisTile != 0) distance += Math.abs((thisTile-1)/this.tiles.length-row) + Math.abs((thisTile-1)%this.tiles.length-col);
            else distance += this.tiles.length-row+this.tiles.length-col-2;
        }
        return distance;
    }

    public boolean isGoal() { return this.hamming() == 0;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        Board o = (Board) obj;
        return Arrays.deepEquals(this.tiles, o.tiles);
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> list = new ArrayList<Board>();
        int indexOfZero = this.getIndexOfZero();
        int row = indexOfZero/this.tiles.length;
        int col = indexOfZero%this.tiles.length;
        if (col != 0) {
            int[][] leftNeighborTiles = Board.copy2DArray(this.tiles);
            Board.swap(leftNeighborTiles, row, col, row, col-1);
            list.add(new Board(leftNeighborTiles));
        }
        if (col+1 != this.tiles.length) {
            int[][] rightNeighborTiles = Board.copy2DArray(this.tiles);
            Board.swap(rightNeighborTiles, row, col, row, col+1);
            list.add(new Board(rightNeighborTiles));
        }
        if (row != 0) {
            int[][] upperNeighborTiles = Board.copy2DArray(this.tiles);
            Board.swap(upperNeighborTiles, row, col, row-1, col);
            list.add(new Board(upperNeighborTiles));
        }
        if (row+1 != this.tiles.length) {
            int[][] lowerNeighborTiles = Board.copy2DArray(this.tiles);
            Board.swap(lowerNeighborTiles, row, col, row+1, col);
            list.add(new Board(lowerNeighborTiles));
        }
        return list;
    }

    public boolean isSolvable() {return false;}

    private int getIndexOfZero() {
        int maxDistance = this.tiles.length*this.tiles.length;
        for (int i=0; i<maxDistance; i++) {
            if (this.tiles[i/this.tiles.length][i%this.tiles.length] == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {

    }

    private static int[][] copy2DArray(int[][] arr) {
        int height = arr.length;
        int width = arr[1].length;
        int[][] result = new int[height][width];
        for (int i=0; i<height; i++) {
            System.arraycopy(arr[i], 0, result[i], 0, width);
        }
        return result;
    }

    private static void swap(int[][] arr, int fromRow, int fromCol, int toRow, int toCol) {
        int temp = arr[toRow][toCol];
        arr[toRow][toCol] = arr[fromRow][fromCol];
        arr[fromRow][fromCol] = temp;
    }

}