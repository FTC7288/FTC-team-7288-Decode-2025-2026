package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;


import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.*;
import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.IndexerPositionSelector.*;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Indexer extends SubsystemBase {
    Robot robot = Robot.getInstance();
    private IndexerPositionSelector indexerPositionSelector = IndexerPositionSelector.FIRST;
    private boolean interruptedForLaunch = false;


    public void setLaunchPosition() {
        robot.indexerServo.setPosition(Constants.IndexerSubsystem.LAUNCH_POSITION);
    }

    public void setFirstPosition() {
        robot.indexerServo.setPosition(Constants.IndexerSubsystem.FIRST_POSITION);
    }

    public void setSecondPosition() {
        robot.indexerServo.setPosition(Constants.IndexerSubsystem.SECOND_POSITION);
    }

    public void setThirdPosition() {
        robot.indexerServo.setPosition(Constants.IndexerSubsystem.THIRD_POSITION);
    }



    public boolean isBeamBroken() {
        return !robot.breakBeam.getState();
    }

    public boolean isIndexerAtPosition(double position) {
        return Range.isBetween(getIndexerPosition(), position - 0.01, position + 0.01);
    }

    public boolean isFull() {
        return indexerPositionSelector.equals(IndexerPositionSelector.FULL);
    }

    public double getIndexerPosition() {
        return robot.indexerAnalog.getVoltage();
    }

    public void interruptForLaunch(boolean state) {
        interruptedForLaunch = state;
    }


    // TODO: make sure to fix issue where it skips states when ball get jammed in the indexer to prevent backlogs
    @Override
    public void periodic() {
        switch (indexerPositionSelector) {
            case LAUNCH:
                if (!interruptedForLaunch) {
                    indexerPositionSelector = FIRST;
                }
                if (robot.intake.isIntakeIn()) {
                    setLaunchPosition();
                }
                break;
            case FIRST:
                setFirstPosition();
                if (interruptedForLaunch) {
                    indexerPositionSelector = LAUNCH;
                }
                if (isBeamBroken() && isIndexerAtPosition(FIRST_ANALOG_POSITION)) {
                    indexerPositionSelector = SECOND;
                }
                break;

            case SECOND:
                setSecondPosition();
                if (interruptedForLaunch) {
                    indexerPositionSelector = LAUNCH;
                }
                if (isBeamBroken() && isIndexerAtPosition(SECOND_ANALOG_POSITION)) {
                    indexerPositionSelector = THIRD;
                }
                break;

            case THIRD:
                setThirdPosition();
                if (interruptedForLaunch) {
                    indexerPositionSelector = LAUNCH;
                }
                if (isBeamBroken() && isIndexerAtPosition(THIRD_ANALOG_POSITION)) {
                    indexerPositionSelector = FULL;
                }
                break;
            case FULL:
                if (interruptedForLaunch) {
                    indexerPositionSelector = LAUNCH;
                }
                break;
        }
    }



}
