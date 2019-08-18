public class Coordinate {
    private int height;
    private int width;

    public Coordinate(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Coordinate(Coordinate other) {
        this.height = other.getHeight();
        this.width = other.getWidth();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (this.getClass() != other.getClass()) return false;
        Coordinate o = (Coordinate) other;
        return this.getHeight() == o.getHeight() && this.getWidth() == o.getWidth();
    }
}