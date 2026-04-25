package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.Global.Robot;

public class IntakingCommand extends CommandBase {
    Robot robot = Robot.getInstance();

    @Override
    public void initialize() {
        robot.intake.setIntakeStateIntake();
        robot.indexer.interruptForLaunch(false);
    }

    @Override
    public boolean isFinished() {
        return true;
    }




}
