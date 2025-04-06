import java.awt.Point;

public class Line {
    private double hypo;
    private double hor;
    private double ver;

    public Line(Point a, Point b) {
        this.setLine(a, b);
    }

    void setLine(Point a, Point b) {
        this.hypo = a.distance(b);
        this.hor = a.getX() - b.getX();
        this.ver = a.getY() - b.getY();
    }

    private double sin() {
        return this.ver / this.hypo;
    }

    private double cos() {
        return this.hor / this.hypo;
    }

    Point setPointAtDistWithOrigin(double len, Point origin) {
        Point loc = new Point();
        double x = origin.getX() - len * this.cos();
        double y = origin.getY() - len * this.sin();
        loc.setLocation(x, y);
        return loc;
    }
}
