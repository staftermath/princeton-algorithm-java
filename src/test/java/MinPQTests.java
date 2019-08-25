import org.junit.Assert;
import org.junit.Test;

public class MinPQTests {
    @Test
    public void testInsertAndTop() throws QueueFullException, QueueEmptyException{
        MinPQ minPQ = new MinPQ(10);
        minPQ.insert(6);
        minPQ.insert(4);
        Assert.assertEquals(minPQ.top(), 4);
        minPQ.insert(10);
        Assert.assertEquals(minPQ.top(), 4);
        minPQ.insert(1);
        Assert.assertEquals(minPQ.top(), 1);
    }

    @Test
    public void test_popTop() throws QueueFullException, QueueEmptyException {
        MinPQ minPQ = new MinPQ(10);
        minPQ.insert(6);
        minPQ.insert(4);
        minPQ.insert(10);
        Assert.assertEquals(minPQ.popTop(), 4);
        Assert.assertEquals(minPQ.popTop(), 6);
        Assert.assertEquals(minPQ.popTop(), 10);
        Assert.assertTrue(minPQ.isEmpty());
    }
}
