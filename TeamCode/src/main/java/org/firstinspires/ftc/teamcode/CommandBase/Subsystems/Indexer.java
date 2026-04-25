package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;


import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.*;
import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.IndexerPositionSelector.*;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import Util.Range;

public class Indexer extends SubsystemBase {
    Robot robot = Robot.getInstance();


    public IndexerPositionSelector indexerPositionSelector;
    private boolean interruptedForLaunch = false;

    public Indexer () {

        if (Constants.HardwareInitialization.AUTO) {
            indexerPositionSelector = SECOND_TEMP;
        } else {
            indexerPositionSelector = FIRST;
        }
    }

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
                if (Range.isBetween(getIndexerPosition(), 0.9, 1.2)) {
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
                if (Range.isBetween(getIndexerPosition(), 1.7, 2)) {
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
                if (Range.isBetween(getIndexerPosition(), 2.5, 3)) {
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
