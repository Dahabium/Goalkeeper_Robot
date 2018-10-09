package robotModules;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class robotGraphics {



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
        Rectangle block_top = new Rectangle(40,40);
		Circle joint_middle = new Circle(10);
        Rectangle block_end = new Rectangle(40,40);
        

        block_top.setTranslateX(top.getX());
        block_top.setTranslateY(top.getY());

        joint_middle.setTranslateX(joint.getX());
        joint_middle.setTranslateY(joint.getY());

        System.out.println("Trnaslation of joint: " + joint_middle.getTranslateX() + "  " + joint_middle.getTranslateY());

        block_end.setTranslateX(bottom.getX());
        block_end.setTranslateY(bottom.getY());


        block_end.setArcHeight(15);
        block_end.setArcWidth(15);
        joint_middle.setFill(Color.BLUE);
        block_end.setFill(Color.GREEN);

        //cluster.getChildren().addAll(block_top,joint_middle,block_end);
        cluster.getChildren().add(block_top);
        cluster.getChildren().add(joint_middle);
		cluster.getChildren().add(block_end);
		modules.add(cluster);
	}


	public ArrayList<Group> getModules() {
		return modules;
	}

	public Group getGoal(){
	    return goal;
    }

}
