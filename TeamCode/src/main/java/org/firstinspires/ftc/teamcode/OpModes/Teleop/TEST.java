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
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,136,8.5, AngleUnit.RADIANS,Math.toRadians(90));

        robot.init(hardwareMap);
        robot.drive.setInitialRobotPose();
        driver = new GamepadEx(gamepad1);


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
                new LaunchCommand()
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


        if (robot.limelight.getPose() != null) {
            telemetry.addData("Turret Position X: ", robot.limelight.getPose().getPosition().x * 39.37);
            telemetry.addData("Turret Position Y: ", robot.limelight.getPose().getPosition().y * 39.37);
        }

        telemetry.addData("Rotation: ", robot.imu.getAngularOrientation().firstAngle);
        telemetry.addData("Translational Angle: ", robot.turret.desiredAngle);
        telemetry.addData("Translational Angle #2: ", robot.turret.translationalAngle);
        telemetry.addData("Current Vector: ", robot.turret.currentPosVector.normF());
        telemetry.addData("Initial Vector: ", robot.turret.initialPosVector.normF());


        telemetry.addData("Intake State: ", robot.intake.intakePositionSelector );


        telemetry.addData("Current Pose X: ", robot.drive.getRobotPose().getX(DistanceUnit.INCH));
        telemetry.addData("Current Pose Y: ", robot.drive.getRobotPose().getY(DistanceUnit.INCH));

        telemetry.addData("Distance: ", robot.flywheel.distanceVector.normF());
        telemetry.addData("Is FUll: ", robot.indexer.isFull());

        telemetry.update();
    }


    public void end() {
        robot.limelight.stop();
    }

}
