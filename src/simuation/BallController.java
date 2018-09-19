package simuation;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.io.IOException;
public class BallController {

    Ball ball;


    @FXML
    public Pane ballPane;


    public Group ballScreenGroup;


    public void initialize() {

        ball = new Ball();

        ballScreenGroup.getChildren().add(ball.ball);
        ballScreenGroup.getChildren().add(ball.goal);

    }

    public void launchBallButton() throws IOException {

        System.out.println("Launching a ball!");
        ball.simulate();
    }


}
