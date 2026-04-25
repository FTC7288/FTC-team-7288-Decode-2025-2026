package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Global.Robot;

public class UpdateFollower extends CommandBase {
    Robot robot = Robot.getInstance();

    @Override
    public void execute() {
        robot.follower.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }



}
