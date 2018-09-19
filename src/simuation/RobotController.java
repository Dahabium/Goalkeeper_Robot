package simuation;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class RobotController {

    @FXML
    public Group robotElements;
    @FXML
    public BorderPane RobotPane;

    Robot robot;

    RobotAnimationControl control;


    public void initialize() {
        robot = new Robot();

        robotElements.getChildren().addAll(robot.goal, robot.module1, robot.module2, robot.module3);
        control = new RobotAnimationControl();
    }

    @FXML
    public void keyPressed(KeyEvent event) throws IOException {

        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Controlling the robot");
            control.start();
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
