package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 *
 * Created by Maddie, FTC Team 4962, The Rockettes
 * version 1.0 Aug 11, 2016
 * This is an Iterative vs Linear program
 * for TeleOp control with a single controller
 */

/*
   Holonomic concepts from:
   http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
   Robot wheel mapping:
          X FRONT X
        X           X
      X  FL       FR  X
              X
             XXX
              X
      X  BL       BR  X
        X           X
          X       X
*/
@TeleOp(name = "TeleOpMenOfSteele",group = "TeleOp")
//@Disabled
public class TeleOp_HolonomicMenOfSteele extends OpMode {


    HardwareSetupMenOfSteele robot     =   new HardwareSetupMenOfSteele();

    int     armHoldPosition;             // reading of arm position when buttons released to hold
    int     liftHoldPosition;
    double  slopeVal         = 2000.0;   // increase or decrease to perfect
    /**
     *

     * Constructor
     */
    private ElapsedTime runtime = new ElapsedTime();

    public TeleOp_HolonomicMenOfSteele() {

    }

    @Override
    public void init() {
      /*
       * Use the hardwareMap to get the dc motors and servos by name. Note
       * that the names of the devices must match the names used when you
       * configured your robot and created the configuration file.
       */
        robot.init(hardwareMap);  //Initialize hardware from the HardwareHolonomic Setup

        // initialize current position of arm motor
        liftHoldPosition = robot.motorLift.getCurrentPosition();
        armHoldPosition = robot.motorArm.getCurrentPosition();

    }



    @Override
    public void loop() {

        runtime.reset();

        // left stick X controls Strafe & Y controls Spin Direction
        // right stick Y controls drive Forward/Backward

        float gamepad1LeftY = -gamepad1.left_stick_y;   // drives spin left/right
        float gamepad1LeftX = gamepad1.left_stick_x;    // strafe direction (side to side)
        float gamepad1RightY = gamepad1.right_stick_y;  //drives forwards and backwards


        // holonomic formulas

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightY;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightY;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightY;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightY;

        //        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        // write the values to the motors
        robot.motorFrontRight.setPower(FrontRight);
        robot.motorFrontLeft.setPower(-FrontLeft);
        robot.motorBackLeft.setPower(-BackLeft);
        robot.motorBackRight.setPower(BackRight);


       //moves arm
        if (gamepad2.right_stick_y < 0.0)
        {
            robot.motorArm.setPower(-gamepad2.right_stick_y/2 ); // let stick drive UP (note this is positive value on joystick)
            armHoldPosition = robot.motorArm.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position

        }
        else if (gamepad2.right_stick_y > 0.0) //encoder less than Max limit
        {
            robot.motorArm.setPower(-gamepad2.right_stick_y/2); //let stick drive DOWN (note this is negative value on joystick)
            armHoldPosition = robot.motorArm.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position
        }
        else // to maintain the current position
        {
            robot.motorArm.setPower((double) (armHoldPosition - robot.motorArm.getCurrentPosition()) / slopeVal);
        }

        // Extend arm
        if (gamepad2.right_bumper)
        {
            robot.motorExt.setPower(gamepad2.right_trigger); // let stick drive UP (note this is positive value on joystick)
        }
        else if (!gamepad2.right_bumper ) //encoder less than Max limit
        {
            robot.motorExt.setPower(-gamepad2.right_trigger); //let stick drive DOWN (note this is negative value on joystick)
        }
        else
        {
            robot.motorExt.setPower(0.0);
        }


        //moves lift
        if (gamepad2.left_stick_y < 0.0)
        {
            robot.motorLift.setPower(gamepad2.left_stick_y ); // let stick drive UP (note this is positive value on joystick)
            liftHoldPosition = robot.motorLift.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position

        }
        else if (gamepad2.left_stick_y > 0.0) //encoder less than Max limit
        {
            robot.motorLift.setPower(gamepad2.left_stick_y); //let stick drive DOWN (note this is negative value on joystick)
            liftHoldPosition = robot.motorLift.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position
        }
        else // to maintain the current position
        {
            robot.motorLift.setPower((double) (liftHoldPosition - robot.motorLift.getCurrentPosition()) / slopeVal);   // Note that if the lift is lower than desired position,
            // the subtraction will be positive and the motor will
            // attempt to raise the lift. If it is too high it will
            // be negative and thus try to lower the lift
            // adjust slopeVal to acheived perfect hold power
        }

        //bucket control
        // take ball
        if (gamepad2.b)
        {
            robot.servoBucket1.setPosition(robot.SpinIn);
            robot.servoBucket2.setPosition(robot.SpinOut);
        }

        else if(gamepad2.x)
        {
            robot.servoBucket1.setPosition(robot.SpinOut);
            robot.servoBucket2.setPosition(robot.SpinIn);
        }
        else
        {
            robot.servoBucket1.setPosition(robot.STOP);
            robot.servoBucket2.setPosition(robot.STOP);
        }

        //bucket tilt
        if (gamepad2.a)
        {
            robot.servoEgg.setPosition(0.9);
        }

        /*
       * Telemetry for debugging
       */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " + String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightY));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));

        telemetry.addData("Status", "RunTime: " + runtime.toString());
        telemetry.addData("LiftPosition: ", + robot.motorLift.getCurrentPosition());
        telemetry.addData("LiftHoldPosition:" , + liftHoldPosition);
        telemetry.addData("ArmPosition: ", + robot.motorArm.getCurrentPosition());
        telemetry.addData("ArmHoldPosition", + armHoldPosition);
}

    @Override
    public void stop()
    {

    }

}