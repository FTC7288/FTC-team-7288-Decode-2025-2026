package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;


import static org.firstinspires.ftc.teamcode.Global.Constants.IndexerSubsystem.IndexerPositionSelector.FULL;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.CommandBase.Commands.IndexerPositionCommand;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class Indexer extends SubsystemBase {
    Robot robot = Robot.getInstance();

    public void setLaunchPosition() {robot.indexerServo.setPosition(Constants.IndexerSubsystem.LAUNCH_POSITION);}

    public void setFirstPosition() {robot.indexerServo.setPosition(Constants.IndexerSubsystem.FIRST_POSITION);}

    public void setSecondPosition() {robot.indexerServo.setPosition(Constants.IndexerSubsystem.SECOND_POSITION);}

    public void setThirdPosition() {robot.indexerServo.setPosition(Constants.IndexerSubsystem.THIRD_POSITION);}

    public boolean isFull() {
        return IndexerPositionCommand.indexerPositionSelector.equals(FULL);
    }

}
