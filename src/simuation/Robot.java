package simuation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Robot {
     
    Group goal, module1, module2, module3;

    
    
    
    public Robot(){
        //this has been moved and it comes straight from the object
    }

    public void rotateModule1(int angle){

        double xCircle = 0;
        double yCircle = 0;

        for (int i = 0; i < 3; i++) {
            if(module1.getChildren().get(i) instanceof Circle){
                xCircle= ((Circle) module1.getChildren().get(i)).getCenterX();
                yCircle= ((Circle) module1.getChildren().get(i)).getCenterY();
            }
        }
        Rotate rotation = new Rotate(angle, xCircle, yCircle);

        module1.getChildren().get(2).getTransforms().add(rotation);

        //TODO rotate other parts if we rotated the upper module...
        module2.getTransforms().add(rotation);
        module3.getTransforms().add(rotation);


//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
//                new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), 360)));
//
//
//        timeline.play();

    }
    public void rotateModule2(int angle){

        double xCircle = 0;
        double yCircle = 0;

        for (int i = 0; i < 3; i++) {
            if(module2.getChildren().get(i) instanceof Circle){
                xCircle= ((Circle) module2.getChildren().get(i)).getCenterX();
                yCircle= ((Circle) module2.getChildren().get(i)).getCenterY();
            }
        }
        Rotate rotation = new Rotate(angle, xCircle, yCircle);

        module2.getChildren().get(2).getTransforms().add(rotation);

        //TODO rotate other parts if we rotated the upper module...

        module3.getTransforms().add(rotation);

    }
    public void rotateModule3(int angle){

        double xCircle = 0;
        double yCircle = 0;

        for (int i = 0; i < 3; i++) {
            if(module3.getChildren().get(i) instanceof Circle){
                xCircle= ((Circle) module3.getChildren().get(i)).getCenterX();
                yCircle= ((Circle) module3.getChildren().get(i)).getCenterY();
            }
        }
        Rotate rotation = new Rotate(angle, xCircle, yCircle);

        module3.getChildren().get(2).getTransforms().add(rotation);

    }



}
