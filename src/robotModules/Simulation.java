package robotModules;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Simulation extends AnimationTimer {

    private robotGraphics graphics;
    private Robot robot;
    double i = 0;
    double j = -90;

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

      //  graphics.createCluster(robot.top_3, robot.joint_3, robot.bottom_3);
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

//        if (i )
        if(i > temp){
            i = i - 1;
        }
        else {

            rotatebottom = true;
            if(j > temp2) j = j-1;
            else {
                temp = -180;
                temp2 = - 200;
            }

        }

    	//System.out.println(i+ " "+j);
   // System.out.println(	graphics.getModules().get(0).getChildren().get(0).getId());
        rotations = new Rotations();
      //  rotations.rotateModule(3, robot, 1);
//    	graphics.getModules().get(0).getChildren().get(2).setTranslateX(robot.getObjects().get(2).getX());
//    	graphics.getModules().get(1).getChildren().get(0).setTranslateX(robot.getObjects().get(3).getX());
//    	graphics.getModules().get(1).getChildren().get(1).setTranslateX(robot.getObjects().get(4).getX());
//    	graphics.getModules().get(1).getChildren().get(2).setTranslateX(robot.getObjects().get(5).getX());
//    	graphics.getModules().get(2).getChildren().get(0).setTranslateX(robot.getObjects().get(6).getX());
//    	graphics.getModules().get(2).getChildren().get(1).setTranslateX(robot.getObjects().get(7).getX());
  	
  	//graphics.getModules().get(0).getChildren().get(1).setRotate(i/30);

        if(rotateall) rotateAll();

        if (rotatebottom) rotateBottom();
//    
//    	graphics.getModules().get(0).getChildren().get(2).setTranslateY(robot.getObjects().get(2).getY());
//    	graphics.getModules().get(1).getChildren().get(0).setTranslateY(robot.getObjects().get(3).getY());
//    	graphics.getModules().get(1).getChildren().get(1).setTranslateY(robot.getObjects().get(4).getY());
//    	graphics.getModules().get(1).getChildren().get(2).setTranslateY(robot.getObjects().get(5).getY());
//    	graphics.getModules().get(2).getChildren().get(0).setTranslateY(robot.getObjects().get(6).getY());
//    	graphics.getModules().get(2).getChildren().get(1).setTranslateY(robot.getObjects().get(7).getY());

       
//
    
    }

    public void rotateBottom(){

        long width = 40/2; ///graphics.getModules().get(0).getChildren().get(1).get
        long height = 40/2;

        double pivX = graphics.getModules().get(2).getChildren().get(1).getTranslateX();
        double pivY = graphics.getModules().get(2).getChildren().get(1).getTranslateY();

        graphics.getModules().get(2).getChildren().get(2).setTranslateX(rotations.coords(j,pivX-width, pivY-height, 30)[0]);
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(rotations.coords(j,pivX-width, pivY-height, 30)[1]);

        graphics.getModules().get(2).getChildren().get(2).setRotate(-j);

    }

    public void rotateAll() {
    	
    	long width = 40/2; ///graphics.getModules().get(0).getChildren().get(1).get
    	long height = 40/2;

    	double pivX = graphics.getModules().get(0).getChildren().get(1).getTranslateX();
    	double pivY = graphics.getModules().get(0).getChildren().get(1).getTranslateY();

        System.out.println("PivX "+ pivX + "   PivY " + pivY);

    	graphics.getModules().get(0).getChildren().get(2).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30)[0]);
    	graphics.getModules().get(0).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30)[1]);

    	//for r we need to calculate the distance between the pivot and other object using Euclidean distance.
        graphics.getModules().get(1).getChildren().get(0).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30+height)[0]);
        graphics.getModules().get(1).getChildren().get(0).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+height)[1]);


        //translate for second joint (dont rotate - just translate)
        graphics.getModules().get(1).getChildren().get(1).setTranslateX(rotations.coords(i, pivX, pivY, 30+40)[0]);
        graphics.getModules().get(1).getChildren().get(1).setTranslateY(rotations.coords(i,pivX, pivY, 30+40)[1]);

        graphics.getModules().get(1).getChildren().get(2).setTranslateX(rotations.coords(i, pivX-width, pivY-height, 30+ 70)[0]);
        graphics.getModules().get(1).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 70)[1]);

        graphics.getModules().get(2).getChildren().get(0).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 30+ 100)[0]);
        graphics.getModules().get(2).getChildren().get(0).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 100)[1]);


        graphics.getModules().get(2).getChildren().get(1).setTranslateX(rotations.coords(i,pivX, pivY, 30+ 130)[0]);
        graphics.getModules().get(2).getChildren().get(1).setTranslateY(rotations.coords(i,pivX, pivY, 30+ 130)[1]);

        graphics.getModules().get(2).getChildren().get(2).setTranslateX(rotations.coords(i,pivX-width, pivY-height, 30+ 150)[0]);
        graphics.getModules().get(2).getChildren().get(2).setTranslateY(rotations.coords(i,pivX-width, pivY-height, 30+ 150)[1]);


        graphics.getModules().get(0).getChildren().get(2).setRotate(-i);

    	graphics.getModules().get(1).getChildren().get(0).setRotate(-i);
    	graphics.getModules().get(1).getChildren().get(2).setRotate(-i);

    	graphics.getModules().get(2).getChildren().get(0).setRotate(-i);
    	graphics.getModules().get(2).getChildren().get(2).setRotate(-i);


    }
    
}
