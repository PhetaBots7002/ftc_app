package org.firstinspires.ftc.teamcode.LaserSharks;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 *  CHANGE THIS COMMENTING TO MATCH WHAT THIS PROGRAM IS FOR YOU !!!!!!
 *
 * Created by TeameurekaRobotics on 12/30/2016
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

public class SharkHardWare {

   /* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    */

    //holonomic motors
    public DcMotor motorFrontRight = null;
    public DcMotor motorFrontLeft = null;
    public DcMotor motorBackRight = null;
    public DcMotor motorBackLeft = null;

    //Arm motors
    public DcMotor motorLift = null;

    //Arm Servos
    public Servo servoR = null;
    public Servo servoL = null;

    /* local OpMode members. */
    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0  For CRServo 0.5=stop greater or less than will spin in that direction
    final static double CLOSED = 0.1;
    final static double OPEN = 0.9;
    final static double MOTOR_STOP = 0.0; // sets motor power to zero
    //CR servo variables
    double SpinLeft = 0.1;
    double SpinRight = 0.6;
    double STOP = 0.5;
   
   /* Constructor   // this is not required as JAVA does it for you, but useful if you want to add
    * function to this method when called in OpModes.
    */
    public SharkHardWare() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /************************************************************
         * MOTOR SECTION
         ************************************************************/
        // **Define Motors/Servos/Sensors to match Robot Configuration File**

        /// /Holonomic Drive
        motorFrontRight = hwMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hwMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hwMap.dcMotor.get("motorBackLeft");
        motorBackRight = hwMap.dcMotor.get("motorBackRight");

        //motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE); //may not need reverse
        //motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        //Arm motors
        motorLift = hwMap.dcMotor.get("motorLift");
        motorLift.setDirection(DcMotorSimple.Direction.REVERSE);

        //Keep the motors from moving during initialize.
        motorFrontLeft.setPower(MOTOR_STOP);
        motorFrontRight.setPower(MOTOR_STOP);
        motorBackLeft.setPower(MOTOR_STOP);
        motorBackRight.setPower(MOTOR_STOP);
        motorLift.setPower(MOTOR_STOP);


        /************************************************************
         * SERVO SECTION
         ************************************************************/
            //Add servo configuration

        servoR = hwMap.servo.get("servoR");
        servoR.setPosition(CLOSED);

        servoL = hwMap.servo.get("servoL");
        servoL.setPosition(OPEN);
        
        /************************************************************
         * SENSOR SECTION
         ************************************************************/
            //Add sensors

   }

}

