package robotModules;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Simulation extends AnimationTimer {

    private robotGraphics graphics;
    private Robot robot;
    double i = 230;
  

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

    public robotGraphics getGraphics() {
		return graphics;
	}

	public void setGraphics(robotGraphics graphics) {
		this.graphics = graphics;
	}

	public Group getGoal() {
        return graphics.getGoal();
    }
    
    public Robot getRobot() {
  		return robot;
  	}

    @Override
    public void handle(long now) {
    	
        Rotations rotations = new Rotations();
        rotations.rotateModule(3, robot, 1);
    	graphics.getModules().get(0).getChildren().get(2).setTranslateX(robot.getObjects().get(2).getX());
    	graphics.getModules().get(1).getChildren().get(0).setTranslateX(robot.getObjects().get(3).getX());
    	graphics.getModules().get(1).getChildren().get(1).setTranslateX(robot.getObjects().get(4).getX());
    	graphics.getModules().get(1).getChildren().get(2).setTranslateX(robot.getObjects().get(5).getX());
    	graphics.getModules().get(2).getChildren().get(0).setTranslateX(robot.getObjects().get(6).getX());
    	graphics.getModules().get(2).getChildren().get(1).setTranslateX(robot.getObjects().get(7).getX());
    	graphics.getModules().get(2).getChildren().get(2).setTranslateX(robot.getObjects().get(8).getX());
    	
    	graphics.getModules().get(0).getChildren().get(2).setTranslateY(robot.getObjects().get(2).getY());
    	graphics.getModules().get(1).getChildren().get(0).setTranslateY(robot.getObjects().get(3).getY());
    	graphics.getModules().get(1).getChildren().get(1).setTranslateY(robot.getObjects().get(4).getY());
    	graphics.getModules().get(1).getChildren().get(2).setTranslateY(robot.getObjects().get(5).getY());
    	graphics.getModules().get(2).getChildren().get(0).setTranslateY(robot.getObjects().get(6).getY());
    	graphics.getModules().get(2).getChildren().get(1).setTranslateY(robot.getObjects().get(7).getY());
    	graphics.getModules().get(2).getChildren().get(2).setTranslateY(robot.getObjects().get(8).getY());
    	
    	
    	   
    
    
    
    }
    
    
}
