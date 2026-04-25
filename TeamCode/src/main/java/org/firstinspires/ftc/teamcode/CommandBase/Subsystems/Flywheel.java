package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.arcrobotics.ftclib.util.Timing;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Constants.FlywheelSubsystem.*;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Flywheel extends SubsystemBase {
    Robot robot = Robot.getInstance();
    private static FlywheelSpeedSelector flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_OFF;
    private Pose2D TEAM_GOAL_POSE;
    public SimpleMatrix distanceVector = new SimpleMatrix(2,1);
    private double desiredFlywheelVelocity = 0;

    public Flywheel () {

        if (Constants.AllianceSelection.SELECTED_TEAM.equals(Constants.AllianceSelection.RED_TEAM)) {
            TEAM_GOAL_POSE = Constants.AllianceSelection.RED_GOAL_POSE;
        } else {
            TEAM_GOAL_POSE = Constants.AllianceSelection.BLUE_GOAL_POSE;
        }

    }


    public void turnFlywheelOn () {
        flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_ON;
    }

    public void turnFlywheelOff() {
        flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_OFF;
    }

    public boolean isFlywheelAtSpeed() {
        return Range.isBetween(robot.flywheelEncoder.getCorrectedVelocity(),
                desiredFlywheelVelocity - Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE,
                desiredFlywheelVelocity + Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE);
    }

    public boolean isFlywheelOn () {
        return flywheelSpeedSelector.equals(FlywheelSpeedSelector.FLYWHEEL_ON);
    }

    public void setFlywheelVelocity(double velocity) {
        robot.flywheelTopMotor.set(velocity);
        robot.flywheelBottomMotor.set(velocity);
    }

    public void setFlywheelVelocityZero() {
        robot.flywheelTopMotor.set(0);
        robot.flywheelBottomMotor.set(0);
    }

    private double setTargetFlywheelVelocity(Pose2D currentPose) {
        distanceVector.set(0,0, TEAM_GOAL_POSE.getX(DistanceUnit.INCH) - currentPose.getX(DistanceUnit.INCH));
        distanceVector.set(1,0, TEAM_GOAL_POSE.getY(DistanceUnit.INCH) - currentPose.getY(DistanceUnit.INCH));

        double currentDistance = distanceVector.normF();

        Constants.FlywheelSubsystem.flywheelPIDFCOntroller.setP((currentDistance < 100) ? 0.004: 0.0053);

        desiredFlywheelVelocity = 5.87 * currentDistance + 756.93;

        return Constants.FlywheelSubsystem.flywheelPIDFCOntroller.calculate(robot.flywheelEncoder.getCorrectedVelocity(),desiredFlywheelVelocity);
    }

    @Override
    public void periodic() {
        double flywheelPower = setTargetFlywheelVelocity((Constants.HardwareInitialization.AUTO) ? new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(), AngleUnit.RADIANS, robot.follower.getHeading()) : robot.drive.getRobotPose());
        switch (flywheelSpeedSelector) {
            case FLYWHEEL_ON:
                setFlywheelVelocity(flywheelPower);
                break;
            case FLYWHEEL_OFF:
                setFlywheelVelocityZero();
                break;
        }
    }


}
