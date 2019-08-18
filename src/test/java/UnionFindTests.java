import org.junit.Assert;
import org.junit.Test;

public class UnionFindTests {
    @Test
    public void testUnionIsConnected() {
        int length = 7;
        UnionFind union_find = new UnionFind(length);
        Assert.assertFalse(union_find.connected(1, 3));
        Assert.assertFalse(union_find.connected(3, 6));
        union_find.union(1,3);
        Assert.assertTrue(union_find.connected(1, 3));
    }

    @Test
    public void testCounts() {
        int length = 6;
        UnionFind union_find = new UnionFind(length);
        Assert.assertEquals(union_find.counts(), 6);
        union_find.union(4,5);
        union_find.union(1,4);
        Assert.assertEquals(union_find.counts(), 4);
        union_find.union(1, 5);
        Assert.assertEquals(union_find.counts(), 4);
    }
}
