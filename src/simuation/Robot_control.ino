#include <stdio.h>
#include <math.h>

#define METAL 1

#if defined METAL
#define SERVOMIN  112// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  560// this is the 'maximum' pulse length count (out of 4096)


#elif defined BIG
#define BIGSERVOMIN  75// this is the 'minimum' pulse length count (out of 4096)
#define BIGSERVOMAX  550// this is the 'maximum' pulse length count (out of 4096)
#endif

#define NUM_MOTORS 3 // for now we only use two joints simultaneously
#define LEFTEND -90 // for calibration?
#define RIGHTEND 90 // for calibration?

#include <string.h>
#include <stdlib.h>
#include <Servo.h> 
#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>


unsigned long previousMillis = 0;
unsigned long previousMillis2 = 0;
int tempiterator = 0;
unsigned long currentMillis = 0;
long INTERVAL = 20;

float incoming[NUM_MOTORS]; //buffer
float incomingXY[2]; //buffer for the input X and Y coordinate 
float pwmvalue[NUM_MOTORS]; //stores current angles in machine language
float pwmtarget[NUM_MOTORS]; //stores target angles
float difference[NUM_MOTORS];
float pwmincrement[NUM_MOTORS]; // store whatever is inbetween.

float Kp = 0.07;
float Ki = 0.0001;
float Kd = 3;

float error[NUM_MOTORS];
float prev_error[NUM_MOTORS];
float errorsum = 0;

int controlMode = 2;

float calib[NUM_MOTORS]; // used to calibrate offsets
byte i = 0;
char record[100];
char XYrecord[100];
char recvchar;
byte indx = 0;
Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();
//=====================FOR FABRIK KOSTYA=======================
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

struct moduleAngles{
    float a1;
    float a2;
    float a3;
};

struct moduleAngles moveQueue[10];
int moveQueueSize = 0;

//like a list of joints using a struct....
struct Joint modules[4];

// =======================FOR FABRIK KOSTYA===================

void setup() 
{
  Serial.begin(9600);
  pwm.begin();
  pwm.setPWMFreq(60); 
  delay(10);
  zeroCalib();
  
  // offsets of angular positions to be determined by students for each motor
  setCalib(0,15); // offset for motor 0
  setCalib(1,12); // offset for motor 1
  setCalib(2,-17); //offset for motor 2

  incoming[0] = 0;
  incoming[1] = 0;
  incoming[2] = 0;
  
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

  for (int i = 0; i < 10; ++i) {
        moveQueue[i].a1 = -100;
        moveQueue[i].a2 = -100;
        moveQueue[i].a3 = -100;
   }
    
  //writeToMotorV2();
  while(!Serial);
}

// main function
// receives bytes from Serial communication
// If full data packages is received, values are extracted
// If data package is correct, motor positions are being updated

