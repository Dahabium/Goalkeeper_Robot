package simuation;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.io.IOException;

public class RobotController {

    @FXML
    public Group robotElements;
    @FXML
    public BorderPane RobotPane;

    Robot robot;

    RobotAnimationControl control;

    int CurrentModuleRoatation = 0;


    public void initialize() {
        robot = new Robot();

        robotElements.getChildren().addAll(robot.goal, robot.topGroup);
        control = new RobotAnimationControl();
    }

    @FXML
    public void keyPressed(KeyEvent event) throws IOException {

        if(event.getCode() == KeyCode.DIGIT1){
            CurrentModuleRoatation = 1;
        }
        if(event.getCode() == KeyCode.DIGIT2){
            CurrentModuleRoatation = 2;
        }
        if(event.getCode() == KeyCode.DIGIT3){
            CurrentModuleRoatation = 3;
        }

        if (event.getCode() == KeyCode.LEFT) {

            if(CurrentModuleRoatation == 1){
                robot.rotateModule1(10);
            }
            else if(CurrentModuleRoatation == 2){
                robot.rotateModule2(10);
            }
            else if(CurrentModuleRoatation == 3){
                robot.rotateModule3(10);
            }
        }

        if(event.getCode() == KeyCode.RIGHT){
            System.out.println("Moving rght");

            if(CurrentModuleRoatation == 1){
                robot.rotateModule1(-10);
            }
            else if(CurrentModuleRoatation == 2){
                robot.rotateModule2(-10);
            }
            else if(CurrentModuleRoatation == 3){
                robot.rotateModule3(-10);
            }
        }
    }

    public class RobotAnimationControl extends AnimationTimer {

        @Override
        public void handle(long now) {

            if(robot.module1.getChildren().get(2) instanceof Rectangle){
                robot.module1.getChildren().get(2).setRotate(robot.module1.getChildren().get(2).getRotate() + 2);
            }

        }
    }



//    private class KeyControl implements EventHandler<KeyEvent> {
//
//        @Override
//        public void handle(KeyEvent event) {
//
//            if (event.getCode() == KeyCode.SPACE) {
//                System.out.println("Controlling the robot");
//
//            }
//        }
//    }


    public void focus() {
        robotElements.requestFocus();
    }



}
