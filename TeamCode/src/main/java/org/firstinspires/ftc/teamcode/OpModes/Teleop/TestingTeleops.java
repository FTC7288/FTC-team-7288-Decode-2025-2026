package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import android.app.usage.ConfigurationStats;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.pedropathing.telemetry.SelectScope;
import com.pedropathing.telemetry.SelectableOpMode;
import com.qualcomm.hardware.lynx.LynxModule;
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
                sub.add("Turret: ", TurretPosPID::new);
                sub.add("Flywheel: ", FlywheelPosPID::new);
            });
            main.folder("Main Testing: ",  sub -> {
                sub.add("Distance to Velocity Tuner", DistanceToVelocityTuner::new);
                sub.add("Servo Pos Tuner: ", ServoPosTuner::new);
                sub.add("Max Speed: ", MaxFlywheelSpeed::new);
            });
        });
    }
}


class DistanceToVelocityTuner extends OpMode {
    Robot robot = Robot.getInstance();
    double currentVelocity = 1000;
    SimpleMatrix distanceVector = new SimpleMatrix(2,1);

    double p = 0,i = 0,d = 0,f = 0;
    PIDFController flywheelPIDFCOntroller = new PIDFController(p,i,d,f);

    @Override
    public void init() {


        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.otos.setPosition(new SparkFunOTOS.Pose2D(136,8.5,Math.toRadians(90)));

        robot.turretEncoder.reset();
    }

    @Override
    public void loop() {
        robot.drive.updateIMUOrientation();
        robot.drive.driveFieldCentric(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, robot.drive.getBotHeading());

        if (gamepad1.dpadUpWasPressed()) {
            flywheelPIDFCOntroller.setP(flywheelPIDFCOntroller.getP() + 0.0001);
        } else if (gamepad1.dpadDownWasPressed()) {
            flywheelPIDFCOntroller.setP(flywheelPIDFCOntroller.getP() - 0.0001);
        }

        if (gamepad1.dpadLeftWasPressed()) {
            flywheelPIDFCOntroller.setF(flywheelPIDFCOntroller.getF() + 0.0001);
        } else if (gamepad1.dpadRightWasPressed()) {
            flywheelPIDFCOntroller.setF(flywheelPIDFCOntroller.getF() - 0.0001);
        }

        if (gamepad1.rightBumperWasPressed()) {
            currentVelocity += 10;
        } else if (gamepad1.leftBumperWasPressed()) {
            currentVelocity -= 10;
        }

        distanceVector.set(0,0, Constants.AllianceSelection.RED_GOAL_POSE.getX(DistanceUnit.INCH) - robot.otos.getPosition().x);
        distanceVector.set(1,0, Constants.AllianceSelection.RED_GOAL_POSE.getY(DistanceUnit.INCH) - robot.otos.getPosition().y);

        robot.turretMotor.set(Constants.TurretSubsystem.turretPIDFController.calculate(robot.turretEncoder.getPosition(), 0));

        double velocity = flywheelPIDFCOntroller.calculate(robot.flywheelEncoder.getCorrectedVelocity(), currentVelocity);
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

        telemetry.addData("P: ", flywheelPIDFCOntroller.getP() * 100);
        telemetry.addData("F: ", flywheelPIDFCOntroller.getF() * 100);

        telemetry.addData("Desired Velocity: ", currentVelocity);
        telemetry.addData("Current Velocity: ", robot.flywheelEncoder.getCorrectedVelocity());
        telemetry.addData("Current Distance: ", distanceVector.normF());
        telemetry.addData("Indexer Analog: ", robot.intakeAnalog.getVoltage());

        telemetry.update();
    }
}

class ServoPosTuner extends OpMode{
    Robot robot = Robot.getInstance();
    double pos = 0.5;
    double intakePose = 0.5;

    @Override
    public void init() {
        Constants.HardwareInitialization.AUTO = false;
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.dpadUpWasPressed()) {
            pos += 0.01;
        } else if (gamepad1.dpadDownWasPressed()) {
            pos -= 0.01;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            intakePose += 0.01;
        } else if (gamepad1.dpadRightWasPressed()) {
            intakePose -= 0.01;
        }
        robot.indexerServo.setPosition(pos);

        robot.intakeRightServo.setPosition(intakePose);
        robot.intakeLeftServo.setPosition(intakePose);

        telemetry.addData("Pos: ", pos);
        telemetry.addData("Pose 2: ", intakePose);

        telemetry.update();
    }
}



class TurretPosPID extends OpMode {
    Robot robot = Robot.getInstance();
    double P=0,I=0,D=0,F=0;
    PIDFController pidfController = new PIDFController(P,I,D,F);

    double[] stepSizes = {0.1,0.01,0.001,0.0001,0.00001,0.000001};

