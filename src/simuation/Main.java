package simuation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    Scene BallScene, RobotScene;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create the window for the ball, add the ball in controller.

        Parent parent = FXMLLoader.load(getClass().getResource("../userinterface/mainView.fxml"));

        primaryStage.setTitle("Ball");

        BallScene = new Scene(parent, 500, 500);

        //just to check if keyboard input is working...

//        KeyControl keyControl = new KeyControl();
//        BallScene.setOnKeyPressed(keyControl);

        primaryStage.setScene(BallScene);
        primaryStage.setX(300);
        primaryStage.setY(200);
        primaryStage.show();


        //create window for the robot, add robot parts through controller.
        Stage secondryStage = new Stage();

        secondryStage.setTitle("robot");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../userinterface/RobotView.fxml"));
        Parent parent2 = loader.load();

        RobotScene = new Scene(parent2, 500, 500);

        secondryStage.setScene(RobotScene);
        secondryStage.show();

        //this is a javafx bug that is fixed with a walkaround... (otherwise i would call the method straigh
        //from the controller... without those lines to make it focused.
        RobotController controller = loader.<RobotController>getController();

        controller.focus();


    }

    private class KeyControl implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.SPACE) {
                System.out.println("SPACE IS PRESSED!");

            }
        }
    }


    public static void main(String[] args) {
    	launch(args);
    }
}
