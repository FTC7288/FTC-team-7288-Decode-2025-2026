package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class TurnOffRobotCommand extends CommandBase {
    Robot robot = Robot.getInstance();

    @Override
    public void initialize() {
        robot.indexer.indexerPositionSelector = Constants.IndexerSubsystem.IndexerPositionSelector.FIRST;
        robot.intake.setIntakeStateNeutral();
        robot.flywheel.turnFlywheelOff();
        robot.turret.turnTurretOff();
    }

    @Override
    public boolean isFinished() {
        return true;
    }



}
