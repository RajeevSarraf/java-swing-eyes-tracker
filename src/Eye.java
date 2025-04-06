import java.awt.Color;
import java.awt.Graphics;

public class Eye {
    Circle eyeball;
    Circle iris;

    Eye(int x, int y, double radius) {
        this.eyeball = new Circle(x, y, radius);
        this.iris = new Circle(x, y, 15.0);
    }

    void drawEye(Graphics g) {
        Color cur = g.getColor();
        g.setColor(Color.white);
        this.eyeball.fillCircle(g);
        g.setColor(Color.black);
        this.iris.fillCircle(g);
        g.setColor(cur);
    }
}