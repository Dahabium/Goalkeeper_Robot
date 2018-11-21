#include <stdio.h>
#include <math.h>
#include <stdlib.h>


float lengths[] = {10.5, 10.5, 5.5};

float tolerance = 1;

//Creating a struct as if its an object that holds x and y position
struct Joint {
    float x;
    float y;
    float angle;
};

struct Tuple{
    float x;
    float y;
};

//like a list of joints using a struct....
struct Joint modules[4];

float distance(float x1, float y1, float x2, float y2){
    return sqrtf(((x1-x2)*(x1-x2) +(y1-y2)*(y1-y2)));
}

//returns the angle in degrees between 2 vectors that share the same start point
float getAngle(float endX1,float endY1, float endX2, float endY2){

    float uvDash = (endX1 * endX2) + (endY1*endY2);

    float aAbs = sqrtf((endX1*endX1) + (endY1*endY1));

    float bAbs = sqrtf((endX2*endX2) + (endY2*endY2));

    float cosAns = uvDash / (aAbs*bAbs);

    return acos(cosAns) * (180.0 / 3.1415);

}

//input: 1) current joint positions(3 dof), 2) target position that we want to reach 3) lengths of each module
//output: new joint positions

void solve_fabrik(float xTarget, float yTarget) {
    //don't move anywhere if the ball is flying above the goal, right to the goal or left of the goal...
    if( yTarget > 0 || xTarget > 22 || xTarget < -22){
        return;
    }

    //check if the target is possible to reach:
    float distFromRoot = distance(0,0,xTarget,yTarget);
    //target is unreachable:
    if(distFromRoot >= lengths[0] + lengths[1] + lengths[2]){

        for (int i = 0; i < 3; i++){
            //find the distance between each joint and target position
            float distFromJoint = distance(modules[i].x, modules[i].y, xTarget,yTarget);
            float lambda = lengths[i] / distFromJoint;

            //find the new joint position:
            modules[i+1].x = (1-lambda)* modules[i].x + lambda*xTarget;
            modules[i+1].y = (1-lambda)* modules[i].y + lambda*yTarget;
        }

        return;
    }

    else{
        //the target is possible to reach.
        float b_x = modules[0].x;
        float b_y = modules[0].y;

        //Check whether the distance between the end effector
        //pn and the target t is greater than a tolerance
        float difEE = distance(modules[3].x,modules[3].y,xTarget,yTarget);

        while (difEE > tolerance){
            printf("Difference between EE and Target : %f \n", difEE);

            //Part 1: Forward reaching: Set the EE position as target
            modules[3].x = xTarget;
            modules[3].y = yTarget;

            for (int i = 2; i >= 0; i--) {
                //Find the distance Ri between the new joint position (i+1) and other joint i
                float distOtherJoint = distance(modules[i].x, modules[i].y, modules[i+1].x, modules[i+1].y);
                float lambda = lengths[i] / distOtherJoint;


                //find the new positions for i'th joint:
                modules[i].x = (1-lambda)*modules[i+1].x + lambda*modules[i].x;
                modules[i].y = (1-lambda)*modules[i+1].y + lambda*modules[i].y;

            }

            //Part 2: Backward reaching: Set the root joint ar its initial position
            modules[0].x = b_x;
            modules[0].y = b_y;

            for (int j = 0; j <= 2; j++) {
                // Find the distance Ri between the new joint position pi and the next joint position
                float lambda = lengths[j] / distance(modules[j].x,modules[j].y,modules[j+1].x,modules[j+1].y);

                // Find the new joint positions Pi
                modules[j+1].x = (1-lambda)*modules[j].x + lambda*modules[j+1].x;
                modules[j+1].y = (1-lambda)*modules[j].y + lambda*modules[j+1].y;

            }

            //update the distance between the target and last module
            difEE = distance(modules[3].x,modules[3].y,xTarget,yTarget);

        }

    }

    printf("Done with iterations. Coordinates of points: \n");
    for (int i = 0; i <= 3; ++i) {
        printf("P %i ->  X: %f , Y: %f \n", i, modules[i].x, modules[i].y );
    }

    //=======Setting angles properly=======

    //modules[0].angle = (float) atan2(modules[1].y, modules[1].x) ;

    float previous_angle;

    if(modules[1].x < 0){
        modules[0].angle = -1 * getAngle(modules[1].x,modules[1].y, 0, -10.5);
        previous_angle = modules[0].angle;
    }
    else{
        modules[0].angle = getAngle(modules[1].x,modules[1].y, 0, -10.5);
        previous_angle = modules[0].angle;
    }


    if(modules[2].x < 0 ){
        modules[1].angle = -1 * getAngle(modules[2].x - modules[1].x, modules[2].y-modules[1].y, 0, -10.5) - previous_angle;
        previous_angle = modules[1].angle;
    }
    else{
        modules[1].angle = getAngle(modules[2].x - modules[1].x, modules[2].y-modules[1].y, 0, -10.5) - previous_angle;
        previous_angle = modules[1].angle;
    }


    if(modules[3].x < 0){
        modules[2].angle = -1 * getAngle(modules[3].x - modules[2].x, modules[3].y-modules[2].y, 0, -10.5) - previous_angle;
    }
    else{
        modules[2].angle = getAngle(modules[3].x - modules[2].x, modules[3].y-modules[2].y, 0, -10.5)- previous_angle;
    }


    //======done======
}

