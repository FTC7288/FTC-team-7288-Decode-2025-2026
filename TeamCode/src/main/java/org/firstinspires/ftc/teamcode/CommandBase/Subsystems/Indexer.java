package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;


import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.*;
import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.IndexerPositionSelector.*;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Indexer extends SubsystemBase {
    Robot robot = Robot.getInstance();
    public IndexerPositionSelector indexerPositionSelector = IndexerPositionSelector.FIRST;
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
        double compensation = (robot.intake.isIntakeOut())? 0.15 : .0;
        double currentPosition = getIndexerPosition() - compensation;
        return Range.isBetween(currentPosition, position - 0.05, position + 0.05);
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
    // It might be caused by the servo getting the correct voltage but not being at the right position at the time
    @Override
    public void periodic() {
        if (interruptedForLaunch) indexerPositionSelector = LAUNCH;
        switch (indexerPositionSelector) {
            case LAUNCH:
                if(robot.intake.isIntakeIn()) {
                    setLaunchPosition();
                }
                if (!interruptedForLaunch) {
                    indexerPositionSelector = FIRST;
                }
                break;
            case FIRST:
                setFirstPosition();
                if (isIndexerAtPosition(FIRST_ANALOG_POSITION)) {
                    if (isBeamBroken()) {
                        indexerPositionSelector = FIRST_TEMP;
                    }
                }
                break;
            case FIRST_TEMP:
                setSecondPosition();
                if (!isBeamBroken()) {
                    indexerPositionSelector = SECOND;
                }
                break;
            case SECOND:
                if (isIndexerAtPosition(SECOND_ANALOG_POSITION)) {
                    if (isBeamBroken()) {
                        indexerPositionSelector = SECOND_TEMP;
                    }
                }
                break;
            case SECOND_TEMP:
                setThirdPosition();
                if (!isBeamBroken()) {
                    indexerPositionSelector = THIRD;
                }
                break;
            case THIRD:
                if (isIndexerAtPosition(THIRD_ANALOG_POSITION)) {
                    if (isBeamBroken()) {
                        indexerPositionSelector = FULL;
                    }
                }
                break;
            case FULL:
                break;
        }
    }



}
