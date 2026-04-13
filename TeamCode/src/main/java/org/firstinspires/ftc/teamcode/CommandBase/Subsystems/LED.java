package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class LED extends SubsystemBase {
    Robot robot = Robot.getInstance();

    HashMap<Constants.IndexerSubsystem.IndexerPositionSelector, Double> LEDColorMap = new HashMap<>();
    public LED() {
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.LAUNCH, Constants.LEDSubsystem.LEDColors.BLACK.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.FIRST, Constants.LEDSubsystem.LEDColors.RED.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.FIRST_TEMP, Constants.LEDSubsystem.LEDColors.RED.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.SECOND, Constants.LEDSubsystem.LEDColors.ORANGE.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.SECOND_TEMP, Constants.LEDSubsystem.LEDColors.ORANGE.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.THIRD, Constants.LEDSubsystem.LEDColors.YELLOW.getColor());
        LEDColorMap.put(Constants.IndexerSubsystem.IndexerPositionSelector.FULL, Constants.LEDSubsystem.LEDColors.GREEN.getColor());
    }


    @Override
    public void periodic() {
        if (robot.flywheel.isFlywheelOn()) {
            robot.ledServo.setPosition((robot.flywheel.isFlywheelAtSpeed()) ? Constants.LEDSubsystem.LEDColors.BLUE.getColor() : Constants.LEDSubsystem.LEDColors.WHITE.getColor());
        } else {
            robot.ledServo.setPosition((LEDColorMap.get(robot.indexer.indexerPositionSelector) != null) ? LEDColorMap.get(robot.indexer.indexerPositionSelector) : 0 );
        }
    }



}
