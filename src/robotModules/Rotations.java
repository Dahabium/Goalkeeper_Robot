package robotModules;



public class Rotations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//Robot robot = new Robot();
		
		for(double i=0;i<360;) {
			//System.out.println(rotateLine(30,250,280,250,140)[0]);
			System.out.println(coords(i,250,140,140)[1]);
			
		i = i + 0.1;
		}
		
//		for(double i=0;i<30;) {
//			System.out.println(rotateLine(i,250,280,250,140)[1]);
//			//System.out.println(rotateLine(i,250,280,250,140)[1]);
//			
//			i = i + 0.1;
//		}
		
		
		
		//System.out.println(rotateLine(1,250,280,250,140)[0]+" "+rotateLine(1,250,280,250,140)[1]);
		
	}
	
	public static double[] coords(double angleGrad, double a, double b, double r) {
		
		double x =  a + r * Math.cos(Math.toRadians(angleGrad));
		double y =  b + r * Math.sin(Math.toRadians(angleGrad+180));
		return new double[]{x, y};
	}
	
	public void rotateModule(int jointNumber, Robot robot, double radAngle) {
		int deviation = 0;
		
		
		if(jointNumber == 1) {
			for(int i = 2; i<robot.getObjects().size();i++) {
				if(i == 4 || i == 7) deviation = - 10;
			robot.getObjects().get(i).setX(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX() + deviation, 
					  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),robot.getObjects().get(1).getX(),
						robot.getObjects().get(1).getY())[0]
			);
			robot.getObjects().get(i).setY(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX() + deviation, 
								  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),
								  robot.getObjects().get(1).getX(),
									robot.getObjects().get(1).getY())[1]);
			}
		}
		
		
		if(jointNumber == 2) {
			for(int i = 5; i<robot.getObjects().size();i++) {
			robot.getObjects().get(i).setX(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX(), 
					  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),
					  robot.getObjects().get(4).getX(),
						robot.getObjects().get(4).getY())[0]);
			robot.getObjects().get(i).setY(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX(), 
								  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),
								  robot.getObjects().get(4).getX(),
									robot.getObjects().get(4).getY())[1]);
			}
		}
		
		if(jointNumber == 3) {
			for(int i = 8; i<robot.getObjects().size();i++) {
			robot.getObjects().get(i).setX(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX(), 
					  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),
					  robot.getObjects().get(7).getX(),
						robot.getObjects().get(7).getY())[0]);
			robot.getObjects().get(i).setY(rotateLine(radAngle,((robotModules.Objects)  robot.getObjects().get(i)).getX(), 
								  ((robotModules.Objects)  robot.getObjects().get(i)).getY(),
								  robot.getObjects().get(7).getX(),
									robot.getObjects().get(7).getY())[1]);
			}
		}
		
	}
	
	private static double[] rotateLine( double degAngle, double endX, double endY, double px, double py) {
        double x, y;
       double radAngle = Math.toRadians(degAngle);
        x = Math.cos(radAngle) * (endX - px) - Math.sin(radAngle) * (endY - py) + px;
        y = Math.sin(radAngle) * (endX - px) + Math.cos(radAngle) * (endY - py) + py;
        return new double[]{x, y};
    }

}
