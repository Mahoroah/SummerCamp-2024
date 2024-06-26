package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoBase")
public class AutoTemplate extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.driveDistance(10, 0.5);

        robot.turnRight(90, 0.5);
    }
}
