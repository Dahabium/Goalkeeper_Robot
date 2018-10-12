package robotModules;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Simulation extends AnimationTimer {

    private robotGraphics graphics;
    private Robot robot;
    double i = -90;
    double k = -90;
    

	double j = -90;
    boolean rotated = false;
    double temp = -90;
    double temp2 = -120;

    Rotations rotations;

	Group goal;

	public boolean rotateall = true;
	public boolean rotatebottom = false;


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
    	
i++;
j = j + 2;    	
k = k + 0.5;
        rotations = new Rotations();
      
        
        rotateAll();
        rotateMiddle();
        rotateBottom();
        
      
//        
    }

    public void rotateBottom(){
    	rotations.rotateModule(7, robot, -j);
  
        graphics.getModules().get(2).getChildren().get(2).setTranslateX(robot.getObjects().get(8).getX());
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(robot.getObjects().get(8).getY());
        graphics.getModules().get(2).getChildren().get(2).setRotate(j);

    }
    
    public void rotateMiddle() {
       rotations.rotateModule(4, robot, -i);
    	
       graphics.getModules().get(1).getChildren().get(2).setTranslateX(robot.getObjects().get(5).getX());
       graphics.getModules().get(1).getChildren().get(2).setTranslateY(robot.getObjects().get(5).getY());
       graphics.getModules().get(2).getChildren().get(0).setTranslateX(robot.getObjects().get(6).getX());
       graphics.getModules().get(2).getChildren().get(0).setTranslateY(robot.getObjects().get(6).getY());
       graphics.getModules().get(2).getChildren().get(1).setTranslateX(robot.getObjects().get(7).getX());
       graphics.getModules().get(2).getChildren().get(1).setTranslateY(robot.getObjects().get(7).getY());
       graphics.getModules().get(2).getChildren().get(2).setTranslateX(robot.getObjects().get(8).getX());
       graphics.getModules().get(2).getChildren().get(2).setTranslateY(robot.getObjects().get(8).getY());
       graphics.getModules().get(1).getChildren().get(2).setRotate(i);
       graphics.getModules().get(2).getChildren().get(0).setRotate(i);
       graphics.getModules().get(2).getChildren().get(2).setRotate(i);
    }
    
    public void rotateAll() {
    	rotations.rotateModule(1, robot, k);

    	graphics.getModules().get(0).getChildren().get(2).setTranslateX(robot.getObjects().get(2).getX());
    	graphics.getModules().get(0).getChildren().get(2).setTranslateY(robot.getObjects().get(2).getY());
        graphics.getModules().get(1).getChildren().get(0).setTranslateX(robot.getObjects().get(3).getX());
        graphics.getModules().get(1).getChildren().get(0).setTranslateY(robot.getObjects().get(3).getY());
        graphics.getModules().get(1).getChildren().get(1).setTranslateX(robot.getObjects().get(4).getX());
        graphics.getModules().get(1).getChildren().get(1).setTranslateY(robot.getObjects().get(4).getY());
        graphics.getModules().get(1).getChildren().get(2).setTranslateX(robot.getObjects().get(5).getX());
        graphics.getModules().get(1).getChildren().get(2).setTranslateY(robot.getObjects().get(5).getY());
        graphics.getModules().get(2).getChildren().get(0).setTranslateX(robot.getObjects().get(6).getX());
        graphics.getModules().get(2).getChildren().get(0).setTranslateY(robot.getObjects().get(6).getY());
        graphics.getModules().get(2).getChildren().get(1).setTranslateX(robot.getObjects().get(7).getX());
        graphics.getModules().get(2).getChildren().get(1).setTranslateY(robot.getObjects().get(7).getY());
        graphics.getModules().get(2).getChildren().get(2).setTranslateX(robot.getObjects().get(8).getX());
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(robot.getObjects().get(8).getY());
        graphics.getModules().get(0).getChildren().get(2).setRotate(-k);
    	graphics.getModules().get(1).getChildren().get(0).setRotate(-k);
    	graphics.getModules().get(1).getChildren().get(2).setRotate(-k);
    	graphics.getModules().get(2).getChildren().get(0).setRotate(-k);
    	graphics.getModules().get(2).getChildren().get(2).setRotate(-k);
    }
    
}
