package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by user on 7/12/2018.
 */

public class captainHookHardware {
    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;
    DigitalChannel killswitch;

    boolean disabled;
    public void init(HardwareMap hw){
        leftDrive = hw.dcMotor.get("ld");
        rightDrive = hw.dcMotor.get("rd");
        arm = hw.dcMotor.get("arm");

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        killswitch=hw.digitalChannel.get("ks");
        killswitch.setMode(DigitalChannel.Mode.INPUT);

        disabled=false;
    }
}
