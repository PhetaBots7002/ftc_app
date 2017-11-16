package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Mecanum Chassis Hardware configuration
 *
 * OUR MAIN PhetaBot Hardware setup
 *
 */

public class HardwareSetupMecanum {

   /* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    */

    //motors
    public DcMotor motorFrontLeft = null;
    public DcMotor motorFrontRight = null;
    public DcMotor motorBackLeft = null;
    public DcMotor motorBackRight = null;

    public DcMotor motorLift = null;
    //servos
    public Servo servoClamp    = null;

   // public Servo servo1 = null;
   // public Servo servo2 = null;
   // public Servo servo180 = null;
    //sensors
        //Add sensors here

    /* local OpMode members. */
    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0;  For CRServo 0.5=stop greater or less than will spin in that direction

    final static double MOTOR_STOP = 0.0; // sets motor power to zero
    //final static double OPEN = 1;  //180 Servos not currently used
    //final static double CLOSE = 0;

    //CR servo variables
    double SpinLeft = 0.0;
    double SpinRight = 0.7;
    double STOP = 0.5;      //CR servo Stopped

   /* Constructor   // this is not required as JAVA does it for you, but useful if you want to add
    * function to this method when called in OpModes.
    */
    public HardwareSetupMecanum() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /************************************************************
         * MOTOR SECTION
         ************************************************************/
        // Define Motors to match Robot Configuration File
        motorFrontLeft = hwMap.dcMotor.get("FL"); //port 1
        motorFrontRight = hwMap.dcMotor.get("FR");//port 0
        motorBackLeft = hwMap.dcMotor.get("BL");  //port 2
        motorBackRight = hwMap.dcMotor.get("BR"); //port 3

        motorLift = hwMap.dcMotor.get("Lift");

        servoClamp = hwMap.servo.get("Clamp");

       // servo1 = hwMap.servo.get("servo1");
       // servo2 = hwMap.servo.get("servo2");
       // servo180 = hwMap.servo.get("servo180");

        // eg: Set the drive motor directions:
        motorLift.setDirection(DcMotor.Direction.FORWARD); // Can change based on motor configuration
        // reset Encoder to zero
        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // runs motor faster than when set to RUN_USING_ENCODER
        //motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // not sure why this happens


        // Set the drive motor directions:
        //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);   // NOTE: did not need opposing motors reversed.
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);  //       as this is done in the formula of the MecanumTeleOp
        //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        //Keep the motors from moving during initialize.
        motorFrontLeft.setPower(MOTOR_STOP);
        motorFrontRight.setPower(MOTOR_STOP);
        motorBackLeft.setPower(MOTOR_STOP);
        motorBackRight.setPower(MOTOR_STOP);

        motorLift.setPower(MOTOR_STOP);
        /************************************************************
         * SERVO SECTION
         ************************************************************/
        servoClamp.setPosition(STOP);

      //  servo1.setPosition(STOP);
       // servo2.setPosition(STOP);
       // servo180.setPosition(STOP);


        /************************************************************
         * SENSOR SECTION
         ************************************************************/
            //Add sensors

   }

}

