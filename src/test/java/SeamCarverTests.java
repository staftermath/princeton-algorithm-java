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

    @Test
    public void testRemoveVerticalSeam() {
        int[] seam = {2, 2, 1, 1, 2, 2};
        this.seamCarver.removeVerticalSeam(seam);
        assertEquals(3, this.seamCarver.width());
        assertEquals(6, this.seamCarver.height());
    }

    @Test
    public void testRemoveHorizontalSeam() {
        int[] seam = {2, 2, 1, 1};
        Picture trimmedPicture = new Picture(4, 5);
        for (int j=0; j<4; j++) {
            for (int i=0; i<6; i++) {
                if (i<seam[j]) trimmedPicture.set(j, i, this.picture.get(j, i));
                else if (i>seam[j]) trimmedPicture.set(j, i-1, this.picture.get(j, i));
            }
        }
        SeamCarver newSeamCarver = new SeamCarver(trimmedPicture);
        this.seamCarver.removeHorizontalSeam(seam);
        assertEquals(4, this.seamCarver.width());
        assertEquals(5, this.seamCarver.height());
        assertArrayEquals(this.seamCarver.energy, newSeamCarver.energy);
    }
 }
