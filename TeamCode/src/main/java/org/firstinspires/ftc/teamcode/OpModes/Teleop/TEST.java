package org.firstinspires.ftc.teamcode.OpModes.Teleop;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

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

import kotlin.Unit;

@TeleOp
public class TEST extends CommandOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx driver;

    @Override
    public void initialize() {
        super.reset();

        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        driver = new GamepadEx(gamepad1);


        Trigger driverStartButton = new GamepadButton(driver, GamepadKeys.Button.START);
        driverStartButton.whenActive(
                new InstantCommand(() -> robot.drive.updateBotHeading())
        );

        Trigger driverLeftBumper = new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER);
        driverLeftBumper.whileActiveContinuous(
                new RunCommand(() -> robot.intake.startOuttake(), robot.intake)
        );


        Trigger driverAButton = new GamepadButton(driver, GamepadKeys.Button.B);
        driverAButton.whileActiveContinuous(
                new LaunchCommand()
        ).whenInactive(
                new InstantCommand(() -> robot.indexer.interruptForLaunch(false), robot.indexer)
        );


        Trigger leftTriggerScheduler = new Trigger(
                () -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > Constants.GamePad.TRIGGER_THRESHOLD);
        robot.intake.setDefaultCommand(
                new RunCommand(() -> {
                    if (leftTriggerScheduler.get()) {
                        robot.intake.setIntakeStateIntake();
                    } else {
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

        if (robot.limelight.getPose() != null) {
            telemetry.addData("Turret Position X: ", robot.limelight.getPose().getPosition().x * 3.28084);
            telemetry.addData("Turret Position Y: ", robot.limelight.getPose().getPosition().y * 3.28084);
        }

        telemetry.addData("Intake State: ", robot.intake.intakePositionSelector );

        telemetry.addData("State: ", robot.indexer.indexerPositionSelector);
        telemetry.addData("Indexer Position: ", robot.indexer.getIndexerPosition());
        telemetry.addData("Intake Position: ", robot.intake.getIntakePosition());


        telemetry.addData("Current Pose X: ", robot.drive.getRobotPose().getX(DistanceUnit.INCH));
        telemetry.addData("Current Pose Y: ", robot.drive.getRobotPose().getY(DistanceUnit.INCH));


        telemetry.addData("Break Beam: ", robot.breakBeam.getState());
        telemetry.addData("Is FUll: ", robot.indexer.isFull());

        telemetry.update();
    }


    public void end() {
        robot.limelight.stop();
    }

}
