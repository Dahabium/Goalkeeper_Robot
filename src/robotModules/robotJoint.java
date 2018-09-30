package robotModules;

public class robotJoint {
	
	
	private int x;
	private int y;
	private int globalID;
	private int currentBentDegree;
	private int robotBentDegree;
	private int calibraionValue;
	private robotModule_top topModule;
	private robotModule_bottom bottomModule;
	
	public robotJoint(int x, int y) {
		this.x = x;
		this.y = y;
		this.globalID = x + y;
	}
	
	public int getGlobalID() {
		return this.globalID;
	}
	
	public robotModule_top getTopModule() {
		return topModule;
	}


	public void setTopModule(robotModule_top topModule) {
		this.topModule = topModule;
	}


	public robotModule_bottom getBottomModule() {
		return bottomModule;
	}


	public void setBottomModule(robotModule_bottom bottomModule) {
		this.bottomModule = bottomModule;
	}


	public robotJoint(int x, int y, int currentBentDegree) {
		this.x = x;
		this.y = y;
		this.currentBentDegree = currentBentDegree;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getCurrentBentDegree() {
		return currentBentDegree;
	}


	public void setCurrentBentDegree(int currentBentDegree) {
		this.currentBentDegree = currentBentDegree;
	}


	public int getRobotBentDegree() {
		return robotBentDegree;
	}


	public void setRobotBentDegree(int robotBentDegree) {
		this.robotBentDegree = robotBentDegree;
	}


	public int getCalibraionValue() {
		return calibraionValue;
	}


	public void setCalibraionValue(int calibraionValue) {
		this.calibraionValue = calibraionValue;
	}
	
	
	public String print() {
		return "Joint " + this.globalID;
	}
	
	
}
