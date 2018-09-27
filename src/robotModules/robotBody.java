package robotModules;

import java.util.ArrayList;

public class robotBody {

	public static void main(String args[]) {
		robotModule_top top_1 = new robotModule_top(250,300,100,100,50);
		robotJoint joint_1 = new robotJoint(225,267);
		robotModule_bottom bottom_1 = new robotModule_bottom(250,233,100,100,50);
		top_1.setBottom(joint_1);
		top_1.setTop(null);
		joint_1.setTopModule(top_1);
		joint_1.setBottomModule(bottom_1);
		bottom_1.setTop(joint_1);
		bottom_1.setBottom(null);
		System.out.println(top_1.print());
		System.out.println(joint_1.print());
		System.out.println(bottom_1.print());
		
		
	}


	
}