void loop() 
{
    if (Serial.available())
    {
      //control by given angles....
      if(controlMode == 1){
        recvchar = Serial.read();
        if (recvchar != '\n')
        { 
            record[indx++] = recvchar;
        }
        else if (recvchar == '\n')
        {
          record[indx] = '\0';
          indx = 0;
          Serial.println(record);

           // extract motor positions from data package. - PUT THE Delta to incmoing array
          getData(record);        
          
           for (byte j = 0 ; j < NUM_MOTORS ; j++){     

              //set a new target position in pwm values.
              pwmtarget[j] = map(incoming[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
                                        
            }
                   
        }

        
      }
      
      //control by given coordinates
      if(controlMode == 2){
        recvchar = Serial.read();
        if (recvchar != '\n')
        { 
            XYrecord[indx++] = recvchar;
        }
        else if (recvchar == '\n')
        {
          XYrecord[indx] = '\0';
          indx = 0;

          // extract motor positions from data package. - PUT THE Delta to incmoing array
          getDataXY(XYrecord); 
          Serial.println("input: ");
          Serial.println(incomingXY[0]);       
          Serial.println(incomingXY[1]); 
          if(incomingXY[0] == 0 && incomingXY[1] == 0){
            reset_to_init();
          }
          else{
            setdegreesInZones(incomingXY[0],incomingXY[1]);
          }

           Serial.println("Fabrik values:   ");
           Serial.println(modules[0].angle);
           Serial.println(modules[1].angle);
           Serial.println(modules[2].angle);

           pwmtarget[0] = map(modules[0].angle,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
           pwmtarget[1] = map(modules[1].angle,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
           pwmtarget[2] = map(modules[2].angle,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
        
      }
                        
    }

    if(controlMode == 3 ){
      recvchar = Serial.read();
        if (recvchar != '\n')
        { 
            XYrecord[indx++] = recvchar;
        }
        else if (recvchar == '\n')
        {
          XYrecord[indx] = '\0';
          indx = 0;

          // extract motor positions from data package. - PUT THE Delta to incmoing array
          getDataXY(XYrecord);          
          intermediate_fabrik(incomingXY[0],incomingXY[1]);        
      }
   }
}     
         
//===============================movemnt loop====================
currentMillis = millis();

if(controlMode == 3 ){
  if((currentMillis - previousMillis2) > 200){ 
       previousMillis2 = currentMillis;
    //check if the queue value is valid...    
    if(moveQueue[tempiterator].a1 != -100){   
      pwmtarget[0] = map(moveQueue[tempiterator].a1,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
      pwmtarget[1] = map(moveQueue[tempiterator].a2,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
      pwmtarget[2] = map(moveQueue[tempiterator].a3,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
    }
    if(tempiterator < moveQueueSize){
      tempiterator += 1;
    }
    else{
      tempiterator = 0;

      for (int i = 0; i < 10; ++i) {
        moveQueue[i].a1 = -100;
        moveQueue[i].a2 = -100;
        moveQueue[i].a3 = -100;
        }
   
    }
    
  }
}
      
     if((currentMillis - previousMillis) > INTERVAL){ 
       previousMillis = currentMillis;

        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {  
          
          error[j] = pwmtarget[j] - pwmvalue[j];
          
          if(abs(prev_error[j]) > 3){
            errorsum += (error[j] * INTERVAL);
          }

            float P = Kp * error[j];
            
            float I = Ki * errorsum;

            float D = Kd * ((error[j] - prev_error[j]) / INTERVAL);
            
            //Serial.println(((error[j] - prev_error[j]) / INTERVAL));
             
            prev_error[j] = error[j];
            
            pwmvalue[j] += (P + I + D);
                             
            
        }
     }
     
     writeToMotorV2();
}
//======================END LOOP=============================

// extract data from data packages
// expected format: VALUE KOMMA VALUE \n
void getData(char record[])
{
    i = 0;
    char *index = strtok(record, ",");
    while(index != NULL)
    {
        incoming[i++] = atof(index); 
        index = strtok(NULL, ",");
    }
}

void getDataXY(char record[])
{
    i = 0;
    char *index = strtok(record, ",");
    while(index != NULL)
    {
        incomingXY[i++] = atof(index); 
        index = strtok(NULL, ",");
    }
}


void writeToMotorV2()
{
        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {           
          float temp = pwmvalue[j] + calib[j] ; 
          // do not remove this safety function to avoid hardware damages
          temp = constrain(temp ,SERVOMIN,SERVOMAX);
//          Serial.println(temp);
          pwm.setPWM(j, 0, temp); // function by Adafruit library
        }
}

// Print data
void printData(float data[], char what)
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++)
    {
      Serial.print(data[j]);
      Serial.print('\t');
      Serial.print(what);
    }
    Serial.println(); 
}

// Initialize all calibration values to zero
void zeroCalib()
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++){
      calib[j] = 0;
      //set initial position of robot to be straight.
      pwmvalue[j] = map(0,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
      pwmtarget[j] = map(0,LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX); 
    }
      
}

// Update calibration value
void setCalib(int motor,int val)
{
    if(motor < NUM_MOTORS)
        calib[motor] = val;
        
    else
       Serial.println("Enter a valid motor number"); 
}

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


void solve_fabrik(float xTarget, float yTarget) {

    float distFromRoot = distance(0,0,xTarget,yTarget);
    //check if the target is possible to reach:

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
    //End effector direction: 1 is left, 2 is right.

    if(getZone(x,y) == 1){

        struct Tuple temp = getVectorPosition(x,y);

        modules[1].x = temp.x;
        modules[1].y = temp.y;

        if(modules[1].x < 0){
            modules[0].angle = -1 * getAngle(modules[1].x,modules[1].y, 0, -10.5);
            modules[1].angle = -1 * modules[0].angle;
            modules[2].angle = -90;

        }
        else{
            modules[0].angle = getAngle(modules[1].x,modules[1].y, 0, -10.5);
            modules[1].angle = -1 * modules[0].angle;
            modules[2].angle = -90;
        }

    }
    else if(getZone(x,y) == 2){
        Serial.println("using Trignometric method!"); 
        struct Tuple temp = getVectorPosition(x,y);

        modules[2].x = temp.x;
        modules[2].y = temp.y;

        if(modules[2].x < 0){
            modules[0].angle = -1 * getAngle(modules[2].x,modules[2].y, 0, -10.5);
            modules[1].angle = 0;
//            modules[2].angle = -1 * modules[0].angle;
            modules[2].angle = -90;
        }
        else{
            modules[0].angle = getAngle(modules[2].x,modules[2].y, 0, -10.5);
            modules[1].angle = 0;
//            modules[2].angle = -1 * modules[0].angle;
            modules[2].angle = -90;
        }

    }
    else{
      //TODO
      Serial.println("Using Fabrik"); 
        solve_fabrik(x,y);
        //intermediate_fabrik(x,y);
    }

    //todo update the backend modules positions via Foreward Kinematics

    return;

}

struct Tuple forwardKinematics(float a, float b, float c) {

    //convert degrees to radians:
    a = (a/180) * 3.1415;
    b = (b/180) * 3.1415;
    c = (c/180) * 3.1415;

    float outX = (lengths[0]*sin(a)) + (lengths[1]*sin(a+b)) + (lengths[2]*sin(a+b+c));
    float outY = -1*(lengths[0]*cos(a)) + -1*(lengths[1]*cos(a+b)) + -1*(lengths[2]*cos(a+b+c));

    struct Tuple out = {outX, outY};

    return out;
}

void reset_to_init(){
  
    incoming[0] = 0;
    incoming[1] = 0;
    incoming[2] = 0;
    
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

}

void intermediate_fabrik(float xTarget, float yTarget){
    float totalDistance = distance(modules[3].x,modules[3].y,xTarget,yTarget);
    float totalDistanceX = sqrtf(((modules[3].x-xTarget)*(modules[3].x-xTarget)));
    float totalDistanceY = sqrtf(((modules[3].y-yTarget)*(modules[3].y-yTarget)));

    printf("Distance between two points %f, Distance for X %f, Distance for Y %f " , totalDistance, totalDistanceX, totalDistanceY);

    //stepsize is how often do we want to calculate fabrik at a given region
    float stepSize = 4;
    int steps = 1;

    //determine how many iterations shall we run
    while(totalDistance - stepSize > 0){
        totalDistance = totalDistance - stepSize;
        steps += 1;
    }

    printf("Number of steps needed: %i ", steps);

    float incrementX = 0;
    float incrementY = 0;

    if(totalDistanceX>0){
        incrementX = totalDistanceX/steps;
    }
    if(totalDistanceY>0){
        incrementY = totalDistanceY/steps;
    }

    //put in a queue of angles to be passed towards control in future...
    moveQueueSize = steps;

    for (int i = 0; i < steps; i++) {

        solve_fabrik(modules[3].x+incrementX,modules[3].y+incrementY);

        moveQueue[i].a1 = modules[0].angle;
        moveQueue[i].a2 = modules[1].angle;
        moveQueue[i].a3 = modules[2].angle;

    }

    //Do one last iteration once we are really really close to the target.
//    solve_fabrik(xTarget,yTarget);
//    moveQueue[steps].a1 = modules[0].angle;
//    moveQueue[steps].a2 = modules[1].angle;
//    moveQueue[steps].a3 = modules[2].angle;

}
