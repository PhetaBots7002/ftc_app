/*
   Holonomic/Mecanum concept autonomous program. Driving motors for TIME

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
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareSetupMecanum;

@Autonomous(name="Ablue2", group="Phetabot")
//@Disabled
public class ABlue2 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    /* Define Hardware setup */
    HardwareSetupMecanum r     =   new HardwareSetupMecanum();
    /**
     * Constructor
     */
    public ABlue2() {
    }

    @Override
    public void runOpMode() throws InterruptedException {
        r.init(hardwareMap);  //Initialize hardware from the HardwareHolonomic Setup

        //adds feedback telemetry to DS
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /************************
         * Autonomous Code Below://!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         *************************///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        DriveForwardTime(DRIVE_POWER, 600);
        StopDrivingTime(500);

        StrafeRight(DRIVE_POWER, 500); //neg power drives backwards
        StopDrivingTime(500);

        DriveForwardTime(DRIVE_POWER, 500);
        StopDrivingTime(500);

        Drop(0.2, 500);
        StopDrivingTime(1000);

        DriveForwardTime(-DRIVE_POWER, 100);

        StopDriving();
    }//runOpMode
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /** Below: Basic Drive Methods used in Autonomous code...**/
    //set Drive Power variable
    double DRIVE_POWER = 1.0;

    public void DriveForward(double power)
    {
        // write the values to the motors
        r.motorFrontRight.setPower(power);//still need to test motor directions for desired movement
        r.motorFrontLeft.setPower(-power);
        r.motorBackRight.setPower(power);
        r.motorBackLeft.setPower(-power);
    }

    public void DriveForwardTime(double power, long time) throws InterruptedException
    {
        DriveForward(power);
        Thread.sleep(time);
    }

    public void StopDriving()
    {
        DriveForward(0);
    }

    public void StopDrivingTime(long time) throws InterruptedException
    {
        DriveForwardTime(0, time);
    }

    public void StrafeLeft(double power, long time) throws InterruptedException// Robot Slides to the left
    {
        // write the values to the motors
        r.motorFrontRight.setPower(power);
        r.motorFrontLeft.setPower(power);
        r.motorBackRight.setPower(-power);
        r.motorBackLeft.setPower(-power);
        Thread.sleep(time);
    }

    public void StrafeRight(double power, long time) throws InterruptedException// Robot Slides to the Right
    {
        StrafeLeft(-power, time);
    }

    public void SpinRight (double power, long time) throws InterruptedException
    {
        // write the values to the motors
        r.motorFrontRight.setPower(-power);
        r.motorFrontLeft.setPower(-power);
        r.motorBackRight.setPower(-power);
        r.motorBackLeft.setPower(-power);
        Thread.sleep(time);
    }

    public void SpinLeft (double power, long time) throws InterruptedException
    {
        SpinRight(-power, time);
    }

    //Drops or grabs Glyph
    public void Drop (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.2);
    }
    public void Grab (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.3);
    }


/*** Currently no Servo configured in Holonomic Hardware setup

 public void RaiseArm()
 {
 r.armServo.setPosition(.8); //note: uses servo instead of motor.
 }

 public void LowerArm()
 {
 r.armServo.setPosition(.2);
 }
 */


}//TestAutoDriveByTime
