package exercise;

// BEGIN
public class Cottage implements Home{

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(getArea(), another.getArea());
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью "
                + getArea() + " метров";
    }
}
// END
