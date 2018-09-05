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

package org.firstinspires.ftc.teamcode.LaserSharks;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.LaserSharks.SharkHardWare;

@Autonomous(name="SharkAutoTime", group="MasterPro")
//@Disabled
public class SharkHolonomicAutoDriveByTime extends LinearOpMode {

    SharkHardWare shark = new SharkHardWare();

    @Override
    public void runOpMode() throws InterruptedException {

            shark.init(hardwareMap);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        /************************
         * Autonomous Code Below://
         *************************/

        DriveForwardTime(DRIVE_POWER, 1150);
        StopDrivingTime(1000);

       // SpinRight(DRIVE_POWER, 1000);
       // StopDrivingTime(1000);

        StopDriving();

    } //runOpMode


/** Below: Basic Drive Methods used in Autonomous code...**/

    //set Drive Power variable
    double DRIVE_POWER = 0.75;

    public void DriveForward(double power)
    {
        // write the values to the motors
        shark.motorFrontRight.setPower(power);//still need to test motor directions for desired movement
        shark.motorFrontLeft.setPower(power);
        shark.motorBackRight.setPower(power);
        shark.motorBackLeft.setPower(power);
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
        shark.motorFrontRight.setPower(power);
        shark.motorFrontLeft.setPower(-power);
        shark.motorBackRight.setPower(-power);
        shark.motorBackLeft.setPower(power);
        Thread.sleep(time);
    }

    public void StrafeRight(double power, long time) throws InterruptedException
    {
        StrafeLeft(-power, time);
    }

    public void SpinRight (double power, long time) throws InterruptedException
    {
        // write the values to the motors
        shark.motorFrontRight.setPower(-power);
        shark.motorFrontLeft.setPower(power);
        shark.motorBackRight.setPower(-power);
        shark.motorBackLeft.setPower(power);
        Thread.sleep(time);
    }

    public void SpinLeft (double power, long time) throws InterruptedException
    {
        SpinRight(-power, time);
    }

    public void RaiseArm (double power, long time) throws InterruptedException
    {
        shark.motorLift.setPower(power);
      Thread.sleep(time);
    }


//open/close claw
    public void OpenClaw()
    {
        shark.servoR.setPosition(.8);
        shark.servoL.setPosition(.2);
    }

    public void CloseClaw()
    {
        shark.servoR.setPosition(.2);
        shark.servoL.setPosition(.8);
    }



}//TestAutoDriveByTime

