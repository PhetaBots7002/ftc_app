package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by TeameurekaRobotics on 12/30/2016
 *
 * This file contains an example Hardware Setup Class for a 4 motor Holonomic drive.
 *
 * It can be customized to match the configuration of your Bot by adding/removing hardware, and then used to instantiate
 * your bot hardware configuration in all your OpModes. This will clean up OpMode code by putting all
 * the configuration here, needing only a single instantiation inside your OpModes and avoid having to change configuration
 * in all OpModes when hardware is changed on robot.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 *
 */

public class HardwareSetupMenOfSteele {

   /* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    */

    //Drive motors
    public DcMotor motorFrontRight = null;
    public DcMotor motorFrontLeft = null;
    public DcMotor motorBackRight = null;
    public DcMotor motorBackLeft = null;

    //Accessories motors
    public DcMotor motorArm = null;
    public DcMotor motorLift = null;
    public DcMotor motorExt = null;
    //public DcMotor armMotor = null;

    //servos
    public Servo servoEgg = null;
    public Servo servoBucket1 = null;
    public Servo servoBucket2 = null;

    //sensors
        //Add sensors here

    /* local OpMode members. */
    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0  For CRServo 0.5=stop greater or less than will spin in that direction

    final static double CLOSED = 0.0;
    final static double OPEN = 0.5;
    //sets motor power to 0
    final static double MOTOR_STOP = 0.0; // sets motor power to zero
    
    //CR servo variables
     double SpinIn = 0.3;
     double SpinOut = 0.8;
     double STOP = 0.5;
     
   /* Constructor   // this is not required as JAVA does it for you, but useful if you want to add
    * function to this method when called in OpModes.
    */
    public HardwareSetupMenOfSteele() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /************************************************************
         * MOTOR SECTION
         ************************************************************/
        // Define Motors to match Robot Configuration File
        motorFrontLeft = hwMap.dcMotor.get("motorFL");
        motorFrontRight = hwMap.dcMotor.get("motorFR");
        motorBackLeft = hwMap.dcMotor.get("motorBL");
        motorBackRight = hwMap.dcMotor.get("motorBR");

        //armMotor = hwMap.dcMotor.get ("armMotor");
        motorArm = hwMap.dcMotor.get("motorArm");
        motorLift =  hwMap.dcMotor.get("motorLift");
        motorExt = hwMap.dcMotor.get("motorExt");

        servoEgg = hwMap.servo.get("Egg");
        servoBucket1 = hwMap.servo.get("servoBt1");
        servoBucket2 = hwMap.servo.get("servoBt2");
        // Set the drive motor directions:
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        //Keep the motors from moving during initialize.
        motorFrontLeft.setPower(MOTOR_STOP);
        motorFrontRight.setPower(MOTOR_STOP);
        motorBackLeft.setPower(MOTOR_STOP);
        motorBackRight.setPower(MOTOR_STOP);
        motorArm.setPower(MOTOR_STOP);
        motorLift.setPower(MOTOR_STOP);
        motorExt.setPower(MOTOR_STOP);

        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /************************************************************
         * SERVO SECTION
         ************************************************************/
        
         servoEgg.setPosition(CLOSED);
         servoBucket1.setPosition(STOP);
         servoBucket2.setPosition(STOP);
        /************************************************************
         * SENSOR SECTION
         ************************************************************/
            //Add sensors

   }

}

