package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class InitializeAutoCommand extends CommandBase {
    Robot robot = Robot.getInstance();

    @Override
    public void initialize() {
        new ParallelCommandGroup(
                new InstantCommand(() -> robot.flywheel.turnFlywheelOn()),
                new InstantCommand(() -> robot.indexer.indexerPositionSelector = Constants.IndexerSubsystem.IndexerPositionSelector.THIRD),
                new InstantCommand(() -> robot.intake.setIntakeStateNeutral()));
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
