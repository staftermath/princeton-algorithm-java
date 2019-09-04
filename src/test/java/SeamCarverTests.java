import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SeamCarverTests {
    Picture picture;
    SeamCarver seamCarver;

    @BeforeEach
    public void setUp() {
        this.picture = new Picture(getClass().getClassLoader().getResource("4x6.png").getPath());
        this.seamCarver = new SeamCarver(this.picture);
    }

    @Test
    public void testFindVerticalSeam() throws QueueEmptyException, QueueFullException {
        int[] verticalSeam = this.seamCarver.findVerticalSeam();
        int[] expected = {2, 2, 1, 1, 2, 2};
        assertArrayEquals(expected, verticalSeam);
    }

    @Test
    public void testFindHorizontalSeam() throws QueueEmptyException, QueueFullException {
        int[] horizontalSeam = this.seamCarver.findHorizontalSeam();
        int[] expected = {2, 2, 1, 1};
        assertArrayEquals(expected, horizontalSeam);
    }
}
