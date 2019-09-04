import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

public class SeamCarver {
    private ArrayList<ArrayList<Color>> picture;


    public SeamCarver(Picture picture) {
        this.picture = new ArrayList<ArrayList<Color>>();
        for (int i=0; i<picture.height();i++) {
            this.picture.add(new ArrayList<Color>());
            for (int j=0; j<picture.width();j++) {
                this.picture.get(i).add(picture.get(j, i));
            }
        }
    }

    public Picture picture() {
        int height = this.picture.size();
        if (height == 0) return null;
        int width = this.picture.get(0).size();
        Picture result = new Picture(width, height);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                result.set(j, i, this.picture.get(i).get(j));
            }
        }
        return result;
    }

    public int width() {
        if (this.height() == 0) return 0;
        return this.picture.get(0).size();
    }

    public int height() {
        return this.picture.size();
    }

    public int[] findHorizontalSeam() throws QueueEmptyException, QueueFullException  {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(this.height()*this.width()+2);
        for (int i=0; i<this.width(); i++) {
            graph.addEdge(new DirectedEdge(0, i, this.energy(i, 0)));
        }
        for (int j=0; j<this.width()-1; j++) {
            for (int i=1; i<this.height()-1; i++) {
                int index = this.getHorizontalIndex(i, j);
                graph.addEdge(new DirectedEdge(index, index+this.height(), this.energy(j+1, i)));
                graph.addEdge(new DirectedEdge(index, index+this.height()-1, this.energy(j+1, i-1)));
                graph.addEdge(new DirectedEdge(index, index+this.height()+1, this.energy(j+1, i+1)));
            }
            // add left boundary path and right boundary path;
            int topIndex = this.getHorizontalIndex(0, j);
            graph.addEdge(new DirectedEdge(topIndex, topIndex+this.height(), this.energy(j+1, 0)));
            graph.addEdge(new DirectedEdge(topIndex, topIndex+this.height()+1, this.energy(j+1, 1)));

            int buttomIndex = this.getHorizontalIndex(this.height()-1, j);
            graph.addEdge(new DirectedEdge(buttomIndex, buttomIndex+this.height()-1, this.energy(j+1, this.height()-1)));
            graph.addEdge(new DirectedEdge(buttomIndex, buttomIndex+this.height()-2, this.energy(j+1, this.height()-2)));
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
        return seamList.stream()
                .mapToInt(i -> (i == null ? 0 : i))
                .toArray();
    }

    public int[] findVerticalSeam() throws QueueEmptyException, QueueFullException {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(this.height()*this.width()+2);
        for (int i=0; i<this.width(); i++) {
            graph.addEdge(new DirectedEdge(0, i, this.energy(i, 0)));
        }
        for (int i=0; i<this.height()-1; i++) {
            for (int j=1; j<this.width()-1; j++) {
                int index = this.getVerticalIndex(i, j);
                graph.addEdge(new DirectedEdge(index, index+this.width(), this.energy(j, i+1)));
                graph.addEdge(new DirectedEdge(index, index+this.width()-1, this.energy(j-1, i+1)));
                graph.addEdge(new DirectedEdge(index, index+this.width()+1, this.energy(j+1, i+1)));
            }
            // add left boundary path and right boundary path;
            int leftIndex = this.getVerticalIndex(i, 0);
            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width(), this.energy(0, i+1)));
            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width()+1, this.energy(1, i+1)));

            int rightIndex = this.getVerticalIndex(i, this.width()-1);
            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width()-1, this.energy(this.width()-1, i+1)));
            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width()-2, this.energy(this.width()-2, i+1)));
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
        return seamList.stream()
                .mapToInt(i -> (i == null ? 0 : i))
                .toArray();
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != this.width()) throw new IndexOutOfBoundsException("seam length and current picture width doesn't match.");
        for (int i=0; i<seam.length; i++) {
            this.picture.get(seam[i]).remove(i);
        };
    }

    public void removeVerticalSeam(int [] seam) {
        if (seam.length != this.height()) throw new IndexOutOfBoundsException("seam length and current picture height doesn't match.");
        for (int i=0; i<seam.length; i++) {
            this.picture.get(i).remove(seam[i]);
        };
    }

    private int dualGradientEnergy(Color color1, Color color2) {
        return (int) Math.pow(color1.getRed()-color2.getRed(), 2) +
                (int) Math.pow(color1.getGreen()-color2.getGreen(), 2) +
                (int) Math.pow(color1.getBlue()-color2.getBlue(), 2);
    }

    private int energy(int col, int row) {
        int total = 0;
        if (col > 0 && col < this.width()-1 && row > 0 && row < this.height()-1) {
            total += dualGradientEnergy(this.picture.get(row).get(col-1), this.picture.get(row).get(col+1));
            total += dualGradientEnergy(this.picture.get(row-1).get(col), this.picture.get(row+1).get(col));
        } else {
            // boundary must have maximum energy
            total = ((int) Math.pow(256, 2))*6;
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
}

class PixelNode implements Comparable<PixelNode> {
    final int row;
    final int col;
    final int energy;
    PixelNode(int row, int col, int energy) {
        this.row = row;
        this.col = col;
        this.energy = energy;
    }

    @Override
    public int compareTo(@NotNull PixelNode pixelNode) {
        return Integer.compare(this.energy, pixelNode.energy);
    }
}