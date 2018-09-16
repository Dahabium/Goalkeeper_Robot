import javax.swing.*;
import java.awt.*;

//Display whatever the user sees (simulation) in this class
//No calculations are involved
public class View  {

    Sphere sphere;
    RobotGraphics robotGraphics;

    JFrame topFrame, frontFrame;

    public View(){

        topFrame = new JFrame();
        frontFrame = new JFrame();

        robotGraphics = new RobotGraphics();
        sphere = new Sphere();

        topFrame.add(sphere);
        frontFrame.add(robotGraphics);

        topFrame.setSize(new Dimension(500, 500));
        topFrame.setVisible(true);

        frontFrame.setSize(new Dimension(500, 500));
        frontFrame.setVisible(true);

        topFrame.setLocation(500,300);
        frontFrame.setLocation(1000,300);
    }

    void setSphereLoc(int x, int y){

        System.out.println("setting new location");
        sphere.setLocation(x,y);

    }


}
