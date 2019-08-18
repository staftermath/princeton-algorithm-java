import org.junit.Assert;
import org.junit.Test;

public class DequeTests {
    @Test
    public void testAddFirstPopFirst() {
        Deque deque = new Deque();
        deque.addFirst(3);
        Assert.assertEquals(deque.first(), 3);
        Assert.assertEquals(deque.size(), 1);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        Assert.assertEquals(deque.first(), 6);
        Assert.assertEquals(deque.size(), 4);
        Assert.assertEquals(deque.popFirst(), 6);
        Assert.assertEquals(deque.popFirst(), 5);
        Assert.assertEquals(deque.popFirst(), 4);
        Assert.assertEquals(deque.size(), 1);
    }

    @Test
    public void testAddLastPopLast() {
        Deque deque = new Deque();
        deque.addLast(3);
        Assert.assertEquals(deque.last(), 3);
        Assert.assertEquals(deque.size(), 1);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        Assert.assertEquals(deque.last(), 6);
        Assert.assertEquals(deque.size(), 4);
        Assert.assertEquals(deque.popLast(), 6);
        Assert.assertEquals(deque.popLast(), 5);
        Assert.assertEquals(deque.popLast(), 4);
        Assert.assertEquals(deque.size(), 1);
    }

    @Test
    public void testRepeatedAddAndPop() {
        Deque deque = new Deque();
        deque.addLast(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addLast(6);
        Assert.assertEquals(deque.last(), 6);
        Assert.assertEquals(deque.first(), 5);
        Assert.assertEquals(deque.size(), 4);
        Assert.assertEquals(deque.popFirst(), 5);
        Assert.assertEquals(deque.popLast(), 6);
        Assert.assertEquals(deque.popLast(), 3);
        Assert.assertEquals(deque.popLast(), 4);
        Assert.assertTrue(deque.empty());
    }
}
