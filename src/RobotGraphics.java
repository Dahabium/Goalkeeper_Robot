import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RobotGraphics extends JPanel {

    public ArrayList<Shape> shapes = new ArrayList<>();

    private Point mousePt = new Point(10,10);

    public RobotGraphics() {

        Shape shape = new Rectangle(10,10,60,70);

        shapes.add(shape);

        Shape shape2 = new Rectangle(100,10,20,10);

        shapes.add(shape2);

    }

    public void updateRectange(Point point){

        shapes.set(0,new Rectangle(point.x,point.y,60,70));

    }



    @Override
    public void paintComponent(Graphics g){

        Rectangle rectangle = new Rectangle(20,20,50,30);

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < shapes.size(); i++) {
            g2.fill(shapes.get(i));
            System.out.println("added a shape!");
        }

        g2.setColor(Color.BLUE);

        System.out.println("painting");
    }
}
