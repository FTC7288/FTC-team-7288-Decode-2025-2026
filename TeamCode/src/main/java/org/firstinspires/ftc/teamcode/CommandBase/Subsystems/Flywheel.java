package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Flywheel extends SubsystemBase {
    Robot robot = Robot.getInstance();
    Constants.FlywheelSubsystem.FlywheelSpeedSelector flywheelSpeedSelector = Constants.FlywheelSubsystem.FlywheelSpeedSelector.OFF;

    private double desiredFlywheelVelocity;



    @Override
    public void periodic() {
        desiredFlywheelVelocity = setTargetFlywheelVelocity();

        switch (flywheelSpeedSelector) {
            case ON:
                setFlywheelVelocity(desiredFlywheelVelocity);
            case OFF:
                setFlywheelVelocityZero();
        }
    }

    public void turnFlywheelOn () {flywheelSpeedSelector = Constants.FlywheelSubsystem.FlywheelSpeedSelector.ON;}

    public void turnFlywheelOff() {flywheelSpeedSelector = Constants.FlywheelSubsystem.FlywheelSpeedSelector.OFF;}

    public boolean isFlywheelAtSpeed() {
        return Range.isBetween(robot.flywheelEncoder.getCorrectedVelocity(),
                desiredFlywheelVelocity - Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE,
                desiredFlywheelVelocity + Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE);
    }

    public boolean isFlywheelOn () {
        return flywheelSpeedSelector.equals(Constants.FlywheelSubsystem.FlywheelSpeedSelector.ON);
    }

    public void setFlywheelVelocity(double velocity) {
        robot.flywheelTopMotor.set(velocity);
        robot.flywheelBottomMotor.set(velocity);
    }

    public void setFlywheelVelocityZero() {
        robot.flywheelTopMotor.set(0);
        robot.flywheelBottomMotor.set(0);
    }


    // TODO: FINISH PLEASE
    private double setTargetFlywheelVelocity(Pose3D currentPose) {





        //Temp
        return 0;
    }



}
