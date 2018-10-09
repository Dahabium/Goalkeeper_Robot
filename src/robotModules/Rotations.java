package robotModules;



public class Rotations {

	
	public double[] coords(double angleGrad, double a, double b, double r) {
		
		double x =  a + r * Math.cos(Math.toRadians(angleGrad));
		double y =  b + r * Math.sin(Math.toRadians(angleGrad+180));
		return new double[]{x, y};
	}
	
	public void rotateModule(int jointNumber, Robot robot, double degAngle) {
		
		double[] radius = new double[8];
		
		
		radius[1] = 10;
		radius[2] = 30;
		radius[3] = 80;
		radius[4] = 90;
		radius[5] = 110;
		radius[6] = 160;
		radius[7] = 170;
	
		if(jointNumber == 1) {
			for(int i = 2; i<robot.getObjects().size();i++) {
			robot.getObjects().get(i).setX(coords(degAngle,robot.getObjects().get(1).getX(),robot.getObjects().get(1).getY(), 
					radius[i-1])[0]);
			robot.getObjects().get(i).setY(coords(degAngle,robot.getObjects().get(1).getX(),robot.getObjects().get(1).getY(), 
					radius[i-1])[1]);
			}
		}
		
		
		
	}
	

}
