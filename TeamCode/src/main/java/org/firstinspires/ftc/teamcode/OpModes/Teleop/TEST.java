package org.firstinspires.ftc.teamcode.OpModes.Teleop;


import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.bylazar.panels.Panels;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.LaunchCommand;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.CommandOpMode;
import kotlin.Unit;

@TeleOp
public class TEST extends CommandOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx driver;
    FieldManager panelsField = PanelsField.INSTANCE.getField();

    double P=0,I=0,D=0,F=0;
    double[] stepSizes = {0.1,0.01,0.001,0.0001,0.00001,0.000001};
    String[] valNames = {"P","I","D","F"};
    int stepIndex = 0;

    int PIDSelection = 1;

    @Override
    public void initialize() {
        super.reset();

        Constants.HardwareInitialization.AUTO = false;
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = Constants.HardwareInitialization.END_ROBOT_POSE;

        robot.init(hardwareMap);
        robot.drive.setInitialRobotPose();
        driver = new GamepadEx(gamepad1);

        panelsField.setOffsets(PanelsField.INSTANCE.getPresets().getPEDRO_PATHING());


        Trigger driverStartButton = new GamepadButton(driver, GamepadKeys.Button.START);
        driverStartButton.whenActive(
                new InstantCommand(() -> robot.drive.updateBotHeading())
        );

        Trigger driverLeftBumper = new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER);
        driverLeftBumper.whileActiveContinuous(
                new RunCommand(() -> robot.intake.setIntakeStateOuttake(), robot.intake)
        );


        Trigger driverBButton = new GamepadButton(driver, GamepadKeys.Button.B);
        driverBButton.whileActiveContinuous(
                new RunCommand(() -> {robot.intake.setIntakeStateTransfer(); robot.indexer.interruptForLaunch(true);})
        ).whenInactive(
                new InstantCommand(() -> robot.indexer.interruptForLaunch(false), robot.indexer)
        );


        Trigger leftTriggerScheduler = new Trigger(
                () -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > Constants.GamePad.TRIGGER_THRESHOLD);
        robot.intake.setDefaultCommand(
                new RunCommand(() -> {
                    if (leftTriggerScheduler.get() && !driverLeftBumper.get()) {
                        robot.intake.setIntakeStateIntake();
                    } else if (!driverLeftBumper.get()){
                        robot.intake.setIntakeStateNeutral();
                    }
                }, robot.intake)
        );


        Trigger rightTriggerScheduler = new Trigger(
                () -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > Constants.GamePad.TRIGGER_THRESHOLD);
        rightTriggerScheduler.whenActive(
                new ParallelCommandGroup(
                        new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),
                        new InstantCommand(() -> robot.turret.turnTurretOn(), robot.turret)
                )
        ).whenInactive(
               new ParallelCommandGroup(
                       new InstantCommand(() -> robot.flywheel.turnFlywheelOff(), robot.flywheel),
                       new InstantCommand(() -> robot.turret.turnTurretOff(), robot.turret)
               )
        );
    }


    @Override
    public void run() {
        super.run();



        robot.drive.updateIMUOrientation();
        robot.drive.driveFieldCentric(
                driver.getLeftY(),
                driver.getLeftX(),
                driver.getRightX(),
                robot.drive.getBotHeading()
        );




        if (gamepad1.aWasPressed()) {
            if (PIDSelection == 3) {
                PIDSelection = 0;
            } else {
                PIDSelection++;
            }
        }


        telemetry.addData("Changing PID Value:", valNames[PIDSelection]);

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }
        telemetry.addData("Step Index *100: ", stepSizes[stepIndex] * 100);
        telemetry.addLine();


        if (gamepad1.dpadUpWasPressed()) {
            if (PIDSelection == 0) {
                P += stepSizes[stepIndex];
            } else if (PIDSelection == 1) {
                I += stepSizes[stepIndex];
            } else if (PIDSelection == 2) {
                D += stepSizes[stepIndex];
            } else if (PIDSelection == 3) {
                F += stepSizes[stepIndex];
            }
        } else if (gamepad1.dpadDownWasPressed()) {
            if (PIDSelection == 0) {
                P -= stepSizes[stepIndex];
            } else if (PIDSelection == 1) {
                I -= stepSizes[stepIndex];
            } else if (PIDSelection == 2) {
                D -= stepSizes[stepIndex];
            } else if (PIDSelection == 3) {
                F -= stepSizes[stepIndex];
            }
        }



//        telemetry.addData("Current P Values: ", Constants.TurretSubsystem.turretPIDFController.getP() * 100);
//        telemetry.addData("Current I Values: ", Constants.TurretSubsystem.turretPIDFController.getI() * 100);
//        telemetry.addData("Current D Values: ", Constants.TurretSubsystem.turretPIDFController.getD() * 100);
//        telemetry.addData("Current F Values: ", Constants.TurretSubsystem.turretPIDFController.getF() * 100);



        telemetry.addData("Translational Angle: ", robot.turret.translationalAngle);
        telemetry.addData("Rotation: ", robot.turret.rotationalAngle);
        telemetry.addData("Desiered Angle: ", robot.turret.wantedAngle);
        telemetry.addData("Desired Turret Position: ", robot.turret.desiredAngle);
        telemetry.addData("Current Turret Pos: ", robot.turretEncoder.getPosition());
        telemetry.addData("Current Turret Angle: ", robot.turretEncoder.getPosition() / Constants.TurretSubsystem.TICK_CONSTANT);
        telemetry.addLine();

        telemetry.addData("Current Vector X: ", robot.turret.currentPosVector.get(0,0));
        telemetry.addData("Current Vector Y: ", robot.turret.currentPosVector.get(1,0));

        telemetry.addData("Initial Vector X: ", robot.turret.initialPosVector.get(0,0));
        telemetry.addData("Initial Vector Y: ", robot.turret.initialPosVector.get(1,0));

        telemetry.addLine();

        telemetry.addData("Team Pose X: ", robot.turret.TEAM_GOAL_POSE.getX(DistanceUnit.INCH));
        telemetry.addData("Team Pose Y: ", robot.turret.TEAM_GOAL_POSE.getY(DistanceUnit.INCH));


        telemetry.addLine();
//
//
//        telemetry.addData("Intake State: ", robot.intake.intakePositionSelector );
//        telemetry.addData("Intake Analog: ", robot.intake.getIntakePosition());
//
//
        telemetry.addData("Current Pose X: ", robot.drive.getRobotPose().getX(DistanceUnit.INCH));
        telemetry.addData("Current Pose Y: ", robot.drive.getRobotPose().getY(DistanceUnit.INCH));
//
        telemetry.addData("Distance: ", robot.flywheel.distanceVector.normF());
//        telemetry.addData("Is FUll: ", robot.indexer.isFull());
//
//        telemetry.addData("Break Beam: ", robot.breakBeam.getState());
        telemetry.addData("Analog: ", robot.indexerAnalog.getVoltage());

        telemetry.addData("Analog Intake: ", robot.intake.getIntakePosition());
//
        telemetry.update();
    }

    @Override
    public void end() {
        Constants.HardwareInitialization.END_ROBOT_POSE = robot.drive.getRobotPose();
        robot.limelight.stop();
    }

}
