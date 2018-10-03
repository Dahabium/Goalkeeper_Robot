package robotModules;


import java.util.ArrayList;



//creates and stores the back-end objects of the robot
public class Robot {

	robotModule_top top_1,top_2,top_3;
	robotJoint joint_1, joint_2, joint_3;
	robotModule_bottom bottom_1,bottom_2,bottom_3;

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


	}

}
