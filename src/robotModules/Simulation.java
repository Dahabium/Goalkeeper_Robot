package robotModules;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Simulation extends AnimationTimer {

    private robotGraphics graphics;
    private Robot robot;
    private double i;
    
    public void setI(double i) {
		this.i = i;
	}

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
    	
    	
        rotations = new Rotations();
     
        rotateMiddle();
        rotateBottom();
        //rotateAll();
        
    }

    public void rotateBottom(){
    	 rotations.rotateModule(7, robot, i);
        long width = 20; 
        long height = 20;

        double pivX = graphics.getModules().get(2).getChildren().get(1).getTranslateX();
        double pivY = graphics.getModules().get(2).getChildren().get(1).getTranslateY();

        graphics.getModules().get(2).getChildren().get(2).setTranslateX(rotations.coords(j,pivX-width, pivY-height, 30)[0]);
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(rotations.coords(j,pivX-width, pivY-height, 30)[1]);

        graphics.getModules().get(2).getChildren().get(2).setRotate(-j);

    }
    
    public void rotateMiddle() {
        rotations.rotateModule(4, robot, i);
    	long width = 20;
    	long height = 20;

    	double pivX = graphics.getModules().get(1).getChildren().get(1).getTranslateX();
    	double pivY = graphics.getModules().get(1).getChildren().get(1).getTranslateY();
    	
    	
       graphics.getModules().get(1).getChildren().get(2).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30)[0]);
       graphics.getModules().get(1).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30)[1]);
       graphics.getModules().get(2).getChildren().get(0).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 50)[0]);
       graphics.getModules().get(2).getChildren().get(0).setTranslateY(rotations.coords(i,pivX-width, pivY-height,  50)[1]);
       graphics.getModules().get(2).getChildren().get(1).setTranslateX(rotations.coords(i,pivX, pivY, 80)[0]);
       graphics.getModules().get(2).getChildren().get(1).setTranslateY(rotations.coords(i,pivX, pivY, 80)[1]);
       graphics.getModules().get(2).getChildren().get(2).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 110)[0]);
       graphics.getModules().get(2).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 110)[1]);
       graphics.getModules().get(1).getChildren().get(2).setRotate(-i);
       graphics.getModules().get(2).getChildren().get(0).setRotate(-i);
       graphics.getModules().get(2).getChildren().get(2).setRotate(-i);
    }

    public void rotateAll() {
    	rotations.rotateModule(1, robot, i);
    	long width = 20;
    	long height = 20;

    	double pivX = graphics.getModules().get(0).getChildren().get(1).getTranslateX();
    	double pivY = graphics.getModules().get(0).getChildren().get(1).getTranslateY();

    	graphics.getModules().get(0).getChildren().get(2).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30)[0]);
    	graphics.getModules().get(0).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30)[1]);
        graphics.getModules().get(1).getChildren().get(0).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30+height)[0]);
        graphics.getModules().get(1).getChildren().get(0).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+height)[1]);
        graphics.getModules().get(1).getChildren().get(1).setTranslateX(rotations.coords(i, pivX, pivY, 40+40)[0]);
        graphics.getModules().get(1).getChildren().get(1).setTranslateY(rotations.coords(i,pivX, pivY, 40+40)[1]);
        graphics.getModules().get(1).getChildren().get(2).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30+ 80)[0]);
        graphics.getModules().get(1).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 80)[1]);
        graphics.getModules().get(2).getChildren().get(0).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 30+ 110)[0]);
        graphics.getModules().get(2).getChildren().get(0).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 110)[1]);
        graphics.getModules().get(2).getChildren().get(1).setTranslateX(rotations.coords(i,pivX, pivY, 30+140)[0]);
        graphics.getModules().get(2).getChildren().get(1).setTranslateY(rotations.coords(i,pivX, pivY, 30+140)[1]);
        graphics.getModules().get(2).getChildren().get(2).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 30+ 170)[0]);
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 170)[1]);
        graphics.getModules().get(0).getChildren().get(2).setRotate(-i);
    	graphics.getModules().get(1).getChildren().get(0).setRotate(-i);
    	graphics.getModules().get(1).getChildren().get(2).setRotate(-i);
    	graphics.getModules().get(2).getChildren().get(0).setRotate(-i);
    	graphics.getModules().get(2).getChildren().get(2).setRotate(-i);
 }





    
    
    
    
}
