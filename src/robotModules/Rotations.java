package robotModules;



public class Rotations {

	
	public double[] coords(double angleGrad, double a, double b, double r) {
		
		double x =  a + r * Math.cos(Math.toRadians(angleGrad));
		double y =  b + r * Math.sin(Math.toRadians(angleGrad+180));
		return new double[]{x, y};
	}
	
	public void rotateModule(int jointNumber, Robot robot, double degAngle) {
		
		if(jointNumber == 1) {
		double[] radius = new double[8];
		int deviation = 20;
		
		radius[1] = 30;
		radius[2] = 50;
		radius[3] = 80;
		radius[4] = 110;
		radius[5] = 140;
		radius[6] = 170;
		radius[7] = 200;
	
		
			for(int i = 2; i<robot.getObjects().size();i++) {
			
			if(i==4 || i==7) deviation = 0;	
			else deviation = 20;
			robot.getObjects().get(i).setX(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
					radius[i-1])[0]);
			robot.getObjects().get(i).setY(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
					radius[i-1])[1]);
			}
		}
		
		if(jointNumber == 4) {
			double[] radius = new double[8];
			int deviation = 20;
			
			radius[4] = 30;  
			radius[5] = 60; 
			radius[6] = 90; 
			radius[7] = 120; 
			
			for(int i = 5; i<robot.getObjects().size();i++) {
				
				if(i==7) deviation = 0;	
				else deviation = 20;
				robot.getObjects().get(i).setX(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
						radius[i-1])[0]);
				robot.getObjects().get(i).setY(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
						radius[i-1])[1]);
				}
		}

		
		if(jointNumber == 7) {
			double radius = 30;
			int deviation = 20;
			
			robot.getObjects().get(8).setX(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
					radius)[0]);
			robot.getObjects().get(8).setY(coords(degAngle,robot.getObjects().get(jointNumber).getX()-deviation,robot.getObjects().get(jointNumber).getY()-deviation, 
					radius)[1]);
			
		}
		
		
	}
	

}
