package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import org.firstinspires.ftc.teamcode.Global.Constants;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class Turret extends SubsystemBase {
    Robot robot = Robot.getInstance();
    private Constants.TurretSubsystem.TurretPositionSelector turretState = Constants.TurretSubsystem.TurretPositionSelector.TURRET_OFF;
    public SimpleMatrix initialPosVector = new SimpleMatrix(2,1);
    public SimpleMatrix currentPosVector = new SimpleMatrix(2,1);
    private Pose2D TEAM_GOAL_POSE;
    public double desiredAngle;
    public double translationalAngle;

    public Turret() {
        if (Constants.AllianceSelection.SELECTED_TEAM.equals(Constants.AllianceSelection.RED_TEAM)) {
            TEAM_GOAL_POSE = Constants.AllianceSelection.RED_GOAL_POSE;
        } else {
            TEAM_GOAL_POSE = Constants.AllianceSelection.BLUE_GOAL_POSE;
        }
        initialPosVector.set(0,0,TEAM_GOAL_POSE.getX(DistanceUnit.INCH)- Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getX(DistanceUnit.INCH));
        initialPosVector.set(1,0,TEAM_GOAL_POSE.getY(DistanceUnit.INCH)- Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getY(DistanceUnit.INCH));

        currentPosVector.set(0,0,initialPosVector.get(0,0));
        currentPosVector.set(1,0,initialPosVector.get(1,0));
    }

    public void turnTurretOn () {
        turretState = Constants.TurretSubsystem.TurretPositionSelector.TURRET_ON;
    }

    public void turnTurretOff() {
        turretState = Constants.TurretSubsystem.TurretPositionSelector.TURRET_OFF;
    }

    public void setTurretPower(double position) {
        robot.turretMotor.set(position);
    }

    public double calculateTurretPosition(Pose2D currentPose) {


        currentPosVector.set(0,0, TEAM_GOAL_POSE.getX(DistanceUnit.INCH)-currentPose.getX(DistanceUnit.INCH));
        currentPosVector.set(1,0, TEAM_GOAL_POSE.getY(DistanceUnit.INCH)-currentPose.getY(DistanceUnit.INCH));

        translationalAngle = Math.acos(initialPosVector.dot(currentPosVector) / (currentPosVector.normF() * initialPosVector.normF()));

        double multiplier;
//        if (currentPose.getX(DistanceUnit.INCH) - Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getX(DistanceUnit.INCH) < 0) {
//            multiplier = 1;
//        } else {
//            multiplier = -1;
//        }

        desiredAngle = Constants.TurretSubsystem.TICK_CONSTANT * ((Math.toDegrees(translationalAngle) * -1) - Math.toDegrees(robot.drive.getIMUOrientation()));
        return Constants.TurretSubsystem.turretPIDFController.calculate(robot.turretEncoder.getPosition(), desiredAngle);
    }

    @Override
    public void periodic() {
        switch (turretState) {
            case TURRET_OFF:
                setTurretPower(0);
                break;
            case TURRET_ON:
                setTurretPower(calculateTurretPosition(robot.drive.getRobotPose()));
                break;
        }
    }


}
