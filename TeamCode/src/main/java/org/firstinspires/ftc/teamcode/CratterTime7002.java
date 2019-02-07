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

import org.firstinspires.ftc.teamcode.ExampleCode.HardwareSetupHolonomicExample;

@Autonomous(name="CratterTime7002", group="Concept")
//@Disabled
public class CratterTime7002 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    /* Define Hardware setup */
    HardwareSetupMenOfSteele robot     =   new HardwareSetupMenOfSteele();
    /**
     * Constructor
     */
    public CratterTime7002() {
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);  //Initialize hardware from the HardwareHolonomic Setup

        //adds feedback telemetry to DS
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /************************
         * Autonomous Code Below://
         *************************/
        pullOut(0.9);
        StopDrivingTime(1600);
        setDown(-0.10, 5);
        StopDrivingTime(1200);
        StrafeRight(DRIVE_POWER, 800);
        StopDrivingTime(1200);
        setDown(0.7, 400);
        DriveForwardTime(DRIVE_POWER, 1500);
        StopDrivingTime(500);
        spitOut(0.9, 500);



     /////////////////////////////////////////////////////////////////////////////
        //SpinLeft(DRIVE_POWER, 1200);
        //DriveForwardTime(DRIVE_POWER, 2300);
        //SpinLeft(DRIVE_POWER, 600);
        //DriveForwardTime(DRIVE_POWER, 1000);
        //robot.servoBucket2.setPosition(robot.SpinOut);



        //DriveForwardTime(-DRIVE_POWER, 1000); //neg power drives backwards
        //StopDrivingTime(1000);
        

        StopDriving();

    }//runOpMode

    /** Below: Basic Drive Methods used in Autonomous code...**/
    //set Drive Power variable
    double DRIVE_POWER = 1.0;

    public void DriveForward(double power)
    {
        // write the values to the motors
        robot.motorFrontRight.setPower(power);//still need to test motor directions for desired movement
        robot.motorFrontLeft.setPower(power);
        robot.motorBackRight.setPower(power);
        robot.motorBackLeft.setPower(power);
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

    public void StrafeLeft(double power, long time) throws InterruptedException
    {
        // write the values to the motors
        robot.motorFrontRight.setPower(power);
        robot.motorFrontLeft.setPower(-power);
        robot.motorBackRight.setPower(-power);
        robot.motorBackLeft.setPower(power);
        Thread.sleep(time);
    }

    public void StrafeRight(double power, long time) throws InterruptedException
    {
        StrafeLeft(-power, time);
    }

    public void SpinRight (double power, long time) throws InterruptedException
    {
        // write the values to the motors
        robot.motorFrontRight.setPower(-power);
        robot.motorFrontLeft.setPower(power);
        robot.motorBackRight.setPower(-power);
        robot.motorBackLeft.setPower(power);
        Thread.sleep(time);
    }

    public void SpinLeft (double power, long time) throws InterruptedException
    {
        SpinRight(-power, time);
    }

    public void setDown (double power, long time) throws InterruptedException
    {
        robot.motorLift.setPower(power);
        Thread.sleep(time);
    }

    public void pullOut (double position) throws InterruptedException
    {
        robot.servoRelease.setPosition(position);
    }

    public void spitOut (double position, long time) throws InterruptedException
    {
        robot.servoBucket1.setPosition(position);
        robot.servoBucket2.setPosition(position - 0.5);
        Thread.sleep(time);
    }

/*** Currently no Servo configured in Holonomic Hardware setup

    public void RaiseArm()
    {
        robot.armServo.setPosition(.8); //note: uses servo instead of motor.
    }

    public void LowerArm()
    {
        robot.armServo.setPosition(.2);
    }
*/


}//TestAutoDriveByTime
