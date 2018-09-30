package robotModules;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

//creates and stores the backend objects of the robot
public class Robot {

	robotModule_top top_1,top_2,top_3;
	robotJoint joint_1, joint_2, joint_3;
	robotModule_bottom bottom_1,bottom_2,bottom_3;

	public robotGraphics graphics;

	public Robot() {

		top_1 = new robotModule_top(230,50,100,100,50);
		joint_1 = new robotJoint(250,100);
		bottom_1 = new robotModule_bottom(230,110,100,100,50);
		
		top_2 = new robotModule_top(230,130,100,100,50);
		joint_2 = new robotJoint(250,180);
		bottom_2 = new robotModule_bottom(230,190,100,100,50);
		
		top_3 = new robotModule_top(230,210,100,100,50);
		joint_3 = new robotJoint(250,270);
		bottom_3 = new robotModule_bottom(230,280,100,100,50);
		
		top_1.setBottom(joint_1);
		top_1.setTop(null);
		joint_1.setTopModule(top_1);
		joint_1.setBottomModule(bottom_1);
		bottom_1.setTop(joint_1);
		bottom_1.setBottom(top_2);
		
		top_2.setBottom(joint_2);
		top_2.setTop(top_1);
		joint_2.setTopModule(top_2);
		joint_2.setBottomModule(bottom_2);
		bottom_2.setTop(joint_2);
		bottom_2.setBottom(null);
		
		top_3.setBottom(joint_3);
		top_3.setTop(top_2);
		joint_3.setTopModule(top_3);
		joint_3.setBottomModule(bottom_3);
		bottom_3.setTop(joint_3);
		bottom_3.setBottom(null);
		

		graphics = new robotGraphics();

		graphics.createGoal();

		graphics.createCluster(top_1, joint_1, bottom_1);
		graphics.createCluster(top_2, joint_2, bottom_2);
		graphics.createCluster(top_3, joint_3, bottom_3);

		graphics.createTree();

		System.out.println( joint_2.getX());
		System.out.println( joint_2.getY());
	}

	//TODO Move the animation to some other animation class? leaving it here for now.
	public void rotateModule1(int angle){

		double xCircle = getJoint(graphics.modules.get(0)).getCenterX();
		double yCircle = getJoint(graphics.modules.get(0)).getCenterY();

		System.out.println("1 : " + xCircle + "   " + yCircle);
		Rotate rotation = new Rotate(angle);
		rotation.setPivotX(xCircle);
		rotation.setPivotY(yCircle);

		graphics.topGroup.getTransforms().add(rotation);
		doTranslate(rotation, angle);


	}
	public void rotateModule2(int angle){

		double xCircle = getJoint(graphics.modules.get(1)).getCenterX();
		double yCircle = getJoint(graphics.modules.get(1)).getCenterY();

		System.out.println("2 : " + xCircle + "   " + yCircle);
		Rotate rotation = new Rotate(angle, xCircle, yCircle);

		//use only middlegroup
		graphics.modules.get(1).getChildren().get(2).getTransforms().add(rotation);

		graphics.bottomGroup.getTransforms().add(rotation);


		doTranslate(rotation, angle);

		//test - updating new info for backend
//		System.out.println("Old backend coordinates X:" + joint_2.getX()+ "  Y:" + joint_2.getX() );
//		joint_2.setX((int)getJoint(graphics.modules.get(1)).getCenterX());
//		joint_2.setY((int)getJoint(graphics.modules.get(1)).getCenterY());
//		System.out.println("New backend coordinates X:" + joint_2.getX()+ "  Y:" + joint_2.getX() );

	}

	public void rotateModule3(int angle){

		double xCircle = getJoint(graphics.modules.get(2)).getCenterX();
		double yCircle = getJoint(graphics.modules.get(2)).getCenterY();
		System.out.println("3 : " + xCircle + "   " + yCircle);
		Rotate rotation = new Rotate(angle, xCircle, yCircle);

		graphics.modules.get(2).getChildren().get(2).getTransforms().add(rotation);

		doTranslate(rotation,angle);

	}

	public void doTranslate(Rotate rotation, int angle){

		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
				new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), angle)));

		timeline.play();

		System.out.println("New backend coordinates X:" + joint_2.getX()+ "  Y:" + joint_2.getY() );


	}

	public Circle getJoint(Group module){

		for (int i = 0; i < 3; i++) {
			if(module.getChildren().get(i) instanceof Circle){
				return (Circle) module.getChildren().get(i);
			}
		}
		return null;
	}

	public void updateBackend(){

	}



	//OLD WAY OF CREATION

	//        goal = new Group();
//
//        Rectangle leftColumn = new Rectangle(20,20,30,300);
//        Rectangle rightColumn = new Rectangle(450,20,30,300);
//        Rectangle topBar = new Rectangle(40,20,420,30);
//
//        goal.getChildren().addAll(leftColumn,rightColumn,topBar);
//
//        //modules... for each module, there will be a java group so we could maintain them individually...
//
//        //part 1
//        module1 = new Group();
//        Circle module1_joint = new Circle(250,65,15);
//        Rectangle module1_end = new Rectangle(230,80,40,50);
//
//        module1_end.setArcHeight(15);
//        module1_end.setArcWidth(15);
//        module1_joint.setFill(Color.BLUE);
//        module1_end.setFill(Color.GREEN);
//
//        module1.getChildren().addAll(module1_joint,module1_end);
//
//        //part 2
//        module2 = new Group();
//        Rectangle module2_top = new Rectangle(230,130,40,40);
//        Circle module2_joint = new Circle(250,185,15);
//        Rectangle module2_end = new Rectangle(230,200,40,50);
//
//        module2_end.setArcHeight(15);
//        module2_end.setArcWidth(15);
//        module2_joint.setFill(Color.BLUE);
//        module2_end.setFill(Color.GREEN);
//
//        module2.getChildren().addAll(module2_top,module2_joint,module2_end);
//
//        //part 3
//        module3 = new Group();
//        Rectangle block3 = new Rectangle(230,250,40,40);
//        Circle joint3 = new Circle(250,305,15);
//        Rectangle block3_end = new Rectangle(230,320,40,40);
//
//        block3_end.setArcHeight(15);
//        block3_end.setArcWidth(15);
//        joint3.setFill(Color.BLUE);
//        block3_end.setFill(Color.GREEN);
//
//        module3.getChildren().addAll(block3,joint3,block3_end);
//
//        //hierarchical structure
//        bottomGroup = new Group(module3);
//        middleGroup = new Group(module2,bottomGroup);
//        topGroup = new Group(module1,middleGroup);

}
