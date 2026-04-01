package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Intake extends SubsystemBase {
   Robot robot = Robot.getInstance();
   private boolean transferInterrupt = false;

    private static Constants.IntakeSubsystem.INTAKE_POSITIONS intake = Constants.IntakeSubsystem.INTAKE_POSITIONS.NEUTRAL;

    public void startIntake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.INTAKE_SPEED);
    }

    public void stopIntake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.OFF_SPEED);
    }

    public void startOuttake() {
        robot.intakeMotor.set(Constants.IntakeSubsystem.OUTTAKE_SPEED);
    }


    public void setIntakeStateIntake() {
        intake = Constants.IntakeSubsystem.INTAKE_POSITIONS.INTAKE;
    }
    public void setIntakeStateTransfer() {
        intake = Constants.IntakeSubsystem.INTAKE_POSITIONS.TRANSFER;
        transferInterrupt = true;
    }
    public void setIntakeStateNeutral() {
        intake = Constants.IntakeSubsystem.INTAKE_POSITIONS.NEUTRAL;
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

    public boolean isIntakeIn() {
        return robot.intakeAnalog.getVoltage() > Constants.IntakeSubsystem.INTAKE_ANALOG_IN;
    }

    public double getIntakePosition() {
        return robot.intakeAnalog.getVoltage();
    }

    @Override
    public void periodic () {
        switch (intake) {
            case INTAKE:
                setIntakeIntake();
                startIntake();
                break;
            case NEUTRAL:
                setIntakeNeutral();
                stopIntake();
                break;
            case TRANSFER:
                setIntakeTransfer();
                startIntake();
                break;
        }
    }

}
