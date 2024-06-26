package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.checkerframework.checker.units.qual.A;

@Autonomous(name="AutoBase")
public class AutoTemplate extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            robot.DriveDistance(1, 5);

        }
    }
}
