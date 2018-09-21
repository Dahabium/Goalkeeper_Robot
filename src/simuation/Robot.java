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
        goal = new Group();

        Rectangle leftColumn = new Rectangle(20,20,30,300);
        Rectangle rightColumn = new Rectangle(450,20,30,300);
        Rectangle topBar = new Rectangle(40,20,420,30);

        goal.getChildren().addAll(leftColumn,rightColumn,topBar);

        //modules... for each module, there will be a java group so we could maintain them individually...

        //part 1
        module1 = new Group();
        Rectangle block1 = new Rectangle(230,50,40,40);
        Circle joint1 = new Circle(250,100,15);
        Rectangle block1_end = new Rectangle(230,110,40,20);

        block1_end.setArcHeight(15);
        block1_end.setArcWidth(15);
        joint1.setFill(Color.BLUE);
        block1_end.setFill(Color.GREEN);

        module1.getChildren().addAll(block1,joint1,block1_end);

        //part 2
        module2 = new Group();
        Rectangle block2 = new Rectangle(230,130,40,40);
        Circle joint2 = new Circle(250,180,15);
        Rectangle block2_end = new Rectangle(230,190,40,20);

        block2_end.setArcHeight(15);
        block2_end.setArcWidth(15);
        joint2.setFill(Color.BLUE);
        block2_end.setFill(Color.GREEN);

        module2.getChildren().addAll(block2,joint2,block2_end);

        //part 3
        module3 = new Group();
        Rectangle block3 = new Rectangle(230,210,40,40);
        Circle joint3 = new Circle(250,260,15);
        Rectangle block3_end = new Rectangle(230,270,40,20);

        block3_end.setArcHeight(15);
        block3_end.setArcWidth(15);
        joint3.setFill(Color.BLUE);
        block3_end.setFill(Color.GREEN);

        module3.getChildren().addAll(block3,joint3,block3_end);
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
