import org.apache.xmlbeans.impl.piccolo.xml.Piccolo;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

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

    public int[] findHorizontalSeam() {
        return null;
    }

    public int[] findVerticalSeam() throws QueueEmptyException, QueueFullException {
//        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(this.height()*this.width()+2);
//        for (int i=0; i<this.width(); i++) {
//            graph.addEdge(new DirectedEdge(0, i, this.energy(i, 0)));
//        }
//        for (int i=0; i<this.height()-1; i++) {
//            for (int j=1; j<this.width()-1; j++) {
//                int index = this.getIndex(i, j);
//                graph.addEdge(new DirectedEdge(index, index+this.width(), this.energy(j, i+1)));
//                graph.addEdge(new DirectedEdge(index, index+this.width()-1, this.energy(j-1, i+1)));
//                graph.addEdge(new DirectedEdge(index, index+this.width()+1, this.energy(j+1, i+1)));
//            }
//            // add left boundary path and right boundary path;
//            int leftIndex = this.getIndex(i, 0);
//            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width(), this.energy(0, i+1)));
//            graph.addEdge(new DirectedEdge(leftIndex, leftIndex+this.width()+1, this.energy(1, i+1)));
//
//            int rightIndex = this.getIndex(i, this.width()-1);
//            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width()-1, this.energy(this.width()-1, i+1)));
//            graph.addEdge(new DirectedEdge(rightIndex, rightIndex+this.width()-2, this.energy(this.width()-2, i+1)));
//        }
//
//        MinimumSpanningDigraphTree mst = new MinimumSpanningDigraphTree(graph);
        Stack<PixelNode> stack = new Stack<>();


        return null;
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
        if (col > 0) total += dualGradientEnergy(this.picture.get(row).get(col-1), this.picture.get(row).get(col));
        if (col < this.width()-1) total += dualGradientEnergy(this.picture.get(row).get(col+1), this.picture.get(row).get(col));
        if (row > 0) total += dualGradientEnergy(this.picture.get(row-1).get(col), this.picture.get(row).get(col));
        if (row < this.height()-1) total += dualGradientEnergy(this.picture.get(row+1).get(col-1), this.picture.get(row).get(col));
        return total;
    }

    private int getColFromIndex(int index) {
        return (index-1) % this.width();
    }

    private int getRowFromIndex(int index) {
        return (index-1) / this.width();
    }

    private int getIndex(int row, int col) {
        return row*this.width()+col+1;
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