package robotModules;

public class robotModule_bottom implements Objects{

	private double x;
	private double y;
	private double globalID;
    private int length;
    private int width;
    private int weight;
    private robotJoint top;
    private robotModule_top bottom;
   
  
    public robotModule_bottom(double x, double y, int length, int width, int weight) {
    	this.length = length;
    	this.width = width;
    	this.weight = weight;
    	this.x = x;
    	this.y = y;
    	this.globalID = x + y;
    }


    public double getGlobalID() {
		return this.globalID;
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


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public robotJoint getTop() {
		return top;
	}


	public void setTop(robotJoint top) {
		this.top = top;
	}


	public robotModule_top getBottom() {
		return bottom;
	}


	public void setBottom(robotModule_top bottom) {
		this.bottom = bottom;
	}

	
	public String print() {
		return "Bottom module " + this.globalID;
	}
	
    

}
