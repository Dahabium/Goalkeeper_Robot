
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;

//Display whatever the user sees (simulation) in this class
//No calculations are involved
public class View  {


    JPanel panel;
    Sphere sphere;

    public View(){

        JFrame frame = new JFrame();

        panel= new JPanel();

        sphere = new Sphere();

        frame.add(sphere);



        panel.setSize(300, 300);
        panel.setVisible(true);
        panel.setFocusable(true);


        frame.setSize(new Dimension(300, 300));
        frame.setVisible(true);


        frame.getContentPane().add(panel);
    }

    void setSphereLoc(int x, int y){

        System.out.println("setting new location");
        sphere.setLocation(x,y);
    }


}
