package robotModules;


public class robotBody {

	public static void test() {
		robotModule_top top_1 = new robotModule_top(230,50,100,100,50);
		robotJoint joint_1 = new robotJoint(225,100);
		robotModule_bottom bottom_1 = new robotModule_bottom(230,110,100,100,50);
		
		robotModule_top top_2 = new robotModule_top(230,130,100,100,50);
		robotJoint joint_2 = new robotJoint(225,180);
		robotModule_bottom bottom_2 = new robotModule_bottom(230,190,100,100,50);
		
		robotModule_top top_3 = new robotModule_top(230,210,100,100,50);
		robotJoint joint_3 = new robotJoint(225,270);
		robotModule_bottom bottom_3 = new robotModule_bottom(230,280,100,100,50);
		
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
		
		
		
		
		moduleCreator creator = new moduleCreator();
		creator.createGoal();
		creator.createCluster(top_1, joint_1, bottom_1);
		creator.createCluster(top_2, joint_2, bottom_2);
		creator.createCluster(top_3, joint_3, bottom_3);
		
		System.out.println("There are "+moduleCreator.getModules().size() + " modules to be rendered");
	}
	public static void main(String args[]) {
		test();
	}


	
}
