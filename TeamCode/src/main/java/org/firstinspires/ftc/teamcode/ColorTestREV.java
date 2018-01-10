package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name="ColorTestREV", group ="Test")
//@Disabled
public class ColorTestREV extends LinearOpMode {

    HardwareSetupMecanum r = new HardwareSetupMecanum(); //get hardware members from HardwareSetUp class


    @Override public void runOpMode() throws InterruptedException {

        r.init(hardwareMap); // get initializatin of hardware from HardwareSetUp class


        waitForStart();
        telemetry.addData("Red  ", r.colorR.red());
        telemetry.addData("Blue ", r.colorB.blue());

        telemetry.update();



        //Extend ColorSensor to read Particles
        r.servoB.setPosition(.9);//Down

        while (r.servoB.getPosition()== 0.9);
        {

            telemetry.addData("Red  ", r.colorR.red());
            telemetry.addData("Blue ", r.colorB.blue());

            telemetry.update();
         /*
            if (r.colorB.blue() > r.Blue) {
                //do this
                telemetry.update();

                SpinRight(.25, 300);
                StopDrivingTime(500);

                SpinLeft(.25, 300);
                StopDrivingTime(500);


                r.servoB.setPosition(0);//Up

                // check for red present greater than Target value


            } else if (r.colorR.red() > r.Red) {
                //else if (r.colorsensor.red() < r.Red && r.colorsensor.red() >r.Red2) {
                //do this
                telemetry.update();

                SpinLeft(.25, 300);
                StopDrivingTime(500);

                SpinRight(.25, 300);
                StopDrivingTime(500);

                r.servoB.setPosition(0);//Up
            } else {
                telemetry.addData("Color", "NOT VISIBLE"); // else if color IS UNKNOWN display NOT VISABLE
                telemetry.update();
            }
        */

        }//while

    }//runOpMode



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



}//MyConceptVuforia