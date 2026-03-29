package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Constants.FlywheelSubsystem.*;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Flywheel extends SubsystemBase {
    Robot robot = Robot.getInstance();
    private static FlywheelSpeedSelector flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_OFF;

    private double desiredFlywheelVelocity = 0.5;


    public void turnFlywheelOn () {
        flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_ON;
    }

    public void turnFlywheelOff() {
        flywheelSpeedSelector = FlywheelSpeedSelector.FLYWHEEL_OFF;
    }

//    public boolean isFlywheelAtSpeed() {
//        return Range.isBetween(robot.flywheelEncoder.getCorrectedVelocity(),
//                desiredFlywheelVelocity - Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE,
//                desiredFlywheelVelocity + Constants.HardwareInitialization.FLYWHEEL_ENCODER_TOLERANCE);
//    }

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


    // TODO: FINISH PLEASE
    private double setTargetFlywheelVelocity(Pose3D currentPose) {





        //Temp
        return 0;
    }

    @Override
    public void periodic() {
        switch (flywheelSpeedSelector) {
            case FLYWHEEL_ON:
                setFlywheelVelocity(1);
                break;
            case FLYWHEEL_OFF:
                setFlywheelVelocityZero();
                break;
        }
    }


}
