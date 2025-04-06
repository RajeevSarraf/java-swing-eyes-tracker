import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainApp {
    private JPanel window;
    private JFrame fr;
    private Eye e1 = new Eye(200, 150, 50.0);
    private Eye e2;
    private Line eyesCenToCur;
    private Point centerOfEyes;
    private Point curLoc;
    private Point lastCurLoc;
    private Point pOnScreen;

    MainApp() {
        this.e2 = new Eye(this.e1.eyeball.center.x + 120, this.e1.eyeball.center.y, 50.0);
        this.curLoc = this.getCurLoc();
        this.lastCurLoc = this.curLoc;
        this.pOnScreen = new Point();
        this.centerOfEyes = new Point((this.e1.eyeball.center.x + this.e2.eyeball.center.x) / 2, this.e1.eyeball.center.y);
        this.eyesCenToCur = new Line(this.centerOfEyes, this.getCurLoc());
        this.window = new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(this.getBackground());
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                MainApp.this.e1.drawEye(g);
                MainApp.this.e2.drawEye(g);
            }
        };
    }

    void start() {
        this.window.setPreferredSize(new Dimension(500, 500));
        this.fr = new JFrame("Worker!");
        this.fr.setDefaultCloseOperation(3);
        this.fr.setSize(500, 500);
        this.fr.setContentPane(this.window);
        this.fr.setVisible(true);
        (new Thread(() -> {
            while(true) {
                this.curLoc = this.getCurLoc();
                if (!this.curLoc.equals(this.lastCurLoc)) {
                    this.findCursor();
                    this.lastCurLoc = this.curLoc;
                }
            }
        })).start();
    }

    public void findCursor() {
        this.eyesCenToCur.setLine(this.convertToScreen(this.centerOfEyes, this.window), this.curLoc);
        Point p = this.eyesCenToCur.setPointAtDistWithOrigin(this.e1.eyeball.radius - 15.0, this.convertToScreen(this.e1.eyeball.center, this.window));
        SwingUtilities.convertPointFromScreen(p, this.window);
        this.e1.iris.center.setLocation(p);
        p = this.eyesCenToCur.setPointAtDistWithOrigin(this.e1.eyeball.radius - 15.0, this.convertToScreen(this.e2.eyeball.center, this.window));
        SwingUtilities.convertPointFromScreen(p, this.window);
        this.e2.iris.center.setLocation(p);
        this.window.updateUI();
    }

    Point convertToScreen(Point p, Component c) {
        this.pOnScreen.setLocation(p);
        SwingUtilities.convertPointToScreen(this.pOnScreen, c);
        return this.pOnScreen;
    }

    Point getCurLoc() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public static void main(String[] args) {
        MainApp ma = new MainApp();
        ma.start();
    }
}
