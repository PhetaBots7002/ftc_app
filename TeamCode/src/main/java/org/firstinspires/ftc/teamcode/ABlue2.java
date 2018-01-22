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

/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

@Autonomous(name="Blue2", group ="Concept")
//@Disabled
public class ABlue2 extends LinearOpMode {

    HardwareSetupMecanum r = new HardwareSetupMecanum(); //get hardware members from HardwareSetUp class

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    @Override public void runOpMode() throws InterruptedException {

        r.init(hardwareMap); // get initializatin of hardware from HardwareSetUp class

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * licence key for rreynolds
         */
        parameters.vuforiaLicenseKey = "AYvZYCv/////AAAAGcq+NLqQak55ndA8c/UZSNyBVvxcRtiV+muNZrfuXQj38UFKNMDyndfU3J8h95GCDmnCJZf5rEfxbLbdsACtw23yyQAPGiKKHk8GpugjeBT89+Nb5t9vmBn8LgVNPK5SpXcCWP7Ae7Te53Hf3kmwl0STZPFAXU4TGmicC4MUKRcZo26wRGxV4uP9ISgjB8b8N5RkDouhVB6HTG8TC3NUM6AEOPY6W47NImC5Fl/0iCV+6x4+lSwKwqv5uZE2tiYpZpj7UVekeo5NLXhsqAIJqH2Tgb1IoGs5VD2RuPYNuRznIePoNUO5l+jxloy4EFSIyogD4FYOfMwwW/BILD1FuHJ0jEmdZ/hPJUb9iOlp5MBz";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary


        waitForStart();

        relicTrackables.activate();


        //Extend ColorSensor to read Particles
        r.servoB.setPosition(.95);//Down



        telemetry.addData("Red  ", r.colorR.red());
        telemetry.addData("Blue ", r.colorB.blue());

        telemetry.update();

        if (r.colorB.blue() > r.Blue) {
            //do this

            SpinRight(.25, 300);
            StopDrivingTime(500);

            SpinLeft(.25, 300);
            StopDrivingTime(500);


            // check for red present greater than Target value


        }
        else if (r.colorR.red() > r.Red){
            //else if (r.colorsensor.red() < r.Red && r.colorsensor.red() >r.Red2) {
            //do this

            SpinLeft(.25, 300);
            StopDrivingTime(500);

            SpinRight(.25, 300);
            StopDrivingTime(500);
        }


        else {
            telemetry.addData("Red  ", r.colorR.red());
            telemetry.addData("Blue ", r.colorB.blue());
            telemetry.addData("Color", "NOT VISIBLE"); // else if color IS UNKNOWN display NOT VISABLE
            telemetry.update();
        }




        r.servoB.setPosition(0);//Up
            /*
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned, else 'NOT VISIBLE' will display
             */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate); // vuMark gets value from relicTemplate
        while (opModeIsActive()&& vuMark == RelicRecoveryVuMark.UNKNOWN && timer.seconds()<5.0) // if vuMark is NOT UNKNOWN run autoCode for value seen
        {
            vuMark = RelicRecoveryVuMark.from(relicTemplate); // vuMark gets value from relicTemplate
        }
        telemetry.addData("Red  ", r.colorR.red());
        telemetry.addData("Blue ", r.colorB.blue());

        telemetry.addData("VuMark", "%s visible", vuMark);
        telemetry.update();

        // This simple example code runs a different motor for 1 sec then turns it off
        // for each TemplateID found.
        // the run motor test can be replaced by your desired autonomous code.

        if (vuMark == RelicRecoveryVuMark.LEFT) {
            // autonomous code here...
            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            StrafeRight(DRIVE_POWER, 400); //neg power drives backwards
            StopDrivingTime(500);

            DriveForwardTime(DRIVE_POWER, 500);
            StopDrivingTime(500);

            Drop(0.2, 500);
            StopDrivingTime(1000);

            DriveForwardTime(-DRIVE_POWER, 100);

            StopDriving();

        }
        else if (vuMark == RelicRecoveryVuMark.CENTER){
            // autonomous code here...
            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            StrafeRight(DRIVE_POWER, 850); //neg power drives backwards
            StopDrivingTime(500);

            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            Drop(0.2, 500);
            StopDrivingTime(1000);

            DriveForwardTime(-DRIVE_POWER, 100);

            StopDriving();

        }
        else if (vuMark == RelicRecoveryVuMark.RIGHT){
            // autonomous code here...StrafeRight(DRIVE_POWER, 500);
            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            StrafeRight(DRIVE_POWER, 1250); //neg power drives backwards
            StopDrivingTime(500);

            DriveForwardTime(DRIVE_POWER, 500);
            StopDrivingTime(500);

            Drop(0.2, 500);
            StopDrivingTime(1000);

            DriveForwardTime(-DRIVE_POWER, 100);

            StopDriving();
        }
        else {
            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            StrafeRight(DRIVE_POWER, 850); //neg power drives backwards
            StopDrivingTime(500);

            DriveForwardTime(DRIVE_POWER, 800);
            StopDrivingTime(500);

            Drop(0.2, 500);
            StopDrivingTime(1000);

            DriveForwardTime(-DRIVE_POWER, 100);

            StopDriving();
        }

        // *** need to figure out how to end opModeIsActive once code has been run

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

    //Drops or grabs Glyph
    public void Drop (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.1);
    }
    public void Grab (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.3);
    }



}//MyConceptVuforia