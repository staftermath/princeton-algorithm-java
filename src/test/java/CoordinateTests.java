import org.junit.Assert;
import org.junit.Test;

public class CoordinateTests {
    @Test
    public void testGetter() {
        Coordinate coord = new Coordinate(3, 4);
        Assert.assertEquals(coord.getHeight(), 3);
        Assert.assertEquals(coord.getWidth(), 4);
    }

    @Test
    public void testSetter() {
        Coordinate coord = new Coordinate(3, 4);
        coord.setHeight(6);
        coord.setWidth(9);
        Assert.assertEquals(coord.getHeight(), 6);
        Assert.assertEquals(coord.getWidth(), 9);
    }

    @Test
    public void testCopy() {
        Coordinate target = new Coordinate(3, 4);
        Coordinate result = new Coordinate(target);
        Assert.assertNotSame(result, target);
        Assert.assertEquals(result.getHeight(), 3);
        Assert.assertEquals(result.getWidth(), 4);
        Assert.assertTrue(result.equals(target));
    }
}
