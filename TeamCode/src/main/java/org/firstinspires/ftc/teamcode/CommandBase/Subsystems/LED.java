package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Global.Robot;

public class LED extends SubsystemBase {
    Robot robot = Robot.getInstance();

    // TODO: finish adding the led implementation so Rylee can see what the robot is doing


    public LED() {

    }

    @Override
    public void periodic() {
        robot.ledServo.setPosition(0.5);
    }



}
