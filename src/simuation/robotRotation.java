package simuation;

import javafx.scene.shape.Shape;
import robotModules.Robot;

public class robotRotation {

    public robotRotation() {
    }

    public void rotateModule(int jointNumber, Robot robot, double radAngle) {
        int deviation = 0;

        if (jointNumber == 1) {
            for (int i = 2; i < robot.getObjects().size(); i++) {
                if (i == 4 || i == 7) deviation = -10;
                robot.getObjects().get(i).setX(rotateLine(radAngle, (robot.getObjects().get(i)).getX() + deviation,
                        (robot.getObjects().get(i)).getY(), robot.getObjects().get(1).getX(),
                        robot.getObjects().get(1).getY())[0]
                );
                robot.getObjects().get(i).setY(rotateLine(radAngle, ((robotModules.Objects) robot.getObjects().get(i)).getX() + deviation,
                        ((robotModules.Objects) robot.getObjects().get(i)).getY(),
                        robot.getObjects().get(1).getX(),
                        robot.getObjects().get(1).getY())[1]);
            }
        }


        if (jointNumber == 2) {
            for (int i = 5; i < robot.getObjects().size(); i++) {
                robot.getObjects().get(i).setX(rotateLine(radAngle, ((robotModules.Objects) robot.getObjects().get(i)).getX(),
                        ((robotModules.Objects) robot.getObjects().get(i)).getY(),
                        robot.getObjects().get(4).getX(),
                        robot.getObjects().get(4).getY())[0]);
                robot.getObjects().get(i).setY(rotateLine(radAngle, ((robotModules.Objects) robot.getObjects().get(i)).getX(),
                        ((robotModules.Objects) robot.getObjects().get(i)).getY(),
                        robot.getObjects().get(4).getX(),
                        robot.getObjects().get(4).getY())[1]);
            }
        }

        if (jointNumber == 3) {
            for (int i = 8; i < robot.getObjects().size(); i++) {
                robot.getObjects().get(i).setX(rotateLine(radAngle, ((robotModules.Objects) robot.getObjects().get(i)).getX(),
                        ((robotModules.Objects) robot.getObjects().get(i)).getY(),
                        robot.getObjects().get(7).getX(),
                        robot.getObjects().get(7).getY())[0]);
                robot.getObjects().get(i).setY(rotateLine(radAngle, ((robotModules.Objects) robot.getObjects().get(i)).getX(),
                        ((robotModules.Objects) robot.getObjects().get(i)).getY(),
                        robot.getObjects().get(7).getX(),
                        robot.getObjects().get(7).getY())[1]);
            }
        }

    }

    private double[] rotateLine(double radAngle, double endX, double endY, double px, double py) {
        double x, y;
        x = Math.cos(radAngle) * (endX - px) - Math.sin(radAngle) * (endY - py) + px;
        y = Math.sin(radAngle) * (endX - px) + Math.cos(radAngle) * (endY - py) + py;
        return new double[]{x, y};
    }

    private double[] rotateLine(Shape pivot, double radAngle, double endX, double endY) {
        double x, y;
        x = Math.cos(radAngle) * (endX - pivot.getTranslateX()) - Math.sin(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateX();
        y = Math.sin(radAngle) * (endX - pivot.getTranslateX()) + Math.cos(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateY();

        return new double[]{x, y};
    }

}
