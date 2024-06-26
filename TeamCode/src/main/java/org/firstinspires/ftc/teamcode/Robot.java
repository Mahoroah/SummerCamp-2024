package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Robot {
    //28 Ticks / Revolution (* 40 For Gear Ratio)
    private static final double TICKS_PER_REV = 28 * 40;
    private static final double CIRCUMFERENCE = 4 * Math.PI;
    private static final double TICKS_PER_INCH = TICKS_PER_REV / CIRCUMFERENCE;

    public final DcMotor LDrive;
    public final DcMotor RDrive;
    public final IMU imu;

    public Robot(HardwareMap hardwareMap) {
        LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        )));

        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void driveDistance(double distance, double power) {
        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double target = distance * TICKS_PER_REV;

        LDrive.setTargetPosition((int) target);
        RDrive.setTargetPosition((int) target);

        while (LDrive.isBusy() && RDrive.isBusy()) {
            LDrive.setPower(power);
            RDrive.setPower(power);
        }

        LDrive.setPower(0);
        RDrive.setPower(0);

    }

    private static double error = 10;

    private double getYaw() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    private double wrapAngle(double x) {
        x += 180;
        x = (x % 360 + 360) % 360;
        x -= 180;
        return x;
    }

    public void turnLeft(double degrees, double speed) {
        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu.resetYaw(); // imu yaw is now 0

        double yawError;

        while (Math.abs(yawError = wrapAngle(getYaw() - degrees)) > 10) {
            if (yawError > 0) {
                LDrive.setPower(speed);
                RDrive.setPower(-speed);
            } else {
                LDrive.setPower(-speed);
                RDrive.setPower(speed);
            }
        }

        LDrive.setPower(0.0);
        RDrive.setPower(0.0);
    }

    public void turnRight(double degrees, double speed) {
        turnLeft(-degrees, speed);
    }
}
