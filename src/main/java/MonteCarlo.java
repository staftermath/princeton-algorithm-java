import java.util.*;


public class MonteCarlo {
    int height;
    int width;
    int seed;
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private Random random = new Random();

    public MonteCarlo(int height , int width, int seed) {
        this.height = height;
        this.width = width;
        random.setSeed(seed);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                coordinates.add(new Coordinate(i, j));
            }
        }

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public MonteCarlo(int height, int width) {
        this(height, width, 123);
    }

    public Coordinate[] sample() {
        Coordinate[] result = new Coordinate[this.coordinates.size()];
        Collections.shuffle(this.coordinates, this.random);
        result = this.coordinates.toArray(result);
        return result;
    }
}
