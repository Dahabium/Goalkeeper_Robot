package robotModules;

import javafx.scene.shape.Rectangle;

public class robotModule_top {

	private int x;
	private int y;
	private int globalID;
    private int length;
    private int width;
    private int weight;
    private robotModule_top top;
    private robotJoint bottom;

//    private Rectangle graphics;
   
  
    public robotModule_top(int x, int y, int length, int width, int weight) {
    	this.length = length;
    	this.width = width;
    	this.weight = weight;
    	this.x = x;
    	this.y = y;
    	this.globalID = x + y;

    }



    public int getGlobalID() {
		return this.globalID;
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


	public robotModule_top getTop() {
		return top;
	}


	public void setTop(robotModule_top top) {
		this.top = top;
	}


	public robotJoint getBottom() {
		return bottom;
	}


	public void setBottom(robotJoint bottom) {
		this.bottom = bottom;
	}

	
	public String print() {
		return "Top Module " + this.globalID;
	}
	
    

}
