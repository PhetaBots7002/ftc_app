/**
 * Created by Maddie, FTC Team 4962, The Rockettes
 * version 1.0 Aug 11, 2016
 */

package org.firstinspires.ftc.teamcode.LaserSharks;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


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
@TeleOp(name = "SharkTeleOpHold", group = "competition")
//@Disabled
public class holonomic_drive_Hold extends OpMode {

    SharkHardWare shark = new SharkHardWare();

    // variables for arm limits and hold position
    // note: these can be placed in your hardwareSetup Class
    double  armMinPos        = 0.0;      // encoder position for arm at bottom
    double  armMaxPos        = 4350.0;   // encoder position for arm at top
    int     armHoldPosition;             // reading of arm position when buttons released to hold
    double  slopeVal         = 2175.0;   // increase or decrease to perfect

    /**
     * Constructor
     */
    public holonomic_drive_Hold() {
    }

        @Override
        public void init() {
            shark.init(hardwareMap);
            //init current position of arm motor
            armHoldPosition = shark.motorLift.getCurrentPosition();

            //adds feedback telemetry to DS
            telemetry.addData("Status", "Initialized");
            telemetry.addData("armPostion: ", + shark.motorLift.getCurrentPosition());
            telemetry.update();
        }



            @Override
            public void loop() {


                // left stick controls direction
                // right stick X controls rotation
                float gamepad1LeftY = -gamepad1.left_stick_y;
                float gamepad1LeftX = gamepad1.left_stick_x;
                float gamepad1RightX = gamepad1.right_stick_x;

                // holonomic formulas
                float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
                float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
                float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
                float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

                // clip the right/left values so that the values never exceed +/- 1
                FrontRight = Range.clip(FrontRight, -1, 1);
                FrontLeft = Range.clip(FrontLeft, -1, 1);
                BackLeft = Range.clip(BackLeft, -1, 1);
                BackRight = Range.clip(BackRight, -1, 1);

                //Slows the drive train
                double div = 0;
                if (gamepad1.right_bumper) {
                    div = 2;
                } else {
                    div = 1;
                }
                // write the values to the motors
                shark.motorFrontRight.setPower(FrontRight / div);
                shark.motorFrontLeft.setPower(FrontLeft / div);
                shark.motorBackLeft.setPower(BackLeft / div);
                shark.motorBackRight.setPower(BackRight / div);

                // Arm Control - Lift

                // Arm Control - Uses left/right triggers to control motor direction.
                // Uses Encoder values to set upper and lower limits to protect motors from over-driving lift
                // and to hold arm position on decent to account for gravitational inertia

                if (gamepad2.left_trigger > 0.0 && shark.motorLift.getCurrentPosition() > armMinPos) // encoder greater that lower limit
                {
                    shark.motorLift.setPower(-gamepad2.left_trigger ); // let trigger run -motor DOWN / div in half to slow motor
                    armHoldPosition = shark.motorLift.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position
                }
                else if (gamepad2.right_trigger > 0.0 && shark.motorLift.getCurrentPosition() < armMaxPos) //encoder less than Max limit
                {
                    shark.motorLift.setPower(gamepad2.right_trigger); //let trigger run +motor UP
                    armHoldPosition = shark.motorLift.getCurrentPosition(); // while the lift is moving, continuously reset the arm holding position
                }
                else //triggers are released - try to maintain the current position
                {
                    shark.motorLift.setPower((double) (armHoldPosition - shark.motorLift.getCurrentPosition()) / slopeVal);   // Note that if the lift is lower than desired position,
                    // the subtraction will be positive and the motor will
                    // attempt to raise the lift. If it is too high it will
                    // be negative and thus try to lower the lift
                    // adjust slopeVal to achieved perfect hold power
                }

                //open and close hand
                if (gamepad2.a) //button 'a' will open
                {
                    shark.servoL.setPosition(0.9);
                    shark.servoR.setPosition(0.1);
                } else if (gamepad2.b) //button 'b' will close
                {
                    shark.servoL.setPosition(0.5);
                    shark.servoR.setPosition(0.5);
                }


                /*
                 * Telemetry for debugging
                 */
                telemetry.addData("Text", "*** Robot Data***");
                telemetry.addData("Joy XL YL XR", String.format("%.2f", gamepad1LeftX) + " " +
                        String.format("%.2f", gamepad1LeftY) + " " + String.format("%.2f", gamepad1RightX));
                telemetry.addData("f left pwr", "front left  pwr: " + String.format("%.2f", FrontLeft));
                telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
                telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
                telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));
                telemetry.addData("armPostion: ", + shark.motorLift.getCurrentPosition());
                telemetry.addData("LeftTrigger: ", + gamepad2.left_trigger);
                telemetry.addData("RightTrigger: ", + gamepad2.right_trigger);
                telemetry.update();

            }//closes the loop

        @Override
        public void stop() {

        }//closes the stop


} //close teleop