    double[] positions = {-175,175};
    String[] valNames = {"P","I","D","F"};
    int stepIndex = 0;

    int PIDSelection = 1;
    double targetVal = positions[0];


    public void init() {
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.turretEncoder.reset();
    }


    public void loop() {




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

        if (gamepad1.yWasPressed()) {
            if (targetVal == positions[0]) {
                targetVal = positions[1];
            } else if (targetVal == positions[1]) {
                targetVal = positions[0];
            }
        }


        pidfController.setPIDF(P, I, D, F);
        telemetry.addData("Current P Values: ", pidfController.getP() * 100);
        telemetry.addData("Current I Values: ", pidfController.getI() * 100);
        telemetry.addData("Current D Values: ", pidfController.getD() * 100);
        telemetry.addData("Current F Values: ", pidfController.getF() * 100);


        double values = pidfController.calculate(robot.turretEncoder.getPosition(), targetVal);

        if (gamepad1.right_trigger > 0.5) {
            robot.turretMotor.set(values);
        }

        telemetry.addLine();

        String thing = "Target Shooter Velocity: ";
        telemetry.addData(thing, targetVal);
        telemetry.addData("Current Turret Pos: ", robot.turretMotor.getCurrentPosition());
        telemetry.update();
    }
}

class FlywheelPosPID extends OpMode {
    Robot robot = Robot.getInstance();
    double P=0,I=0,D=0,F=0;
    PIDFController pidfController = new PIDFController(P,I,D,F);

    SimpleMatrix distanceVector = new SimpleMatrix(2,1);

    double[] stepSizes = {0.1,0.01,0.001,0.0001,0.00001,0.000001};

    String[] valNames = {"P","I","D","F"};
    int stepIndex = 0;

    int PIDSelection = 1;
    double targetVal = 1000;


    public void init() {
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.flywheelEncoder.reset();

        robot.otos.setPosition(new SparkFunOTOS.Pose2D(136,8.5,Math.toRadians(90)));

        robot.turretEncoder.reset();
    }


    public void loop() {
        robot.drive.updateIMUOrientation();
        robot.drive.driveFieldCentric(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, robot.drive.getBotHeading());


        if (gamepad1.startWasPressed()) {
            if (PIDSelection == 3) {
                PIDSelection = 0;
            } else {
                PIDSelection++;
            }
        }


        telemetry.addData("Changing PID Value:", valNames[PIDSelection]);

        if (gamepad1.backWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }
        telemetry.addData("Step Index *100: ", stepSizes[stepIndex] * 100);


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

        distanceVector.set(0,0, Constants.AllianceSelection.RED_GOAL_POSE.getX(DistanceUnit.INCH) - robot.otos.getPosition().x);
        distanceVector.set(1,0, Constants.AllianceSelection.RED_GOAL_POSE.getY(DistanceUnit.INCH) - robot.otos.getPosition().y);

        robot.turretMotor.set(Constants.TurretSubsystem.turretPIDFController.calculate(robot.turretEncoder.getPosition(), 0));


        pidfController.setPIDF(P, I, D, F);
        telemetry.addData("Current P Values: ", pidfController.getP() * 100);
        telemetry.addData("Current I Values: ", pidfController.getI() * 100);
        telemetry.addData("Current D Values: ", pidfController.getD() * 100);
        telemetry.addData("Current F Values: ", pidfController.getF() * 100);

        telemetry.addData("Current Distance: ", distanceVector.normF());

        if (gamepad1.rightBumperWasPressed()) {
            targetVal += 10;
        } else if (gamepad1.leftBumperWasPressed()) {
            targetVal -= 10;
        }




        double values = pidfController.calculate(robot.flywheelEncoder.getRawVelocity(), targetVal);

        if (gamepad1.right_trigger > 0.5) {
            robot.flywheelTopMotor.set(values);
            robot.flywheelBottomMotor.set(values);

        } else {
            robot.flywheelTopMotor.set(0);
            robot.flywheelBottomMotor.set(0);
        }


        telemetry.addLine();

        String thing = "Target Shooter Velocity: ";
        telemetry.addData(thing, targetVal);
        telemetry.addData("Current Turret Pos: ", robot.flywheelEncoder.getRawVelocity());
        telemetry.update();
    }
}

class MaxFlywheelSpeed extends OpMode {
    Robot robot = Robot.getInstance();
    @Override
    public void init() {
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.flywheelEncoder.reset();

        robot.otos.setPosition(new SparkFunOTOS.Pose2D(136,8.5,Math.toRadians(90)));

        robot.turretEncoder.reset();
    }

    @Override
    public void loop() {
        robot.flywheelTopMotor.set(1);
        robot.flywheelBottomMotor.set(1);

        telemetry.addData("Velocity: " , robot.flywheelEncoder.getRawVelocity());
    }


}

