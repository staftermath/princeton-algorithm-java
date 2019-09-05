import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SeamCarver {
    private Color[][] picture;

    int[][] energy;

    public SeamCarver(Picture picture) {
        this.picture = new Color[picture.height()][picture.width()];
        for (int i=0; i<picture.height();i++) {
            for (int j = 0; j < picture.width(); j++) {
                this.picture[i][j] = picture.get(j, i);
            }
        }
        this.calculateEnergy();
    }

    private void calculateEnergy() {
        this.energy = new int[this.height()][this.width()];
        for (int i=0; i<this.height();i++) {
            for (int j=0; j<this.width();j++) {
                this.energy[i][j] = this.energy(j, i);
            }
        }
    }
    public Picture picture() {
        int height = this.picture.length;
        if (height == 0) return null;
        int width = this.picture[0].length;
        Picture result = new Picture(width, height);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                result.set(j, i, this.picture[i][j]);
            }
        }
        return result;
    }

    public int width() {
        if (this.height() == 0) return 0;
        return this.picture[0].length;
    }

    public int height() {
        return this.picture.length;
    }

    public int[] findHorizontalSeam() throws QueueEmptyException, QueueFullException  {
        if (this.height() <= 2) return null;
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(this.height()*this.width()+2);
        for (int i=0; i<this.height(); i++) {
            graph.addEdge(new DirectedEdge(0, i+1, this.energy[i][0]));
        }
        for (int j=0; j<this.width()-1; j++) {
            for (int i=1; i<this.height()-1; i++) {
                int index = this.getHorizontalIndex(i, j);
                graph.addEdge(new DirectedEdge(index, index+this.height(), this.energy[i][j+1]));
                graph.addEdge(new DirectedEdge(index, index+this.height()-1, this.energy[i-1][j+1]));
                graph.addEdge(new DirectedEdge(index, index+this.height()+1, this.energy[i+1][j+1]));
            }
            // add left boundary path and right boundary path;
            int topIndex = this.getHorizontalIndex(0, j);
            graph.addEdge(new DirectedEdge(topIndex, topIndex+this.height(), this.energy[0][j+1]));
            graph.addEdge(new DirectedEdge(topIndex, topIndex+this.height()+1, this.energy[1][j+1]));

            int buttomIndex = this.getHorizontalIndex(this.height()-1, j);
            graph.addEdge(new DirectedEdge(buttomIndex, buttomIndex+this.height(), this.energy[this.height()-1][j+1]));
            graph.addEdge(new DirectedEdge(buttomIndex, buttomIndex+this.height()-1, this.energy[this.height()-2][j+1]));
        }
        // add 0 weight to bottom row to dummy node
        for (int j=0; j<this.height(); j++) {
            graph.addEdge(new DirectedEdge(graph.V()-this.height()-1+j, graph.V()-1, 0.0));
        }

        SP sp = new SP(graph, 0);
        ArrayList<Integer> seamList = new ArrayList<>();
        for (DirectedEdge e: sp.pathTo(graph.V()-1)) {
            if (e.to() != graph.V()-1) seamList.add(this.getRowFromHorizontalIndex(e.to()));
        }
        int[] result = seamList.stream()
                .mapToInt(i -> (i == null ? 0 : i))
                .toArray();
        result[0] = result[1];
        result[result.length-1] = result[result.length-2];
        return result;
    }

    public int[] findVerticalSeam() throws QueueEmptyException, QueueFullException {
        if (this.width() <= 2) return null;
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(this.height()*this.width()+2);
        for (int j=0; j<this.width(); j++) {
            graph.addEdge(new DirectedEdge(0, j+1, this.energy[0][j]));
        }
        for (int i=0; i<this.height()-1; i++) {
            for (int j=1; j<this.width()-1; j++) {
                int index = this.getVerticalIndex(i, j);
                graph.addEdge(new DirectedEdge(index, index+this.width(), this.energy[i+1][j]));
                graph.addEdge(new DirectedEdge(index, index+this.width()-1, this.energy[i+1][j-1]));
                graph.addEdge(new DirectedEdge(index, index+this.width()+1, this.energy[i+1][j+1]));
            }
            // add left boundary path and right boundary path;
            int leftIndex = this.getVerticalIndex(i, 0);
            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width(), this.energy[i+1][0]));
            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width()+1, this.energy[i+1][1]));

            int rightIndex = this.getVerticalIndex(i, this.width()-1);
            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width(), this.energy[i+1][this.width()-1]));
            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width()-1, this.energy[i+1][this.width()-2]));
        }
        // add 0 weight to bottom row to dummy node
        for (int i=0; i<this.width(); i++) {
            graph.addEdge(new DirectedEdge(graph.V()-this.width()-1+i, graph.V()-1, 0.0));
        }

        SP sp = new SP(graph, 0);
        ArrayList<Integer> seamList = new ArrayList<>();
        for (DirectedEdge e: sp.pathTo(graph.V()-1)) {
            if (e.to() != graph.V()-1) seamList.add(this.getColFromVerticalIndex(e.to()));
        }
        int[] result = seamList.stream()
                .mapToInt(i -> (i == null ? 0 : i))
                .toArray();
        result[0] = result[1];
        result[result.length-1] = result[result.length-2];
        return result;
    }

    public void removeHorizontalSeam(int[] seam) {
        Color[][] newPicture = new Color[this.height()-1][this.width()];
        for (int j=0; j<this.width(); j++) {
            for (int i=0; i<this.height(); i++) {
                if (i<seam[j]) newPicture[i][j] = this.picture[i][j];
                else if (i>seam[j]) newPicture[i-1][j] = this.picture[i][j];
            }
        }
        this.picture = newPicture;
        this.calculateEnergy();
    }

    public void removeVerticalSeam(int [] seam) {
        Color[][] newPicture = new Color[this.height()][this.width()-1];
        for (int i=0; i<this.height(); i++) {
            for (int j=0; j<this.width(); j++) {
                if (j<seam[i]) newPicture[i][j] = this.picture[i][j];
                else if (j>seam[i]) newPicture[i][j-1] = this.picture[i][j];
            }
        }
        this.picture = newPicture;
        this.calculateEnergy();
    }

    private int dualGradientEnergy(Color color1, Color color2) {
        return (int) Math.pow(color1.getRed()-color2.getRed(), 2) +
                (int) Math.pow(color1.getGreen()-color2.getGreen(), 2) +
                (int) Math.pow(color1.getBlue()-color2.getBlue(), 2);
    }

    private int energy(int col, int row) {
        int total = 0;
        if (col > 0 && col < this.width()-1 && row > 0 && row < this.height()-1) {
            total += dualGradientEnergy(this.picture[row][col-1], this.picture[row][col+1]);
            total += dualGradientEnergy(this.picture[row-1][col], this.picture[row+1][col]);
        } else {
            // boundary must have maximum energy
            total = this.maxEnergy();
        }
        return total;
    }

    private int getColFromVerticalIndex(int index) {
        return (index-1) % this.width();
    }

    private int getRowFromVerticalIndex(int index) {
        return (index-1) / this.width();
    }

    private int getVerticalIndex(int row, int col) {
        return row*this.width()+col+1;
    }

    private int getColFromHorizontalIndex(int index) {
        return (index-1) / this.height();
    }

    private int getRowFromHorizontalIndex(int index) {
        return (index-1) % this.height();
    }

    private int getHorizontalIndex(int row, int col) {
        return col*this.height()+row+1;
    }

    private int maxEnergy() {
        return ((int) Math.pow(256, 2))*6;
    }

    public void save(String filename) {
        Picture newPicture = new Picture(this.width(), this.height());
        for (int i=0; i<this.height(); i++) {
            for (int j=0; j<this.width(); j++) {
                newPicture.set(j, i, this.picture[i][j]);
            }
        }
        newPicture.save(filename);
    }

    public static void main(String[] args) throws QueueEmptyException, QueueFullException {
        String input = args[0];
        int numberOfTrimming = Integer.parseInt(args[1]);
        boolean verticalTrimming = Boolean.parseBoolean(args[2]);
        String output = args[3];

        SeamCarver seamCarver = new SeamCarver(new Picture(input));
        int iter=0;
        Logger logger = Logger.getLogger("seam carver");
        while (iter<numberOfTrimming) {
            if (iter%100 == 0) logger.info(String.format("Iter %d", iter));
            if (verticalTrimming) {
                int[] verticalSeam = seamCarver.findVerticalSeam();
                if (verticalSeam == null) return;
                seamCarver.removeVerticalSeam(verticalSeam);
            } else {
                int[] horizontalSeam = seamCarver.findHorizontalSeam();
                if (horizontalSeam == null) return;
                seamCarver.removeHorizontalSeam(horizontalSeam);
            }
            iter++;
        }
        seamCarver.save(output);
    }
}
