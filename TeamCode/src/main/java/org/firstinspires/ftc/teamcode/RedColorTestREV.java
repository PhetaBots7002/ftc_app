package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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


@TeleOp(name="RedColorTestREV", group ="Test")
//@Disabled
public class RedColorTestREV extends LinearOpMode {

    HardwareSetupMecanum r = new HardwareSetupMecanum(); //get hardware members from HardwareSetUp class


    @Override public void runOpMode()
    {

        r.init(hardwareMap); // get initializatin of hardware from HardwareSetUp class


        waitForStart();

        //Red Side Values B=100  R=200


        while (opModeIsActive())
        {

            telemetry.addData("Red  ", r.colorR.red());
            telemetry.addData("Blue ", r.colorR.blue());

            telemetry.update();

            if (gamepad1.dpad_up)
            {
                r.servoR.setPosition(1);
            }
            if (gamepad1.dpad_left)
            {
                r.servoR.setPosition(0.05);
            }

            if (gamepad1.x)
            {
                telemetry.update();
            }


            idle();
        }//while
    }//runOpMode
}//MyConceptVuforia