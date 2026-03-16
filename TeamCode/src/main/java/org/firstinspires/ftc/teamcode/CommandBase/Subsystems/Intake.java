package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class Intake extends SubsystemBase {
   Robot robot = Robot.getInstance();


    public void startIntake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.INTAKE_SPEED);
    }

    public void stopIntake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.OFF_SPEED);
    }

    public void startOuttake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.OUTTAKE_SPEED);
    }

    public void setIntakeIntake() {
        robot.intakeRightServo.setPosition(Constants.IntakeSubsystem.INTAKE_POSITION);
        robot.intakeLeftServo.setPosition(Constants.IntakeSubsystem.INTAKE_POSITION);
    }

    public void setIntakeNeutral() {
        robot.intakeRightServo.setPosition(Constants.IntakeSubsystem.NEUTRAL_POSITION);
        robot.intakeLeftServo.setPosition(Constants.IntakeSubsystem.NEUTRAL_POSITION);
    }

    public void setIntakeTransfer() {
        robot.intakeRightServo.setPosition(Constants.IntakeSubsystem.TRANSFER_POSITION);
        robot.intakeLeftServo.setPosition(Constants.IntakeSubsystem.TRANSFER_POSITION);
    }



}
