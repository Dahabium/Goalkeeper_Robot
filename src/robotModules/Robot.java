package robotModules;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import simuation.RobotController;

//creates and stores the backend objects of the robot
public class Robot {

	robotModule_top top_1,top_2,top_3;
	robotJoint joint_1, joint_2, joint_3;
	robotModule_bottom bottom_1,bottom_2,bottom_3;

	public robotGraphics graphics;
	public RobotAnimationControl robotAnimation;
	private ArrayList<robotModules.Objects> Objects = new ArrayList<>();
	
	public ArrayList<robotModules.Objects> getObjects() {
		return Objects;
	}

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
		
		Objects.add(top_1);
		Objects.add(joint_1);
		Objects.add(bottom_1);
		Objects.add(top_2);
		Objects.add(joint_2);
		Objects.add(bottom_2);
		Objects.add(top_3);
		Objects.add(joint_3);
		Objects.add(bottom_3);
		
		
		
		
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

//		graphics.createTree();

		robotAnimation = new RobotAnimationControl();

		robotAnimation.start();

	}

	//TODO Move the animation to some other animation class? leaving it here for now.
	public void rotateModule1(int angle){

		double xCircle = getJoint(graphics.modules.get(0)).getCenterX();
		double yCircle = getJoint(graphics.modules.get(0)).getCenterY();

		System.out.println("1 : " + xCircle + "   " + yCircle);

		Rotate rotation = new Rotate(angle);
		rotation.setPivotX(xCircle);
		rotation.setPivotY(yCircle);


//		graphics.topGroup.getTransforms().add(rotation);
		doTranslate(rotation, angle);

	}


	public void rotateModule2(int angle){

		double xCircle = getJoint(graphics.modules.get(1)).getCenterX();
		double yCircle = getJoint(graphics.modules.get(1)).getCenterY();

		System.out.println("2 : " + xCircle + "   " + yCircle);
		Rotate rotation = new Rotate(angle, xCircle, yCircle);

		//use only middlegroup
		graphics.modules.get(1).getChildren().get(2).getTransforms().add(rotation);

//		graphics.bottomGroup.getTransforms().add(rotation);


		doTranslate(rotation, angle);
		
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

		System.out.println("PLAYING ANIMATION");

		timeline.play();


	}

	public Circle getJoint(Group module){

		for (int i = 0; i < 3; i++) {
			if(module.getChildren().get(i) instanceof Circle){
				return (Circle) module.getChildren().get(i);
			}
		}
		return null;
	}



	public class RobotAnimationControl extends AnimationTimer {

		@Override
		public void handle(long now) {

			//runs 30 times a second

//			RobotController.robot

			graphics.modules.get(0).getChildren().get(2).setTranslateX(getObjects().get(2).getY());

			System.out.println("Value : " + getObjects().get(2).getX() );
		}
	}



}
