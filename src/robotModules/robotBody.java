package robotModules;

import java.util.ArrayList;

public class robotBody {

	private ArrayList<robotModule> modules;

	public ArrayList<robotModule> getModules() {
		return modules;
	}

	public void createRobot(int nr_of_joints, int length_of_module, int with_of_module) {
		for(int i = 0; i<nr_of_joints; i++){
			robotModule temp = new robotModule();
			modules.add(temp);
		}
		
		for(robotModule robot : modules) {
			robotJoint upperJoint = new robotJoint();
			robotJoint lowerJoint = new robotJoint();
			robot.setUpperJoint(upperJoint);
			robot.setLowerJoint(lowerJoint);
		}
		
	}
	
}
