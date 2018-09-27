package robotModules;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class moduleCreator {
	
	public moduleCreator() {
	
	}
	
	private static ArrayList<Group> modules = new ArrayList<Group>();
	
	public void createGoal() {
    	
    	Group goal = new Group();

        Rectangle leftColumn = new Rectangle(20,20,30,300);
        Rectangle rightColumn = new Rectangle(450,20,30,300);
        Rectangle topBar = new Rectangle(40,20,420,30);

       goal.getChildren().addAll(leftColumn,rightColumn,topBar);
       modules.add(goal);
    }
	
	
	public void createCluster(robotModule_top top, robotJoint joint, robotModule_bottom bottom){
		Group cluster = new Group();
        
		Rectangle block_top = new Rectangle(top.getX(),top.getY(),40,40);
        Circle joint_middle = new Circle(250,joint.getY(),15);
        Rectangle block_end = new Rectangle(bottom.getX(),bottom.getY(),40,20);

        block_end.setArcHeight(15);
        block_end.setArcWidth(15);
        joint_middle.setFill(Color.BLUE);
        block_end.setFill(Color.GREEN);

        cluster.getChildren().addAll(block_top,joint_middle,block_end);
		modules.add(cluster);
	}


	public static ArrayList<Group> getModules() {
		return modules;
	}

}
