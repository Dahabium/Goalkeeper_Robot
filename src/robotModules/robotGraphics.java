package robotModules;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class robotGraphics {

//    Group topGroup,middleGroup, bottomGroup;

    Group goal;

    ArrayList<Group> modules = new ArrayList<>();

	public void createGoal() {
    	goal = new Group();

        Rectangle leftColumn = new Rectangle(20,20,30,300);
        Rectangle rightColumn = new Rectangle(450,20,30,300);
        Rectangle topBar = new Rectangle(40,20,420,30);

       goal.getChildren().addAll(leftColumn,rightColumn,topBar);
    }
	
	
	public void createCluster(robotModule_top top, robotJoint joint, robotModule_bottom bottom){
		Group cluster = new Group();
        
		Rectangle block_top = new Rectangle(top.getX(),top.getY(),40,40);
//		block_top.setTranslateX(top.getX());
//		block_top.setTranslateY(top.getY());
        Circle joint_middle = new Circle(joint.getX(),joint.getY(),10);
        Rectangle block_end = new Rectangle(bottom.getX(),bottom.getY(),40,50);

        block_end.setArcHeight(15);
        block_end.setArcWidth(15);
        joint_middle.setFill(Color.BLUE);
        block_end.setFill(Color.GREEN);

        cluster.getChildren().addAll(block_top,joint_middle,block_end);
		modules.add(cluster);
	}

//	public void createTree(){
//
//        bottomGroup = new Group(modules.get(2));
//        middleGroup = new Group(modules.get(1),bottomGroup);
//        topGroup = new Group(modules.get(0),middleGroup);
//
//    }

	public ArrayList<Group> getModules() {
		return modules;
	}

	public Group getGoal(){
	    return goal;
    }

    //PS: The robot is a hierarchical structure, so Im adding the topmost
    //module, which contains everything below it.
//    public Group getRobot(){
//	    return topGroup;
//    }

}
