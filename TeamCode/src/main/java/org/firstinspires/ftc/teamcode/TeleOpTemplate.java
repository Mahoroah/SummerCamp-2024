package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name = "TeleOpTemplate")
public class TeleOpTemplate extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        DcMotor RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while(opModeIsActive()) {
            double x = -gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;

            double LPower = y + x;
            double RPower = y - x;

            LDrive.setPower(LPower);
            RDrive.setPower(-RPower);

            telemetry.addData("LPower", LPower);
            telemetry.addData("RPower", RPower);
            telemetry.update();
        }

        LDrive.setPower(0);
        RDrive.setPower(0);

    }
}
