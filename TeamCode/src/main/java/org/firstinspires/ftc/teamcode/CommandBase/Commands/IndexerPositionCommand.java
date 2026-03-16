package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.IndexerPositionSelector.LAUNCH;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import java.util.function.BooleanSupplier;

public class IndexerPositionCommand extends CommandBase {
    Robot robot = Robot.getInstance();
    public static Constants.IndexerSubsystem.IndexerPositionSelector indexerPositionSelector = Constants.IndexerSubsystem.IndexerPositionSelector.FIRST;

    @Override
    public void initialize() {
        robot.indexer.setFirstPosition();
    }

    boolean interrupted;

    @Override
    public void execute() {
        switch (indexerPositionSelector) {
            case LAUNCH:
                robot.indexer.setLaunchPosition();
                break;
            case FIRST:
                robot.indexer.setFirstPosition();
                if (!interrupted) {
                    indexerPositionSelector = LAUNCH;
                }
                break;
            case SECOND:
                robot.indexer.setSecondPosition();
                if (!interrupted) {
                    indexerPositionSelector = LAUNCH;
                }
                break;
            case THIRD:
                robot.indexer.setThirdPosition();
                if (!interrupted) {
                    indexerPositionSelector = LAUNCH;
                }

                break;
            case FULL:

                break;
        }
    }

    public void interruptMain(boolean condition) {
        interrupted = condition;
    }









}
