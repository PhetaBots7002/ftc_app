package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Mecanum Chassis Hardware configuration
 *
 * OUR MAIN Bot Hardware setup
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
    public CRServo servoClamp = null;

    //sensors
        //Add sensors here

    /* local OpMode members. */
    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0  For CRServo 0.5=stop greater or less than will spin in that direction
    //final static double CLOSED = 0.2;
    //final static double OPEN = 0.8;
    final static double MOTOR_STOP = 0.0; // sets motor power to zero
    final static double SERVO_STOP = 0.5;
    final static double OPEN = 1;
    final static double CLOSE = 0;
    //CR servo variables
        //Add servo variable here

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
        motorFrontLeft = hwMap.dcMotor.get("FL");
        motorFrontRight = hwMap.dcMotor.get("FR");
        motorBackLeft = hwMap.dcMotor.get("BL");
        motorBackRight = hwMap.dcMotor.get("BR");

        motorLift = hwMap.dcMotor.get("Lift");

        servoClamp = hwMap.crservo.get("Clamp");

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

        motorLift.setPower(MOTOR_STOP);
        /************************************************************
         * SERVO SECTION
         ************************************************************/
        servoClamp.setPower(SERVO_STOP);


        /************************************************************
         * SENSOR SECTION
         ************************************************************/
            //Add sensors

   }

}

