package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Global.Robot;

@TeleOp
public class flywheelthingamabob extends OpMode {
    Robot robot = Robot.getInstance();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.flywheelTopMotor.set(0.75);
        robot.flywheelBottomMotor.set(0.75);
    }
}
