package robotModules;



public class Test_coords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Robot robot = new Robot();
		System.out.println(robot.getObjects().get(0));
		
		rotateModule(1,robot,1);
		for(Object obj : robot.getObjects()) {
			System.out.println(((robotModules.Objects) obj).getX());
		}
		for(Object obj : robot.getObjects()) {
			System.out.println(((robotModules.Objects) obj).getY());
		}
		
	}
	
	public static void rotateModule(int jointNumber, Robot robot, double radAngle) {
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
	
	private static double[] rotateLine( double radAngle, double endX, double endY, double px, double py) {
        double x, y;
        x = Math.cos(radAngle) * (endX - px) - Math.sin(radAngle) * (endY - py) + px;
        y = Math.sin(radAngle) * (endX - px) + Math.cos(radAngle) * (endY - py) + py;
        return new double[]{x, y};
    }

}