//return in which zone are the given coordinates:
//zone 1: if the coordinates are in circle of
int getZone(float x, float y){

    float distFromZero = sqrtf(x*x + y*y);

    if(distFromZero <= lengths[0]){
        return 1;
    }
    else if(distFromZero <= lengths[0]+lengths[1]){
        return 2;
    }
    else return 3;
}
//returns a new tuple of coordinates extended to the farmost radius.
struct Tuple getVectorPosition(float x, float y){

    float distFromZero = sqrtf(x*x + y*y);

    //zone 1
    if(distFromZero <= lengths[0]){
        float extension = fabs(lengths[0] - distFromZero);
        extension = lengths[0] / (lengths[0] - extension);
        struct Tuple out = { x* extension,y*extension};
        return out;
    }
    //zone 2
    else if(distFromZero <= lengths[0]+lengths[1]){
        float extension = fabs((lengths[0]+lengths[1]) - distFromZero);
        extension = lengths[0] / (lengths[0] - extension);
        struct Tuple out = { x* extension,y*extension};
        return out;
    }
    //zone 3
    else{
        float extension = fabs((lengths[0]+lengths[1]+lengths[2]) - distFromZero);
        extension = lengths[0] / (lengths[0] - extension);
        struct Tuple out = { x* extension,y*extension};
        return out;
    }

    //struct Tuple invalid = {100,100};
}

void setdegreesInZones(float x, float y){

    if( getZone(x,y) == 1){

        struct Tuple temp = getVectorPosition(x,y);

        modules[1].x = temp.x;
        modules[1].y = temp.y;

        if(modules[1].x < 0){
            modules[0].angle = -1 * getAngle(modules[1].x,modules[1].y, 0, -10.5);
            modules[1].angle = -1 * modules[0].angle;
        }
        else{
            modules[0].angle = getAngle(modules[1].x,modules[1].y, 0, -10.5);
            modules[1].angle = -1 * modules[0].angle;
        }

    }
    else if(getZone(x,y) == 2){

        struct Tuple temp = getVectorPosition(x,y);

        modules[2].x = temp.x;
        modules[2].y = temp.y;

        if(modules[2].x < 0){
            modules[0].angle = -1 * getAngle(modules[2].x,modules[2].y, 0, -10.5);
            modules[2].angle = -1 * modules[0].angle;
        }
        else{
            modules[0].angle = getAngle(modules[2].x,modules[2].y, 0, -10.5);
            modules[2].angle = -1 * modules[0].angle;
        }
    }

    else{
        solve_fabrik(x,y);
    }

    //todo update the backend modules positions via Foreward Kinematics

    return;

    //else if (getZone(x,y) == 2){

    //}
}

int main() {

    modules[0].x = 0;
    modules[0].y = 0;

    modules[1].x = 0;
    modules[1].y =  -10.5;

    modules[2].x = 0;
    modules[2].y = -21;

    modules[3].x = 0;
    modules[3].y = -26.5;

    modules[0].angle = 0;
    modules[1].angle = 0;
    modules[2].angle = 0;
    modules[3].angle = 0;

    //solve_fabrik(15,0);

    for (int i = 0; i <= 3; ++i) {
        printf("Joint number %i ->  X: %f , Y: %f , Angle(degree): %f \n", i, modules[i].x, modules[i].y, modules[i].angle);
    }

    //Forward kinematics check:
    float a1 = modules[0].angle;
    float a2 = modules[1].angle;
    float a3 = modules[2].angle;

    //printf(" ZONECHECK: %i ", getZone(15,-20) );
    printf(" EXTENSIONCHECK : %f %f", getVectorPosition(5,-5).x, getVectorPosition(5,-5).y );

    setdegreesInZones(15,-20);

    printf(" SETDEGREESZONE : %f %f %f", modules[0].angle, modules[1].angle, modules[2].angle);

    float outX = (lengths[0]*sin(a1)) + (lengths[1]*sin(a1+a2)) + (lengths[2]*sin(a1+a2+a3));
    float outY = (lengths[0]*cos(a1)) + (lengths[1]*cos(a1+a2)) + (lengths[2]*cos(a1+a2+a3));

    //printf("Forwards kinematics check : X: %f , Y: %f", outX, outY);

    //test of getangle method =(-8,1,-2,7) shall return 66.9 degrees.
    //printf("\n ANGLE CHECK! %f ", getAngle(-8,1,-2,7));
    //printf("\n ANGLE CHECK! %f ", getAngle(-0.7,-10.4,0,-10.5));



    return 0;
}
