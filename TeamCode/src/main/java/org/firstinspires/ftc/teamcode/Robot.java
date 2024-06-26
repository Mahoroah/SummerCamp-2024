package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.time.LocalDate;

public class Robot extends LinearOpMode {

    //28 Ticks / Revolution (* 40 For Gear Ratio)
    private final double TicksPerRev = 28 * 40;

    private final double Circumference = 4 * 3.14159;

    DcMotor LDrive;
    DcMotor RDrive;

    public Robot(HardwareMap hardwareMap) {
        LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }



    public void DriveDistance(double Power, double Distance) {
        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double target = (Distance * Circumference) / TicksPerRev;


        LDrive.setTargetPosition((int) target);
        RDrive.setTargetPosition((int) target);
        while(LDrive.isBusy() && RDrive.isBusy()) {
            LDrive.setPower(Power);
            RDrive.setPower(Power);
        }


        LDrive.setPower(0);
        RDrive.setPower(0);

    }


    @Override
    public void runOpMode() throws InterruptedException {

    }
}
