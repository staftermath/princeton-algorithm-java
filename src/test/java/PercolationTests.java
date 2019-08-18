import org.junit.Assert;
import org.junit.Test;

public class PercolationTests {
    @Test
    public void test_construction() {
        Percolation percolation = new Percolation(3, 4);
        Assert.assertEquals(percolation.bottom, 13);
        Assert.assertEquals(percolation.top, 0);
        Assert.assertTrue(percolation.isFilled(0));
        Assert.assertTrue(percolation.isFilled(13));
        Assert.assertFalse(percolation.isConnected(2, 4));
        Assert.assertFalse(percolation.isConnected(9, 12));
    }

    @Test
    public void test_fill() {
        Percolation percolation = new Percolation(3, 4);
        percolation.fill(new Coordinate(0, 1));
        Assert.assertTrue(percolation.isConnected(0, 2));
        Assert.assertFalse(percolation.isConnected(1, 2));
        percolation.fill(new Coordinate(0, 2));
        Assert.assertTrue(percolation.isConnected(2, 3));
        percolation.fill(new Coordinate(1, 2));
        Assert.assertTrue(percolation.isConnected(0, 7));
    }

    @Test
    public void test_isPercolated() {
        Percolation percolation = new Percolation(3, 4);
        Assert.assertFalse(percolation.isPercolated());
        for (int i=0; i<3; i++) {
            percolation.fill(new Coordinate(i, 0));
        }
        Assert.assertTrue(percolation.isPercolated());
    }

    @Test
    public void test_main() {
        Percolation.main(new String[]{"3", "4", "100"});
    }
}
