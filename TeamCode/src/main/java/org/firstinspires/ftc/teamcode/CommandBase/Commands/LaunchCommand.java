package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Global.Robot;

public class LaunchCommand extends CommandBase {
    Robot robot = Robot.getInstance();

    @Override
    public void execute() {
        robot.intake.setIntakeStateTransfer();
        robot.indexer.interruptForLaunch(true);
    }


    public boolean isFinished() {
        return true;
    }



}
