import java.awt.Graphics;
import java.awt.Point;

public class Circle {
    Point center;
    double radius;

    Circle(int x, int y, double radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    void fillCircle(Graphics g) {
        int size = (int)(this.radius + this.radius);
        int x = (int)((double)this.center.x - this.radius);
        int y = (int)((double)this.center.y - this.radius);
        g.fillOval(x, y, size, size);
    }
}
