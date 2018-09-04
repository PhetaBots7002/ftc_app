package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by user on 7/12/2018.
 */
@TeleOp (name="CaptainHOOK", group ="Summer")
//@Disabled
public class captainHOOKTeleOp extends OpMode {
   captainHookHardware robot=new captainHookHardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }


    @Override
    public void loop() {
        if (robot.disabled){
            robot.leftDrive.setPower(0.0);
            robot.rightDrive.setPower(0.0);
            robot.arm.setPower(0.0);
        } else {
           robot.disabled=!robot.killswitch.getState();
            robot.leftDrive.setPower(-gamepad1.left_stick_y - 0.1);
            robot.rightDrive.setPower(-gamepad1.right_stick_y - 0.1);
            robot.arm.setPower(-gamepad2.right_stick_y/2);

//            if(gamepad2.y){
//                robot.arm.setPower(0.4);
//            } else if(gamepad2.a){
//
//                robot.arm.setPower(-0.4);
//            } else {
//                robot.arm.setPower(0.0);
//            }
        }
    }
}