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
@TeleOp(name = "Shark TeleOp", group = "competition")
//@Disabled
public class holonomic_drive extends OpMode {

    SharkHardWare shark = new SharkHardWare();

    /**
     * Constructor
     */
    public holonomic_drive() {
    }

        @Override
        public void init() {
            shark.init(hardwareMap);
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

                shark.motorLift.setPower(gamepad2.right_stick_y);


                //open and close hand
                if (gamepad2.a) //button 'a' will open
                {
                    shark.servoL.setPosition(0.9);
                    shark.servoR.setPosition(0.1);
                } else if (gamepad2.b) //button 'b' will close
                {
                    shark.servoL.setPosition(0.4);
                    shark.servoR.setPosition(0.8);
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

            }//closes the loop

        @Override
        public void stop() {

        }//closes the stop


} //close teleop
