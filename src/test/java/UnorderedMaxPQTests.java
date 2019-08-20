import org.junit.Assert;
import org.junit.Test;

public class UnorderedMaxPQTests {
    @Test
    public void test_insert_and_top() throws QueueFullException, QueueEmptyException {
        UnorderedMaxPQ unorderedMaxPQ = new UnorderedMaxPQ(10);
        unorderedMaxPQ.insert(6);
        unorderedMaxPQ.insert(4);
        Assert.assertEquals(unorderedMaxPQ.top(), 6);
        unorderedMaxPQ.insert(10);
        Assert.assertEquals(unorderedMaxPQ.top(), 10);
    }

    @Test
    public void test_popTop() throws QueueFullException, QueueEmptyException {
        UnorderedMaxPQ unorderedMaxPQ = new UnorderedMaxPQ(10);
        unorderedMaxPQ.insert(6);
        unorderedMaxPQ.insert(4);
        unorderedMaxPQ.insert(10);
        Assert.assertEquals(unorderedMaxPQ.popTop(), 10);
        Assert.assertEquals(unorderedMaxPQ.popTop(), 6);
        Assert.assertEquals(unorderedMaxPQ.popTop(), 4);
        Assert.assertTrue(unorderedMaxPQ.isEmpty());
    }
}
