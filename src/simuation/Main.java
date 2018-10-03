package simuation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import robotModules.Robot;
import robotModules.Simulation;
import robotModules.robotGraphics;

public class Main extends Application {

    Scene BallScene, RobotScene;


    @FXML
    public Group screenElements = new Group();


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create the window for the ball, add the ball in controller.




//        System.out.println("Graphics " + simulation.getGoal().getChildren().size());



        Parent parent = FXMLLoader.load(getClass().getResource("../userinterface/mainView.fxml"));

        FXMLLoader ballLoader = new FXMLLoader();
        ballLoader.load(getClass().getResource("../userinterface/mainView.fxml"));
        primaryStage.setTitle("Ball");

        BallScene = new Scene(parent, 500, 500);

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
        secondryStage.requestFocus();

        KeyControl control = new KeyControl();

        //this is a javafx bug that is fixed with a walkaround... (otherwise i would call the method straigh
        //from the controller... without those lines to make it focused.
//        RobotController controller = loader.getController();
//        BallController controller1 = ballLoader.getController();



//        controller.focus();


        screenElements.requestFocus();


    }

    public void initialize() {

        Robot robot = new Robot();
        robotGraphics graphics = new robotGraphics();

        Simulation simulation = new Simulation(graphics,robot);

        Circle circle = new Circle(10,10,10);

        screenElements.getChildren().add(simulation.getGoal());


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
