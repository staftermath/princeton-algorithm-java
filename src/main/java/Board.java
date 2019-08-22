import cucumber.api.java.it.Ma;

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
        return super.equals(obj);
    }

//    public Iterable<Board> neighbors() {}

    public boolean isSolvable() {return false;}

    public static void main(String[] args) {

    }
}