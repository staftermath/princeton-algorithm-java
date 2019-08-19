import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.HashSet;

public class Collinear {
    int numberOfSegments = 0;

    public Collinear() {

    }

    private LineSegment[] segments(Point[] points) {
        if (points.length == 0) return new LineSegment[0];
        if (points.length <= 2) {
            LineSegment[] lineSegments = new LineSegment[1];
            LineSegment singleLineSegment = new LineSegment();
            for (Point p: points) {
                singleLineSegment.addPoint(p);
            }
            lineSegments[0] = singleLineSegment;
            return lineSegments;
        }
        // calculate all Slopes O(N^2)
        LineSegment[] lineSegments = new LineSegment[points.length-3];
        int counter = 0;
        for (int i=0; i<points.length-3; i++) {
            Slope[] slopes = new Slope[points.length-i-1];
            for (int j=i+1; j<points.length; j++) {
                slopes[j-i-1] = points[i].slopeTo(points[j]);
            }
            Arrays.sort(slopes);
            for (int j=0; j<slopes.length-2;) {
                if (slopes[j].equals(slopes[j+2])) {
                    LineSegment lineSegment = new LineSegment();
                    do {
                        lineSegment.addPoint(slopes[j].from);
                        lineSegment.addPoint(slopes[j].to);
                        j++;
                    } while (j<slopes.length && slopes[j].equals(slopes[j-1]));
                    lineSegments[counter++] = lineSegment;
                }

            }
        }
        return lineSegments;
    }
}


class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    Slope slopeTo(Point other) {
        return new Slope(this.x-other.x, this.y-other.y);
    }
}


class Slope implements Comparable<Slope> {
    int a;
    int b;
    Point from = null;
    Point to = null;

    Slope(Point from, Point to) {
        this.a = to.x - from.x;
        this.b = to.y - from.y;
        this.from = from;
        this.to = to;
        this.reduce();
    }

    Slope(int num, int denom) {
        this.a = num;
        this.b = denom;
        this.from = new Point(0, 0);
        this.to = new Point(num, denom);
        this.reduce();
    }

    private void reduce() {
        if (this.a == 0 && this.b == 0) return;
        if (this.a == 0 ) {this.b = 1; return;}
        if (this.b == 0) {this.a = 1; return;}
        if (this.a < 0) {this.a = -this.a; this.b = -this.b;}
        int gcd = Slope.gcd(this.a, this.b);
        this.a /= gcd;
        this.b /= gcd;
    }

    static int gcd(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        int r = 1;
        while (r != 0) {
            r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    @Override
    public int compareTo(@NotNull Slope slope) {
        if (this.lessThan(slope)) return -1;
        else if (slope.lessThan(this)) return 1;
        else return 0;
    }

    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) return false;
        Slope o = (Slope) other;
        return this.a == o.a && this.b == o.b;
    }

    public boolean lessThan(Slope other) {
        if (this.b == 0 || other.b == 0) return this.b < other.b;
        // vertical line has (pos) infinite slope
        if (this.a == 0 && other.a != 0) return false;
        if (this.a != 0 && other.a == 0) return true;
        return this.a*1.0/this.b < other.a*1.0/other.b;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.a, this.b);
    }
}

class LineSegment {
    private HashSet<Point> points = new HashSet<Point>();

    LineSegment() {}

    public Point[] getPoints() {
        Point[] result = new Point[this.points.size()];
        this.points.toArray(result);
        return result;
    }

    void addPoint(Point p) {
        this.points.add(p);
    }

    boolean isEmpty() {
        return this.points.isEmpty();
    }

    int size() {
        return this.points.size();
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "";
        Point[] arrPoints = this.getPoints();
        String[] arrStrPoints = new String[arrPoints.length];
        for (int i=0; i<arrStrPoints.length; i++) {
            arrStrPoints[i] = arrPoints[i].toString();
        }
        return String.join("->", arrStrPoints);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
