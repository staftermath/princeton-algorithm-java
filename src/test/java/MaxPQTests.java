import org.junit.Assert;
import org.junit.Test;

public class MaxPQTests {
    @Test
    public void testInsertAndTop() throws QueueFullException, QueueEmptyException{
        MaxPQ maxPQ = new MaxPQ(10);
        maxPQ.insert(6);
        maxPQ.insert(4);
        Assert.assertEquals(maxPQ.top(), 6);
        maxPQ.insert(10);
        Assert.assertEquals(maxPQ.top(), 10);
    }

    @Test
    public void test_popTop() throws QueueFullException, QueueEmptyException {
        MaxPQ maxPQ = new MaxPQ(10);
        maxPQ.insert(6);
        maxPQ.insert(4);
        maxPQ.insert(10);
        Assert.assertEquals(maxPQ.popTop(), 10);
        Assert.assertEquals(maxPQ.popTop(), 6);
        Assert.assertEquals(maxPQ.popTop(), 4);
        Assert.assertTrue(maxPQ.isEmpty());
    }
}
