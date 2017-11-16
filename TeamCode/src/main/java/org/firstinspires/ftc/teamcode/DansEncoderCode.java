package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Concept code from FUSION by Dan
 * for using encoders with Holonomic drive
 */

@TeleOp(name = "MecanumTeleOp", group = "Drive")
@Disabled
public abstract class DansEncoderCode extends LinearOpMode{

    HardwareSetupMecanum r = new HardwareSetupMecanum();

    double leftDirAdj;
    double rightDirAdj;

    final double ticksPerInch  = 30;  //188;  //tick of the encoder * gear ratio / circumference of the wheel
    final  int   tickOverRun   = 80;   //number of tick robot overruns target after stop
    final double inchesPerDeg  = .142;  //wheel base of robot * pi / 360
    final double tickPerDeg    = ticksPerInch * inchesPerDeg;

    ElapsedTime movementTime   = new ElapsedTime();


    public enum driveDirections{
        FORWARD, BACKWARD
    }
    public enum turnDirections{
        LEFT, RIGHT
    }
    public enum strafeDirections{
        LEFT, RIGHT
    }

    public void driveTime (double iSpeed, int iTime, driveDirections iDir ) {
//        r.init(hardwareMap);

        if(iDir == driveDirections.BACKWARD){
            leftDirAdj  = -1.0;
            rightDirAdj = -1.0;
        }
        else {
            leftDirAdj  = 1.0;
            rightDirAdj = 1.0;
        }

        r.motorFrontRight.setPower(iSpeed*rightDirAdj);
        r.motorBackRight.setPower(iSpeed*rightDirAdj);
        r.motorFrontLeft.setPower(iSpeed*leftDirAdj);
        r.motorBackLeft.setPower(iSpeed*leftDirAdj);

        movementTime.reset();

        while (opModeIsActive() && movementTime.milliseconds() < iTime) {
        }

        r.motorFrontRight.setPower(0.0);
        r.motorBackRight.setPower(0.0);
        r.motorFrontLeft.setPower(0.0);
        r.motorBackLeft.setPower(0.0);

    }

