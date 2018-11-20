#define METAL 1

#if defined PLASTIC
#define SERVOMIN  135// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  550// this is the 'maximum' pulse length count (out of 4096)
#elif defined METAL
#define SERVOMIN  75// this is the 'minimum' pulse length count (out of 4096)
#define SERVOMAX  550// this is the 'maximum' pulse length count (out of 4096)
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
unsigned long currentMillis = 0;
long INTERVAL = 20;

float incoming[NUM_MOTORS]; //buffer

float pwmvalue[NUM_MOTORS]; //stores current angles
float pwmtarget[NUM_MOTORS]; //stores target angles
float difference[NUM_MOTORS];
float pwmincrement[NUM_MOTORS]; // store whatever is inbetween.
float pwmtemp[NUM_MOTORS];

float calib[NUM_MOTORS]; // used to calibrate offsets
byte i = 0;
char record[100];
char recvchar;
byte indx = 0;
Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();

void setup() 
{
  Serial.begin(9600);
  pwm.begin();
  pwm.setPWMFreq(60); 
  delay(10);
  zeroCalib();
  
  // offsets of angular positions to be determined by students for each motor
  setCalib(0,15); // offset for motor 0
  setCalib(1,10); // offset for motor 1
  setCalib(2,-2); //offset for motor 2

  writeToMotor();  
  

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
          
              pwmtarget[j] = pwmvalue[j] + incoming[j];

              //get the difference between current and goal values
              difference[j] = abs(pwmtarget[j] - pwmvalue[j]);
          
              pwmincrement[j] = difference[j]/100;
              
          
            }

          
          printData(pwmvalue); // for debugging send pwm values to monitor
          printData(pwmtarget);
          printData(difference);
          printData(pwmincrement);
          
          //writeToMotor();

          
        }
                
    }

//smooth transition of robot... execute each 20 ms 

    currentMillis = millis();
     if((currentMillis - previousMillis) > INTERVAL){ 
      
       previousMillis = currentMillis;

        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {  
          
          if(abs(pwmtarget[j] - pwmvalue[j]) > 5 &&  pwmtemp[j] < difference[j]){

            pwmtemp[j] += pwmincrement[j];  
            printData(pwmincrement);

            
            writeToMotorV2();
          }
          else{
            pwmtemp[j] = 0;
            //pwmtemp[j] += calib[j];
          }
          
           
           
        }
         
     }

    
}

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

// update servo motor positions
void writeToMotor()
{
    if(i == NUM_MOTORS)
    {
        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        {     
          incoming[j] += calib[j];
          pwmvalue[j] = map(incoming[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
          // do not remove this safety function to avoid hardware damages
          pwmvalue[j] = constrain(pwmvalue[j],SERVOMIN,SERVOMAX);
          pwm.setPWM(j, 0, pwmvalue[j]); // function by Adafruit library
          delay(10);
        }
    }
    else
    {
        Serial.println("Enter correct number of values separated by commas!");
    }
}

void writeToMotorV2()
{
    
        for (byte j = 0 ; j < NUM_MOTORS ; j++)
        { 
          
          pwmvalue[j] = map(pwmtemp[j] + calib[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
          
          // do not remove this safety function to avoid hardware damages
          pwmvalue[j] = constrain(pwmvalue[j],SERVOMIN,SERVOMAX);
          pwm.setPWM(j, 0, pwmvalue[j]); // function by Adafruit library
          
        }
}


//convertToPWM(input[]){
//  
//  for (byte j = 0 ; j < NUM_MOTORS ; j++)
//        {     
//          incoming[j] += calib[j];
//          pwmvalue[j] = map(incoming[j],LEFTEND,RIGHTEND,SERVOMIN,SERVOMAX);
//          // do not remove this safety function to avoid hardware damages
//          pwmvalue[j] = constrain(pwmvalue[j],SERVOMIN,SERVOMAX);
//        }
//}




// Print data
void printData(float data[])
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++)
    {
      Serial.print(data[j]);
      Serial.print('\t');
    }
    Serial.println(); 
}

// Initialize all calibration values to zero
void zeroCalib()
{
    for (byte j = 0 ; j < NUM_MOTORS ; j++)
      calib[j] = 0;
}

// Update calibration value
void setCalib(int motor,int val)
{
    if(motor < NUM_MOTORS)
        calib[motor] = val;
    else
       Serial.println("Enter a valid motor number"); 
}
