import org.junit.Assert;
import org.junit.Test;

public class UnionFindTests {
    @Test
    public void testGetIndex() {
        int length = 5;
        UnionFind union_find = new UnionFind(length);
        for (int i=0; i<length; i ++) {
            Assert.assertEquals(union_find.getIndex(i), i);
        }
    }

    @Test
    public void testUnion() {
        int length = 7;
        UnionFind union_find = new UnionFind(length);
        union_find.union(1,3);
        Assert.assertEquals(union_find.getIndex(1), 3);
    }

    @Test
    public void testIsConnected() {
        int length = 7;
        UnionFind union_find = new UnionFind(length);
        Assert.assertFalse(union_find.isConnected(1, 3));
        Assert.assertFalse(union_find.isConnected(3, 6));
        union_find.union(1,3);
        Assert.assertTrue(union_find.isConnected(1, 3));
    }

    @Test
    public void testRoot() {
        int length = 6;
        UnionFind union_find = new UnionFind(length);
        Assert.assertEquals(union_find.root(3), 3);
        union_find.union(4,5);
        union_find.union(3,4);
        Assert.assertEquals(union_find.root(3), 5);
        Assert.assertEquals(union_find.root(4), 5);
    }
}
