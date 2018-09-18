package robotModules;

public class robotJoint {
	
	private robotModule input;
	private robotModule output;
	private int rotationAngle;
	
	public robotJoint() {
		
	}
	
	public robotJoint(robotModule input, robotModule output, int rotationAngle) {
		this.input = input;
		this.output = output;
		this.rotationAngle = rotationAngle;
	}
	
	public robotModule getInput() {
		return input;
	}
	public void setInput(robotModule input) {
		this.input = input;
	}
	public robotModule getOutput() {
		return output;
	}
	public void setOutput(robotModule output) {
		this.output = output;
	}
	public int getRotationAngle() {
		return rotationAngle;
	}
	public void setRotationAngle(int rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	
}
