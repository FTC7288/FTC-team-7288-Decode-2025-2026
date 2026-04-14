package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import android.app.usage.ConfigurationStats;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.telemetry.SelectScope;
import com.pedropathing.telemetry.SelectableOpMode;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;



@TeleOp(name = "Testing Teleops", group = "Testing")
public class TestingTeleops extends SelectableOpMode {

    public TestingTeleops() {
        super("Select an Opmode: ", main -> {
            main.folder("PID Tuners: ", sub -> {

            });
            main.folder("Main Testing: ",  sub -> {
                sub.add("Distance to Velocity Tuner", DistanceToVelocityTuner::new);
            });
        });
    }
}


class DistanceToVelocityTuner extends OpMode {
    Robot robot = Robot.getInstance();
    double currentVelocity = 1000;
    SimpleMatrix distanceVector = new SimpleMatrix(2,1);

    @Override
    public void init() {


        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.otos.setPosition(new SparkFunOTOS.Pose2D(136,8.5,Math.toRadians(90)));
    }

    @Override
    public void loop() {
        robot.drive.updateIMUOrientation();
        robot.drive.driveFieldCentric(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, robot.drive.getBotHeading());

        if (gamepad1.dpadUpWasPressed()) {
            Constants.FlywheelSubsystem.flywheelPIDFCOntroller.setP(Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getP() + 0.0001);
        } else if (gamepad1.dpadDownWasPressed()) {
            Constants.FlywheelSubsystem.flywheelPIDFCOntroller.setP(Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getP() - 0.0001);
        }

        if (gamepad1.dpadLeftWasPressed()) {
            Constants.FlywheelSubsystem.flywheelPIDFCOntroller.setP(Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getF() + 0.000001);
        } else if (gamepad1.dpadRightWasPressed()) {
            Constants.FlywheelSubsystem.flywheelPIDFCOntroller.setP(Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getF() - 0.000001);
        }

        if (gamepad1.rightBumperWasPressed()) {
            currentVelocity += 10;
        } else if (gamepad1.leftBumperWasPressed()) {
            currentVelocity -= 10;
        }

        distanceVector.set(0,0, Constants.AllianceSelection.RED_GOAL_POSE.getX(DistanceUnit.INCH) - robot.otos.getPosition().x);
        distanceVector.set(1,0, Constants.AllianceSelection.RED_GOAL_POSE.getY(DistanceUnit.INCH) - robot.otos.getPosition().y);

        robot.turretMotor.set(Constants.TurretSubsystem.turretPIDFController.calculate(robot.turretEncoder.getPosition(), 0));

        double velocity = Constants.FlywheelSubsystem.flywheelPIDFCOntroller.calculate(robot.flywheelEncoder.getCorrectedVelocity(), currentVelocity);
        if (gamepad1.right_trigger > 0.5) {
            robot.flywheelTopMotor.set(velocity);
            robot.flywheelBottomMotor.set(velocity);
        } else {
            robot.flywheelTopMotor.set(0);
            robot.flywheelBottomMotor.set(0);
        }


        if (gamepad1.a) {
            robot.intake.setIntakeTransfer();
            robot.intake.startIntake();
            if (robot.intake.isIntakeIn()) {
                robot.indexerServo.setPosition(Constants.IndexerSubsystem.LAUNCH_POSITION);
            }
        } else if (gamepad1.b) {
            robot.intake.setIntakeNeutral();
            robot.intake.stopIntake();
            robot.indexerServo.setPosition(Constants.IndexerSubsystem.FIRST_POSITION);
        } else if (gamepad1.y) {
            robot.intake.setIntakeNeutral();
            robot.intake.stopIntake();
            robot.indexerServo.setPosition(Constants.IndexerSubsystem.SECOND_POSITION);
        } else if (gamepad1.x) {
            robot.intake.setIntakeNeutral();
            robot.intake.stopIntake();
            robot.indexerServo.setPosition(Constants.IndexerSubsystem.THIRD_POSITION);
        }

        telemetry.addData("X: ", robot.otos.getPosition().x);
        telemetry.addData("Y: ", robot.otos.getPosition().y);

        telemetry.addData("Goal Pose X: ", Constants.AllianceSelection.RED_GOAL_POSE.getX(DistanceUnit.INCH));
        telemetry.addData("Goal Pose Y: ", Constants.AllianceSelection.RED_GOAL_POSE.getY(DistanceUnit.INCH));

        telemetry.addData("P: ", Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getP());
        telemetry.addData("F: ", Constants.FlywheelSubsystem.flywheelPIDFCOntroller.getF());

        telemetry.addData("Desired Velocity: ", currentVelocity);
        telemetry.addData("Current Velocity: ", robot.flywheelEncoder.getCorrectedVelocity());
        telemetry.addData("Current Distance: ", distanceVector.normF());
        telemetry.addData("Indexer Analog: ", robot.intakeAnalog.getVoltage());

        telemetry.update();
    }
}
