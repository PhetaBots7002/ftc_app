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

public class TestHardware {

   /* Declare Public OpMode members.
    *these are the null statements to make sure nothing is stored in the variables.
    */

    //motors
    public DcMotor motor1 = null;


    HardwareMap hwMap        = null;

    //Create and set default servo positions & MOTOR STOP variables.
    //Possible servo values: 0.0 - 1.0;  For CRServo 0.5=stop greater or less than will spin in that direction

    final static double MOTOR_STOP = 0.0; // sets motor power to zero



    public TestHardware() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /************************************************************
         * MOTOR SECTION
         ************************************************************/
        // Define Motors to match Robot Configuration File
        motor1 = hwMap.dcMotor.get("motor1"); //port 0


        //Keep the motors from moving during initialize.
        motor1.setPower(MOTOR_STOP);



    }

}

