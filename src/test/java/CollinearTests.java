import org.junit.Assert;
import org.junit.Test;

public class CollinearTests {
    @Test
    public void testPointSlopeTo() {

    }

    public void testSlopeGCD() {
        Assert.assertEquals(Slope.gcd(15, 10), 5);
        Assert.assertEquals(Slope.gcd(1024, 768), 256);
        Assert.assertEquals(Slope.gcd(6, -3), 3);
        Assert.assertEquals(Slope.gcd(-5, -1), 1);
        Assert.assertEquals(Slope.gcd(1, -1), 1);
    }

    @Test
    public void testSlopeCompareTo() {
        Slope slope1 = new Slope(15, 10);
        Slope slope2 = new Slope(-15, 10);
        Slope slope3 = new Slope(0, 1);
        Slope slope4 = new Slope(-2, 0);
        Slope slope5 = new Slope(5, 0);
        Assert.assertEquals(slope2.compareTo(slope1), -1);
        Assert.assertEquals(slope3.compareTo(slope1), 1);
        Assert.assertEquals(slope2.compareTo(slope4), -1);
        Assert.assertEquals(slope4.compareTo(slope1), -1);

        Assert.assertEquals(slope4.compareTo(slope5), 0);
        Assert.assertEquals(slope4.compareTo(slope1), -1);
        Assert.assertEquals(slope1.compareTo(slope2), 1);

    }

    @Test
    public void testLineSegmentAddPoint() {
        LineSegment seg = new LineSegment();
        seg.addPoint(new Point(3, 4));
        seg.addPoint(new Point(4, 5));
        Assert.assertEquals(seg.size(), 2);
    }

    @Test
    public void testLineSegmentToString() {
        LineSegment seg = new LineSegment();
        Assert.assertEquals(seg.toString(), "");
        seg.addPoint(new Point(3, 4));
        seg.addPoint(new Point(4, 5));
        Assert.assertEquals(seg.toString(), "(3, 4)->(4, 5)");
    }
}
