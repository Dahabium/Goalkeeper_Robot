import javax.swing.*;
import java.awt.*;

//just a class that represents a ball... maybe use jfx later?
public class Sphere extends JPanel {


    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(100, 100, 50, 50);

        System.out.println("painting");
    }

}