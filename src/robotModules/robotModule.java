package robotModules;

public class robotModule {

    private int length;
    private int width;
    private robotJoint upperJoint;
    private robotJoint lowerJoint;

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

    public robotJoint getUpperJoint() {
        return upperJoint;
    }

    public void setUpperJoint(robotJoint upperJoint) {
        this.upperJoint = upperJoint;
    }

    public robotJoint getLowerJoint() {
        return lowerJoint;
    }

    public void setLowerJoint(robotJoint lowerJoint) {
        this.lowerJoint = lowerJoint;
    }


}
