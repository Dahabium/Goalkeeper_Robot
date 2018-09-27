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

    //Holds graphics components that contain the goal
    Group goal;
    //each module contains graphical elements for each joint
    Group module1, module2, module3;
    //topgroup- contains all modules, middlegroup - contains last 2 modules....
    Group topGroup,middleGroup, bottomGroup;

    public Robot(){
        goal = new Group();

        Rectangle leftColumn = new Rectangle(20,20,30,300);
        Rectangle rightColumn = new Rectangle(450,20,30,300);
        Rectangle topBar = new Rectangle(40,20,420,30);

        goal.getChildren().addAll(leftColumn,rightColumn,topBar);

        //modules... for each module, there will be a java group so we could maintain them individually...

        //part 1
        module1 = new Group();
        Circle module1_joint = new Circle(250,65,15);
        Rectangle module1_end = new Rectangle(230,80,40,50);

        module1_end.setArcHeight(15);
        module1_end.setArcWidth(15);
        module1_joint.setFill(Color.BLUE);
        module1_end.setFill(Color.GREEN);

        module1.getChildren().addAll(module1_joint,module1_end);

        //part 2
        module2 = new Group();
        Rectangle module2_top = new Rectangle(230,130,40,40);
        Circle module2_joint = new Circle(250,185,15);
        Rectangle module2_end = new Rectangle(230,200,40,50);

        module2_end.setArcHeight(15);
        module2_end.setArcWidth(15);
        module2_joint.setFill(Color.BLUE);
        module2_end.setFill(Color.GREEN);

        module2.getChildren().addAll(module2_top,module2_joint,module2_end);

        //part 3
        module3 = new Group();
        Rectangle block3 = new Rectangle(230,250,40,40);
        Circle joint3 = new Circle(250,305,15);
        Rectangle block3_end = new Rectangle(230,320,40,40);

        block3_end.setArcHeight(15);
        block3_end.setArcWidth(15);
        joint3.setFill(Color.BLUE);
        block3_end.setFill(Color.GREEN);

        module3.getChildren().addAll(block3,joint3,block3_end);

        //hierarchical structure
        bottomGroup = new Group(module3);
        middleGroup = new Group(module2,bottomGroup);
        topGroup = new Group(module1,middleGroup);

    }

    public void rotateModule1(int angle){

        double xCircle = getJoint(module1).getCenterX();
        double yCircle = getJoint(module1).getCenterY();

        Rotate rotation = new Rotate(angle);
        rotation.setPivotX(xCircle);
        rotation.setPivotY(yCircle);

        topGroup.getTransforms().add(rotation);

        doTranslate(rotation, angle);

    }
    public void rotateModule2(int angle){

        double xCircle = getJoint(module2).getCenterX();
        double yCircle = getJoint(module2).getCenterY();

        Rotate rotation = new Rotate(angle, xCircle, yCircle);

        //use only middlegroup
        module2.getChildren().get(2).getTransforms().add(rotation);

        bottomGroup.getTransforms().add(rotation);

        doTranslate(rotation, angle);

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

        doTranslate(rotation,angle);

    }

    public void doTranslate(Rotate rotation, int angle){

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), angle)));

        timeline.play();

    }

    public Circle getJoint(Group module){
        for (int i = 0; i < 3; i++) {
            if(module.getChildren().get(i) instanceof Circle){
                return (Circle) module.getChildren().get(i);
            }
        }
        return null;
    }


}