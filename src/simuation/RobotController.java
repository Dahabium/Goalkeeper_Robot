package simuation;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import robotModules.Robot;


import java.io.IOException;

public class RobotController {

    @FXML
    public Group screenElements;
    @FXML
    public BorderPane RobotPane;

    Robot robot;

    int CurrentModuleRoatation = 0;


    public void initialize() {

        //create robot backend, which also creates graphics
        robot = new Robot();

        //add the goal to the screen
        screenElements.getChildren().add(robot.graphics.getGoal());

        //add the robot to the screen
        screenElements.getChildren().addAll(robot.graphics.getRobot());

//        control = new RobotAnimationControl();
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

            System.out.println("Moving left");
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
            System.out.println("Moving right");

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

    public void focus() {
        screenElements.requestFocus();
    }

}
