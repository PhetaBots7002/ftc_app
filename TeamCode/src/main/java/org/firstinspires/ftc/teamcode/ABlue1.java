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


@Autonomous(name="Blue1", group ="Concept")
@Disabled
public class ABlue1 extends LinearOpMode {

    HardwareSetupMecanum r = new HardwareSetupMecanum(); //get hardware members from HardwareSetUp class

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;

    @Override public void runOpMode() throws InterruptedException {

        r.init(hardwareMap); // get initializatin of hardware from HardwareSetUp class


        //Initialize hardware for Viforia
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);




        //License Key for Vuforia
        parameters.vuforiaLicenseKey = "AYvZYCv/////AAAAGcq+NLqQak55ndA8c/UZSNyBVvxcRtiV+muNZrfuXQj38UFKNMDyndfU3J8h95GCDmnCJZf5rEfxbLbdsACtw23yyQAPGiKKHk8GpugjeBT89+Nb5t9vmBn8LgVNPK5SpXcCWP7Ae7Te53Hf3kmwl0STZPFAXU4TGmicC4MUKRcZo26wRGxV4uP9ISgjB8b8N5RkDouhVB6HTG8TC3NUM6AEOPY6W47NImC5Fl/0iCV+6x4+lSwKwqv5uZE2tiYpZpj7UVekeo5NLXhsqAIJqH2Tgb1IoGs5VD2RuPYNuRznIePoNUO5l+jxloy4EFSIyogD4FYOfMwwW/BILD1FuHJ0jEmdZ/hPJUb9iOlp5MBz";




        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;//Camera direction
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);//Vuforia stuff

        /**
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");//Vuforia stuff
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary


        waitForStart();
        double REDTHRESHOLD = 30;//The difference between the blue and red values, set for Red ball 60
        double BLUETHRESHOLD = 20;//The difference between the blue and red values, set for Blue ball 40
        relicTrackables.activate();//Activate the VuMark



        //Extend ColorSensor to read Particles
        r.servoB.setPosition(.95);//Down
        sleep(2000);//Wait for 2 seconds


        telemetry.addLine("Blue Alliance Sensor--");//Add text to driver phone
        telemetry.addData("Red Value:  ", r.colorB.red());//Add text to driver phone
        telemetry.addData("Blue Value: ", r.colorB.blue());//Add text to driver phone
        telemetry.update();//Update text

        if (r.colorB.blue() > r.colorB.red() && r.colorB.blue() >= BLUETHRESHOLD ){
            //do this
            telemetry.addLine("saw BLUE ");//Add text to driver phone
            telemetry.addLine("Red Alliance Sensor--");//Add text to driver phone
            telemetry.addData("Red Value:  ", r.colorB.red());//Add text to driver phone
            telemetry.addData("Blue Value: ", r.colorB.blue());//Add text to driver phone
            telemetry.update();
            SpinRight(.25, 300);
            StopDrivingTime(500);
            r.servoB.setPosition(0);//Servo back into Initialized position
            StopDrivingTime(500);
            SpinLeft(.25, 300);
            StopDrivingTime(500);


            // check for red present greater than Target value


        }
        else if  (r.colorB.red() > r.colorB.blue() && r.colorB.red() >= REDTHRESHOLD )  {
            //else if (r.colorsensor.red() < r.Red && r.colorsensor.red() >r.Red2) {
            //do this
            // display all reading data
            telemetry.addLine("saw RED ");//Add text to driver phone
            telemetry.addLine("Blue Alliance Sensor--");//Add text to driver phone
            telemetry.addData("Red Value:  ", r.colorB.red());//Add text to driver phone
            telemetry.addData("Blue Value: ", r.colorB.blue());//Add text to driver phone
            telemetry.update();
            SpinLeft(.25, 300);//Knock Jewel off
            StopDrivingTime(500);
            r.servoB.setPosition(0);//Servo back into Initialized position
            StopDrivingTime(500);
            SpinRight(.25, 300);//Turn back into straight position
            StopDrivingTime(500);
        }

        else{
            // reading un-reliable so do no harm
            // display all reading data
            telemetry.addLine("OH WELL, MOVE ON");//Add text to driver phone
            telemetry.addLine("Blue Alliance Sensor--");//Add text to driver phone
            telemetry.addData("Red Value:  ", r.colorB.red());//Add text to driver phone
            telemetry.addData("Blue Value: ", r.colorB.blue());//Add text to driver phone
            telemetry.update();
            sleep(1000);//wait for 1 second
            r.servoB.setPosition(0);//Servo back into Initialized position
        }

            sleep(1000);//wait for 1 second



        //A timer to set how long robot reads the image
        ElapsedTime timer = new ElapsedTime();
        timer.reset();


        // vuMark gets value from relicTemplate
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        // if vuMark is NOT UNKNOWN run autoCode for value seen
        while (opModeIsActive()&& vuMark == RelicRecoveryVuMark.UNKNOWN && timer.seconds()<10.0)

        {
            vuMark = RelicRecoveryVuMark.from(relicTemplate); // vuMark gets value from relicTemplate
        }

        //Adds the VuMark text to driver phone, waiting to be changed to RIGHT, LEFT, CENTER, or UNKNOWN
        telemetry.addData("VuMark", "%s visible", vuMark);
        telemetry.update();//Update text




        //If robot reads picture and identifies it as LEFT
        if (vuMark == RelicRecoveryVuMark.LEFT) {
                    // autonomous code here...

                    DriveForwardTime(DRIVE_POWER, 1205);//Drive forward for this amount of seconds
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 1000); //Spin Left for seconds
                    StopDrivingTime(500);

                    DriveForwardTime(DRIVE_POWER, 450);//Drive forward for this amount of seconds
                    StopDrivingTime(500);

                    Drop(0, 800);//Drop glyph
                    StopDrivingTime(1000);

                    DriveForwardTime(-DRIVE_POWER, 250);//Drive Backward for this amount of seconds
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 2000);//Celebration spin
                    StopDrivingTime(500);
                }
        //If robot reads picture and identifies it as CENTER
                else if (vuMark == RelicRecoveryVuMark.CENTER){
                    // autonomous code here...
                    DriveForwardTime(DRIVE_POWER, 1470);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 1000);
                    StopDrivingTime(500);

                    DriveForwardTime(DRIVE_POWER, 450);
                    StopDrivingTime(500);

                    Drop(0, 800);//Drop Glyph
                    StopDrivingTime(1000);

                    DriveForwardTime(-DRIVE_POWER, 250);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 2000);
                    StopDrivingTime(500);
                }
        //If robot reads picture and identifies it as RIGHT
                else if (vuMark == RelicRecoveryVuMark.RIGHT){

                    DriveForwardTime(DRIVE_POWER, 1900);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 1000);
                    StopDrivingTime(500);

                    DriveForwardTime(DRIVE_POWER, 450);
                    StopDrivingTime(500);

                    Drop(0, 800);//Drop Glyph
                    StopDrivingTime(1000);

                    DriveForwardTime(-DRIVE_POWER, 250);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 2000);
                    StopDrivingTime(500);
                }
        //If the picture was NOT read within 10 seconds
                else {
                    DriveForwardTime(DRIVE_POWER, 1500);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 1000);
                    StopDrivingTime(500);

                    DriveForwardTime(DRIVE_POWER, 450);
                    StopDrivingTime(500);

                    Drop(0, 800);//Drop Glyph
                    StopDrivingTime(1000);

                    DriveForwardTime(-DRIVE_POWER, 250);
                    StopDrivingTime(500);

                    SpinLeft(DRIVE_POWER, 2000);
                    StopDrivingTime(500);

        }//Last resort code


    }//runOpMode



    /** Below: Basic Drive Methods used in Autonomous code...**/
    //set Drive Power variable
    double DRIVE_POWER = 1.0;


    //Sets robot mecanum drive to a variable
    public void DriveForward(double power)
    {

        r.motorFrontRight.setPower(power);
        r.motorFrontLeft.setPower(-power);
        r.motorBackRight.setPower(power);
        r.motorBackLeft.setPower(-power);
    }
    //Robot drives forward for a time
    public void DriveForwardTime(double power, long time) throws InterruptedException
    {
        DriveForward(power);
        Thread.sleep(time);
    }

    //Robot quits driving
    public void StopDriving()
    {
        DriveForward(0);
    }

    //Robot stops moving
    public void StopDrivingTime(long time) throws InterruptedException
    {
        DriveForwardTime(0, time);
    }

    //Robot Slides to the left
    public void StrafeLeft(double power, long time) throws InterruptedException
    {

        r.motorFrontRight.setPower(power);
        r.motorFrontLeft.setPower(power);
        r.motorBackRight.setPower(-power);
        r.motorBackLeft.setPower(-power);
        Thread.sleep(time);
    }
    //Robot slides to the right
    public void StrafeRight(double power, long time) throws InterruptedException
    {
        StrafeLeft(-power, time);
    }
    //Robot spins right
    public void SpinRight (double power, long time) throws InterruptedException
    {

        r.motorFrontRight.setPower(-power);
        r.motorFrontLeft.setPower(-power);
        r.motorBackRight.setPower(-power);
        r.motorBackLeft.setPower(-power);
        Thread.sleep(time);
    }
    //Robot spins to the left
    public void SpinLeft (double power, long time) throws InterruptedException
    {
        SpinRight(-power, time);
    }

    //Drops Glyph
    public void Drop (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.1);
    }
    //Grabs Glyph
    public void Grab (double power, long time) throws InterruptedException
    {
        r.servoClamp.setPosition(0.3);
    }
    //Servo on Red Alliance side waits
    public void ServoRWait (double power, long time) throws InterruptedException
    {
        r.servoR.setPosition(power);
    }


}//MyConceptVuforia