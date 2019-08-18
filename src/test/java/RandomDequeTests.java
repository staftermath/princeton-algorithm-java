import org.junit.Assert;
import org.junit.Test;

public class RandomDequeTests {
    @Test
    public void testSample() {
        RandomDeque random_deque = new RandomDeque();
        for (int i=0; i<10; i++) random_deque.addLast(i);
        Assert.assertEquals(random_deque.sample(), 2);
        Assert.assertEquals(random_deque.sample(), 0);

        RandomDeque random_deque_with_seed = new RandomDeque(321);
        for (int i=0; i<10; i++) random_deque_with_seed.addLast(i);
        Assert.assertEquals(random_deque_with_seed.sample(), 9);
        Assert.assertEquals(random_deque_with_seed.sample(), 2);

    }
}
