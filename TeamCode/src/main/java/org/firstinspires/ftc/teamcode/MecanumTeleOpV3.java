package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/*
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
@TeleOp(name = "MecanumTeleOpV3", group = "Phetabot")
//@Disabled
public class MecanumTeleOpV3 extends OpMode  {

    HardwareSetupMecanum r     =   new HardwareSetupMecanum();

    /**
     * Constructor
     */
    public MecanumTeleOpV3() {

    }

    @Override
    public void init() {
      /*
        Uses HardwareSetupMecanum and Initializes.
       */
        r.init(hardwareMap);  //Initialize hardware from the Hardware Setup
    }

    @Override
    public void loop() {
//First Person View Drive
            // left stick controls direction
            // right stick X controls rotation

            float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        // holonomic formulas

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;  // neg gamepad1LeftY values for LeftMotors reverses direction of opposing motors
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;   // neg gamepad1LeftY values for LeftMotors reverses direction of opposing motors

        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        // write the values to the motors



        if(gamepad1.left_trigger!=0)
        {

                r.motorFrontRight.setPower(FrontRight/4);
                r.motorFrontLeft.setPower(FrontLeft/4);
                r.motorBackLeft.setPower(BackLeft/4);
                r.motorBackRight.setPower(BackRight/4);

        }
        else
        {
            r.motorFrontRight.setPower(FrontRight);
            r.motorFrontLeft.setPower(FrontLeft);
            r.motorBackLeft.setPower(BackLeft);
            r.motorBackRight.setPower(BackRight);
        }



        //Lift commands, if encoder clicks more than 0, then can lift with left_stick_y.
        //If encoder clicks greater than 1500, than dont move.

        if (gamepad2.left_trigger > 0.0 && gamepad2.right_trigger == 0.0 && r.motorLift.getCurrentPosition() > 0.0) // encoder greater that lower limit
        {
            r.motorLift.setPower(-gamepad2.left_trigger / 2.0); // let trigger run -motor DOWN
        }
        else if (gamepad2.left_trigger == 0.0 && gamepad2.right_trigger == 0.0)
        {
            r.motorLift.setPower (0.1);
            Thread.sleep(100);
        }
//
        else if (gamepad2.right_trigger > 0.0 && gamepad2.left_trigger == 0.0 && r.motorLift.getCurrentPosition() < 5380.0) //encoder less than Max limit
        {
            r.motorLift.setPower(gamepad2.right_trigger / 2.0); //let trigger run +motor UP
        }

        else
        {
            r.motorLift.setPower(0.0); // else not trigger, then set to off or some value of 'hold' power
        }


 //CR Servo commands
        if(gamepad2.b) //button b will spinLeft open
        {
            r.servoClamp.setPosition(r.SpinLeft);
        }
        else if (gamepad2.x) //button x will spinRight close
        {
            r.servoClamp.setPosition(r.SpinRight);
        }
        else
        {
            r.servoClamp.setPosition(r.STOP);
        }

//BallKnocker Extention and Retraction.

        if (gamepad2.dpad_left)
        {
            r.servoR.setPosition(.5);
        }
        if (gamepad2.dpad_up)
        {
            r.servoR.setPosition(1);
        }
        if (gamepad2.dpad_right)
        {
            r.servoR.setPosition(.1);
        }
        if (gamepad2.dpad_down)
        {
            r.servoR.setPosition(0);
        }







        if (gamepad1.dpad_left)
        {
            r.servoB.setPosition(.5);
        }
        if (gamepad1.dpad_up)
        {
            r.servoB.setPosition(0);
        }
        if (gamepad1.dpad_right)
        {
            r.servoB.setPosition(.9);
        }
        if (gamepad1.dpad_down)
        {
            r.servoB.setPosition(1);
        }


      /*
       * Telemetry for debugging
       */
       /* telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " + String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));
       */
        // Display running time and Encoder value
        telemetry.addData("Min=0/ Max=5380/ Encoder Clicks", + r.motorLift.getCurrentPosition());
        telemetry.update();

    }

    @Override
    public void stop() {

    }
    public void ArmUp(double power)
    {
        // write the values to the motors
        r.motorLift.setPower(power);//still need to test motor directions for desired movement
    }
    public void StopDown(double power, long time) throws InterruptedException
    {
        ArmUp(power);
        Thread.sleep(time);
    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.10, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);


        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}