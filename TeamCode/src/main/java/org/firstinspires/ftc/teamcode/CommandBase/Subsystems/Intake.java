package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.Range;

public class Intake extends SubsystemBase {
   Robot robot = Robot.getInstance();

    public Constants.IntakeSubsystem.INTAKE_POSITIONS intakePositionSelector = Constants.IntakeSubsystem.INTAKE_POSITIONS.NEUTRAL;
    public Constants.IntakeSubsystem.INTAKE_POSITIONS previousIntakePosition = null;

    public double getIntakePosition() {
        return robot.intakeAnalog.getVoltage();
    }

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
        intakePositionSelector  = Constants.IntakeSubsystem.INTAKE_POSITIONS.INTAKE;
    }
    public void setIntakeStateTransfer() {
        intakePositionSelector  = Constants.IntakeSubsystem.INTAKE_POSITIONS.TRANSFER;
    }
    public void setIntakeStateNeutral() {
        intakePositionSelector  = Constants.IntakeSubsystem.INTAKE_POSITIONS.NEUTRAL;
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
        return getIntakePosition() > Constants.IntakeSubsystem.INTAKE_ANALOG_IN;
    }

    public boolean isIntakeOut() {
        return intakePositionSelector == Constants.IntakeSubsystem.INTAKE_POSITIONS.INTAKE;
    }


    @Override
    public void periodic () {

        switch (intakePositionSelector ) {
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
