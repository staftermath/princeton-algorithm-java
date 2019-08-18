import org.junit.Assert;
import org.junit.Test;

public class MonteCarloTests {
    @Test
    public void testSetterGetter() {
        MonteCarlo monte_carlo = new MonteCarlo(5,6);
        Assert.assertEquals(monte_carlo.getHeight(), 5);
        Assert.assertEquals(monte_carlo.getWidth(), 6);

        MonteCarlo monte_carlo_with_seed = new MonteCarlo(5,6, 321);
        Assert.assertEquals(monte_carlo_with_seed.getHeight(), 5);
        Assert.assertEquals(monte_carlo_with_seed.getWidth(), 6);
    }

    @Test
    public void testSample() {
        MonteCarlo monte_carlo = new MonteCarlo(2,3);
        Coordinate[] sample = monte_carlo.sample();
        Assert.assertTrue(sample[1].equals(new Coordinate(1, 1)));
        Assert.assertTrue(sample[3].equals(new Coordinate(1, 0)));
        Coordinate[] another_sample = monte_carlo.sample();
        Assert.assertTrue(another_sample[1].equals(new Coordinate(1, 0)));
        Assert.assertTrue(another_sample[3].equals(new Coordinate(1, 1)));
    }
}
