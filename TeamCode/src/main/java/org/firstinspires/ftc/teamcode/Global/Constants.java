package org.firstinspires.ftc.teamcode.Global;


import com.arcrobotics.ftclib.controller.PIDFController;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Constants {

    /*
     * Hardware Constants Bellow
     */

    public final static class HardwareNames {
        public final static String FLYWHEEL_TOP = "smTop";
        public final static String FLYWHEEL_BOTTOM = "smBottom";
        public final static String TURRET = "turret";
        public final static String[] DRIVE_MOTORS = {"fl", "fr", "bl", "br"};
        public final static String INTAKE = "test";
        public final static String[] INTAKE_SERVOS = {"intakeR","intakeL"};
        public final static String INDEXER_SERVO = "indexer";
        public final static String LIMELIGHT = "LimeLight";
        public final static String IMU = "imu";
        public final static String LED = "led";
        public final static String OTOS = "otos";
        public final static String BREAK_BEAM = "breakBeam";
        public final static String INTAKE_ANALOG = "intakeAnalog";
        public final static String INDEXER_ANALOG = "indexerAnalog";
    }


    public final static class HardwareInitialization {
        public static Pose2D INITIAL_ROBOT_POSE;
        public static Pose2D END_ROBOT_POSE;
        public final static String IMUParametersJsonFileName = "BNO055IMUCalibration.json";
        public final static int LIMELIGHT_POLLING_HZ = 100;
        public static final double FLYWHEEL_ENCODER_TOLERANCE = 20;


        // TODO: Add file reading to give the initial pose from auto or see if using a static non final variable works better


    }


    public final static class IntakeSubsystem {
        public final static double INTAKE_SPEED = 1;
        public final static double OFF_SPEED = 0;
        public final static double OUTTAKE_SPEED = -1;

        public final static double TRANSFER_POSITION = 0.37;
        public final static double INTAKE_POSITION = 0.7;
        public final static double NEUTRAL_POSITION = 0.5;

        public final static double INTAKE_ANALOG_IN = 2.1;

        public enum INTAKE_POSITIONS {
            INTAKE,
            TRANSFER,
            NEUTRAL
        }
    }


    public final static class TurretSubsystem {
        public static final double TICK_CONSTANT = 2.57;
        public static PIDFController turretPIDFController = new PIDFController(0.004,0,0.0009,0);
        public enum TurretPositionSelector {
            TURRET_OFF,
            TURRET_ON
        }
    }


    public final static class FlywheelSubsystem {

        public static PIDFController flywheelPIDFCOntroller = new PIDFController(0.0015,0,0,0.00051);
        public enum FlywheelSpeedSelector {
            FLYWHEEL_OFF,
            FLYWHEEL_ON
        }
    }


    public final static class IndexerSubsystem {
        public final static double LAUNCH_POSITION = 0.23;
        public final static double FIRST_POSITION = 0.32;
        public final static double SECOND_POSITION = 0.52;
        public final static double THIRD_POSITION = 0.71;

        public static final double FIRST_ANALOG_POSITION = 1.28;
        public static final double SECOND_ANALOG_POSITION = 1.85;
        public static final double THIRD_ANALOG_POSITION = 2.38;

        public enum IndexerPositionSelector {
            LAUNCH,
            FIRST,
            FIRST_TEMP,
            SECOND,
            SECOND_TEMP,
            THIRD,
            FULL
        }
    }

    public static class LEDSubsystem {
        public enum LEDColors {
            RED(0.280),
            BLUE(0.611),
            GREEN(0.500),
            YELLOW(0.388),
            ORANGE(0.333),
            PURPLE(0.721),
            WHITE(0.99),
            BLACK(0.01);

            private final double color;
            LEDColors(double color) {this.color = color;}

            public double getColor() {return color;}
        }
    }


    public final static class GamePad {
        public final static double TRIGGER_THRESHOLD = 0.5;
    }

    public final static class PedroPathingConstants {
        public static double ROBOT_MASS_KG = 11.70;
        public static double LINEAR_SCALAR = .980;
        public static double ANGULAR_SCALAR = 1.00;
        public static double FORWARD_VELOCITY = 78.34427;
        public static double LATERAL_VELOCITY = 61.51394;

        public static PIDFCoefficients HEADING_PIDF_COEFFICIENTS = new PIDFCoefficients(1.85,0,0.11,0.03);
        public static PIDFCoefficients SECONDARY_HEADING_PIDF_COEFFICIENTS = new PIDFCoefficients(0,0,0,0);
        public static PredictiveBrakingCoefficients PREDICTIVE_BRAKING_COEFFICIENTS = new PredictiveBrakingCoefficients(0.1,0.1492730968664078,0.0013504773304059629);
        public static SparkFunOTOS.Pose2D OTOS_OFFSET_POSE = new SparkFunOTOS.Pose2D(-1.38,1.89,Math.PI);
    }

    public static final class AllianceSelection {
        public static String SELECTED_TEAM;
        public final static String RED_TEAM = "red";
        public final static String BLUE_TEAM = "blue";
        public final static Pose2D RED_GOAL_POSE = new Pose2D(DistanceUnit.INCH,131.9, 135.4, AngleUnit.RADIANS,0); //Heading Unused
        public final static Pose2D BLUE_GOAL_POSE = new Pose2D(DistanceUnit.INCH,12.5, 135.4, AngleUnit.RADIANS,0); //Heading Unused


        public enum LimeLightPipelines {
            RED_TEAM(0),
            BLUE_TEAM(1),
            POSE_ESTIMATOR(2),
            ARTIFACT_TRACKING(3);

            private LimeLightPipelines(int value) {
            }
        }
    }


    public static final class PathNames {
        public final static String RED_START_SHOOT = "RED_START_SHOOT";
    }






}
