package robotModules;

public class robotJoint implements Objects{
	
	
	private double x;
	private double y;
	private double globalID;
	private int currentBentDegree;
	private int robotBentDegree;
	private int calibraionValue;
	private robotModule_top topModule;
	private robotModule_bottom bottomModule;
	
	public robotJoint(double x, double y) {
		this.x = x;
		this.y = y;
		this.globalID = x + y;
	}
	
	public double getGlobalID() {
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


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
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
