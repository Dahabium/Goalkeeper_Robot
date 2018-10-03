package robotModules;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Simulation extends AnimationTimer {

    private robotGraphics graphics;
    private Robot robot;

    Group goal;


    public Simulation(robotGraphics graphics, Robot robot){

        this.graphics = graphics;
        this.robot = robot;

        graphics.createGoal();

        graphics.createCluster(robot.top_1, robot.joint_1, robot.bottom_1);
        graphics.createCluster(robot.top_2, robot.joint_2, robot.bottom_2);
        graphics.createCluster(robot.top_3, robot.joint_3, robot.bottom_3);


        start();
    }


    public Group getGoal() {
        return graphics.getGoal();
    }

    @Override
    public void handle(long now) {

    }
}