    public void driveEnc (double iSpeed, int iDist, driveDirections iDir) {
        double vSpeed = iSpeed;

        int leftTargetFront;
        int leftTargetBack;
        int rightTargetFront;
        int rightTargetBack;

        int leftStartFront = 0;
        int leftStartBack = 0;
        int rightStartFront = 0;
        int rightStartBack = 0;

        int leftFinalFront = 0;
        int leftFinalBack = 0;
        int rightFinalFront = 0;
        int rightFinalBack = 0;

        int leftAdjTargetFront;
        int leftAdjTargetBack;
        int rightAdjTargetFront;
        int rightAdjTargetBack;

        if (iDir == driveDirections.FORWARD) {
            leftDirAdj = 1.0;
            rightDirAdj = 1.0;
        } else {
            leftDirAdj = -1.0;
            rightDirAdj = -1.0;
            vSpeed = vSpeed * -1;
        }

        leftStartFront = r.motorFrontLeft.getCurrentPosition();
        leftStartBack = r.motorBackLeft.getCurrentPosition();
        rightStartFront = r.motorFrontRight.getCurrentPosition();
        rightStartBack = r.motorBackRight.getCurrentPosition();

        leftTargetFront = leftStartFront + (int) (iDist * ticksPerInch * leftDirAdj);
        leftTargetBack = leftStartBack + (int) (iDist * ticksPerInch * leftDirAdj);
        rightTargetFront = rightStartFront + (int) (iDist * ticksPerInch * rightDirAdj);
        rightTargetBack = rightStartBack + (int) (iDist * ticksPerInch * rightDirAdj);

        leftAdjTargetFront  = leftTargetFront  - (int)(tickOverRun * leftDirAdj);
        leftAdjTargetBack   = leftTargetBack   - (int)(tickOverRun * leftDirAdj);
        rightAdjTargetFront = rightTargetFront - (int)(tickOverRun * rightDirAdj);
        rightAdjTargetBack  = rightTargetBack  - (int)(tickOverRun * rightDirAdj);

        telemetry.addData("leftFront", Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront) + "; " + Integer.toString(leftFinalFront));
        telemetry.addData("leftBack", Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack) + "; " + Integer.toString(leftFinalBack));
        telemetry.addData("rightFront", Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront) + "; " + Integer.toString(rightFinalFront));
        telemetry.addData("rightBack", Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack) + "; " + Integer.toString(rightFinalBack));

        telemetry.update();

        r.motorBackRight.setPower(vSpeed);
        r.motorFrontRight.setPower(vSpeed);
        r.motorFrontLeft.setPower(vSpeed);
        r.motorBackLeft.setPower(vSpeed);

        if (iDir == driveDirections.FORWARD) {

            while (opModeIsActive() &&
                    r.motorBackLeft.getCurrentPosition() < leftAdjTargetFront &&
                    r.motorFrontLeft.getCurrentPosition() < leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() < rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() < rightAdjTargetBack ) {
            }
        }
        else {
            while (opModeIsActive() &&
                    r.motorFrontLeft.getCurrentPosition() > leftAdjTargetFront &&
                    r.motorBackLeft.getCurrentPosition() > leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() > rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() > rightAdjTargetBack ) {
            }
        }

        r.motorBackRight.setPower(0.0);
        r.motorFrontRight.setPower(0.0);
        r.motorBackLeft.setPower(0.0);
        r.motorFrontLeft.setPower(0.0);

        leftFinalFront = r.motorFrontLeft.getCurrentPosition();
        leftFinalBack = r.motorBackLeft.getCurrentPosition();
        rightFinalFront = r.motorFrontRight.getCurrentPosition();
        rightFinalBack = r.motorBackRight.getCurrentPosition();

        telemetry.addData("leftFront",Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront)+ "; " + Integer.toString(leftFinalFront)+ "; " + Integer.toString(leftAdjTargetFront));
        telemetry.addData("leftBack",Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack)+ "; " + Integer.toString(leftFinalBack)+ "; " + Integer.toString(leftAdjTargetBack));
        telemetry.addData("rightFront",Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront)+ "; " + Integer.toString(rightFinalFront) + "; " + Integer.toString(rightAdjTargetFront));
        telemetry.addData("rightBack",Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack)+ "; " + Integer.toString(rightFinalBack) + "; " + Integer.toString(rightAdjTargetBack));

        telemetry.update();
    }

    public void strafeEnc (double iSpeed, int iDist, strafeDirections iDir) {

        double vSpeed = iSpeed;

        int leftTargetFront;
        int leftTargetBack;
        int rightTargetFront;
        int rightTargetBack;

        int leftStartFront = 0;
        int leftStartBack = 0;
        int rightStartFront = 0;
        int rightStartBack = 0;

        int leftFinalFront = 0;
        int leftFinalBack = 0;
        int rightFinalFront = 0;
        int rightFinalBack = 0;

        int leftAdjTargetFront;
        int leftAdjTargetBack;
        int rightAdjTargetFront;
        int rightAdjTargetBack;

        leftStartFront = r.motorFrontLeft.getCurrentPosition();
        leftStartBack = r.motorBackLeft.getCurrentPosition();
        rightStartFront = r.motorFrontRight.getCurrentPosition();
        rightStartBack = r.motorBackRight.getCurrentPosition();

        leftTargetFront = leftStartFront + (int) (iDist * ticksPerInch * leftDirAdj);
        leftTargetBack = leftStartBack + (int) (iDist * ticksPerInch * leftDirAdj);
        rightTargetFront = rightStartFront + (int) (iDist * ticksPerInch * rightDirAdj);
        rightTargetBack = rightStartBack + (int) (iDist * ticksPerInch * rightDirAdj);

        leftAdjTargetFront = leftTargetFront - (int) (tickOverRun * leftDirAdj);
        leftAdjTargetBack = leftTargetBack - (int) (tickOverRun * leftDirAdj);
        rightAdjTargetFront = rightTargetFront - (int) (tickOverRun * rightDirAdj);
        rightAdjTargetBack = rightTargetBack - (int) (tickOverRun * rightDirAdj);

        telemetry.addData("leftFront", Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront) + "; " + Integer.toString(leftFinalFront));
        telemetry.addData("leftBack", Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack) + "; " + Integer.toString(leftFinalBack));
        telemetry.addData("rightFront", Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront) + "; " + Integer.toString(rightFinalFront));
        telemetry.addData("rightBack", Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack) + "; " + Integer.toString(rightFinalBack));

        telemetry.update();

        if (iDir == strafeDirections.RIGHT){
            r.motorFrontRight.setPower(iSpeed * -1.0);
            r.motorBackRight.setPower(iSpeed * 1.0);
            r.motorBackLeft.setPower(iSpeed * -1.0);
            r.motorFrontLeft.setPower(iSpeed * 1.0);
        }
        else {
            r.motorFrontRight.setPower(iSpeed * 1.0);
            r.motorBackRight.setPower(iSpeed * -1.0);
            r.motorBackLeft.setPower(iSpeed * 1.0);
            r.motorFrontLeft.setPower(iSpeed * -1.0);
        }



        if ((iDir == strafeDirections.RIGHT)) {

            while (opModeIsActive() &&
                    r.motorFrontLeft.getCurrentPosition() < leftAdjTargetFront &&
                    r.motorBackLeft.getCurrentPosition() < leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() > rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() > rightAdjTargetBack) {
            }
        } else {
            while (opModeIsActive() &&
                    r.motorFrontLeft.getCurrentPosition() > leftAdjTargetFront &&
                    r.motorBackLeft.getCurrentPosition() > leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() < rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() < rightAdjTargetBack) {
            }
        }

        r.motorBackRight.setPower(0.0);
        r.motorFrontRight.setPower(0.0);
        r.motorBackLeft.setPower(0.0);
        r.motorFrontLeft.setPower(0.0);

        leftFinalFront = r.motorFrontLeft.getCurrentPosition();
        leftFinalBack = r.motorBackLeft.getCurrentPosition();
        rightFinalFront = r.motorFrontRight.getCurrentPosition();
        rightFinalBack = r.motorBackRight.getCurrentPosition();

        telemetry.addData("Done leftFront", Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront) + "; " + Integer.toString(leftFinalFront));
        telemetry.addData("leftBack", Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack) + "; " + Integer.toString(leftFinalBack));
        telemetry.addData("rightFront", Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront) + "; " + Integer.toString(rightFinalFront));
        telemetry.addData("rightBack", Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack) + "; " + Integer.toString(rightFinalBack));

        telemetry.update();

    }

    public void spinEnc (double iSpeed, int iDist, turnDirections iDir) {

        double vSpeed = iSpeed;

        int leftTargetFront;
        int leftTargetBack;
        int rightTargetFront;
        int rightTargetBack;

        int leftStartFront = 0;
        int leftStartBack = 0;
        int rightStartFront = 0;
        int rightStartBack = 0;

        int leftFinalFront = 0;
        int leftFinalBack = 0;
        int rightFinalFront = 0;
        int rightFinalBack = 0;

        int leftAdjTargetFront;
        int leftAdjTargetBack;
        int rightAdjTargetFront;
        int rightAdjTargetBack;

        if (iDir == turnDirections.RIGHT) {
            leftDirAdj = 1.0;
            rightDirAdj = -1.0;
        } else {
            leftDirAdj = -1.0;
            rightDirAdj = 1.0;
        }

        leftStartFront = r.motorFrontLeft.getCurrentPosition();
        leftStartBack = r.motorBackLeft.getCurrentPosition();
        rightStartFront = r.motorFrontRight.getCurrentPosition();
        rightStartBack = r.motorBackRight.getCurrentPosition();

        leftTargetFront = leftStartFront + (int) (iDist * tickPerDeg * leftDirAdj);
        leftTargetBack = leftStartBack + (int) (iDist * tickPerDeg * leftDirAdj);
        rightTargetFront = rightStartFront + (int) (iDist * tickPerDeg * rightDirAdj);
        rightTargetBack = rightStartBack + (int) (iDist * tickPerDeg * rightDirAdj);

        leftAdjTargetFront = leftTargetFront - (int) (tickOverRun * leftDirAdj);
        leftAdjTargetBack = leftTargetBack - (int) (tickOverRun * leftDirAdj);
        rightAdjTargetFront = rightTargetFront - (int) (tickOverRun * rightDirAdj);
        rightAdjTargetBack = rightTargetBack - (int) (tickOverRun * rightDirAdj);

        telemetry.addData("leftFront", Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront) + "; " + Integer.toString(leftFinalFront));
        telemetry.addData("leftBack", Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack) + "; " + Integer.toString(leftFinalBack));
        telemetry.addData("rightFront", Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront) + "; " + Integer.toString(rightFinalFront));
        telemetry.addData("rightBack", Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack) + "; " + Integer.toString(rightFinalBack));

        telemetry.update();

        r.motorBackRight.setPower(vSpeed * rightDirAdj);
        r.motorFrontRight.setPower(vSpeed * rightDirAdj);
        r.motorFrontLeft.setPower(vSpeed * leftDirAdj);
        r.motorBackLeft.setPower(vSpeed * leftDirAdj);

        if ((iDir == turnDirections.RIGHT)) {

            while (opModeIsActive() &&
                    r.motorFrontLeft.getCurrentPosition() < leftAdjTargetFront &&
                    r.motorBackLeft.getCurrentPosition() < leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() > rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() > rightAdjTargetBack) {
            }
        } else {
            while (opModeIsActive() &&
                    r.motorFrontLeft.getCurrentPosition() < leftAdjTargetFront &&
                    r.motorBackLeft.getCurrentPosition() < leftAdjTargetBack &&
                    r.motorFrontRight.getCurrentPosition() > rightAdjTargetFront &&
                    r.motorBackRight.getCurrentPosition() > rightAdjTargetBack) {
            }
        }

        r.motorBackRight.setPower(0.0);
        r.motorFrontRight.setPower(0.0);
        r.motorBackLeft.setPower(0.0);
        r.motorFrontLeft.setPower(0.0);

        leftFinalFront = r.motorFrontLeft.getCurrentPosition();
        leftFinalBack = r.motorBackLeft.getCurrentPosition();
        rightFinalFront = r.motorFrontRight.getCurrentPosition();
        rightFinalBack = r.motorBackRight.getCurrentPosition();

        telemetry.addData("Done leftFront", Integer.toString(leftStartFront) + "; " + Integer.toString(leftTargetFront) + "; " + Integer.toString(leftFinalFront));
        telemetry.addData("leftBack", Integer.toString(leftStartBack) + "; " + Integer.toString(leftTargetBack) + "; " + Integer.toString(leftFinalBack));
        telemetry.addData("rightFront", Integer.toString(rightStartFront) + "; " + Integer.toString(rightTargetFront) + "; " + Integer.toString(rightFinalFront));
        telemetry.addData("rightBack", Integer.toString(rightStartBack) + "; " + Integer.toString(rightTargetBack) + "; " + Integer.toString(rightFinalBack));

        telemetry.update();

    }


}