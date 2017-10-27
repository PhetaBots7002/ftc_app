/*

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import  com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


*/
/**
 * Created by TeameurekaRobotics on 12/30/2016
 *
 * This file contains an example Hardware Setup Class.
 *
 * It can be customized to match the configuration of your Bot by adding/removing hardware, and then used to instantiate
 * your bot hardware configuration in all your OpModes. This will clean up OpMode code by putting all
 * the configuration here, needing only a single instantiation inside your OpModes and avoid having to change configuration
 * in all OpModes when hardware is changed on robot.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 *
 *//*


public class MyBotHardwareSetup {

   */
/* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    *//*


    //motors
    public DcMotor motorFL = null;
    public DcMotor motorFR = null;
    public DcMotor motorBL = null;
    public DcMotor motorBR = null;
    //servos
*/
/*    public Servo servoHandL = null;
    public Servo servoHandR = null;
    public Servo crServo    = null;

    //sensors
    public GyroSensor gyro  = null;
*//*

    */
/* local OpMode members. *//*

    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0  For CRServo 0.5=stop greater or less than will spin in that direction
//    final static double CLOSED = 0.2;
//    final static double OPEN = 0.8;
    final static double MOTOR_STOP = 0.0; // sets motor power to zero
    //CR servo variables
//    double SpinLeft = 0.1;
//    double SpinRight = 0.6;
//    double STOP = 0.5;

   */
/* Constructor   // this is not required as JAVA does it for you, but useful if you want to add
    * function to this method when called in OpModes.
    *//*

    public MyBotHardwareSetup() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        */
/************************************************************
         * MOTOR SECTION
         ************************************************************//*

        // Define Motors to match Robot Configuration File
        motorFL = hwMap.dcMotor.get("FL");
        motorFR = hwMap.dcMotor.get("FR");
        motorBL = hwMap.dcMotor.get("BL");
        motorBR = hwMap.dcMotor.get("BR");
        // Set the drive motor directions:
        motorFL.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        motorFR.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        motorBL.setDirection(DcMotor.Direction.FORWARD); // Can change based on motor configuration
        motorBR.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        //Keep the motors from moving during initialize.

*/
/*   Stetson - I believe this part of your code goes in the actual OpMode versus here in the hardware configuration - Rob
   **************************************************************************************************
     double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;



        motorFL.setPower(v1);
        motorFR.setPower(v2);
        motorBL.setPower(v3)
        motorBR.setPower(v4);
*****************************************************************************************************
*//*

        // Set motors to run USING or WITHOUT encoders
        // Depending upon your configuration and use
*/
/*        motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
 *//*

        */
/************************************************************
         * SERVO SECTION
        // Define Motors to match Robot Configuration File
 */
/*       servoHandL = hwMap.servo.get("servoHandL");
        servoHandR = hwMap.servo.get("servoHandR");
        crServo    = hwMap.servo.get("crServo");

        //Set servo hand grippers to open position.
        servoHandL.setPosition(OPEN);
        servoHandR.setPosition(OPEN);

        //Continous Rotation Servo
        crServo.setPosition(STOP);
*//*

        */
/************************************************************
         * SENSOR SECTION
         ************************************************************//*

        //Define sensors
//        gyro = hwMap.gyroSensor.get("gyro");
   }

}

*/