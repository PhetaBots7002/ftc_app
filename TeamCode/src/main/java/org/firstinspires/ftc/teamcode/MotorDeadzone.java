package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

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
@TeleOp(name = "MotorDZTest", group = "Test")
@Disabled
public class MotorDeadzone extends OpMode {

    TestHardware r     =   new TestHardware();

    /**
     * Constructor
     */
    public MotorDeadzone() {

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


        // holonomic formulas

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX;  // neg gamepad1LeftY values for LeftMotors reverses direction of opposing motors

        // clip the right/left values so that the values never exceed +/- 1
        FrontLeft = Range.clip(FrontLeft, -1, 1);

        // write the values to the motors
        r.motor1.setPower(FrontLeft);

    }

    @Override
    public void stop() {

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