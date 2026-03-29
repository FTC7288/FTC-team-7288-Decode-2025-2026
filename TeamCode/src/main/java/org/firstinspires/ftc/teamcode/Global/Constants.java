package org.firstinspires.ftc.teamcode.Global;


import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

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
    }


    public final static class HardwareInitialization {
        public final static String IMUParametersJsonFileName = "BNO055IMUCalibration.json";
        public final static int LIMELIGHT_POLLING_HZ = 100;

        public static final double FLYWHEEL_ENCODER_TOLERANCE = 20;

    }


    public final static class IntakeSubsystem {
        public final static double INTAKE_SPEED = 1;
        public final static double OFF_SPEED = 0;
        public final static double OUTTAKE_SPEED = -1;

        public final static double TRANSFER_POSITION = 0.37;
        public final static double INTAKE_POSITION = 0.7;
        public final static double NEUTRAL_POSITION = 0.5;

        public final static double INTAKE_ANALOG_IN = 2;

        public enum INTAKE_POSITIONS {
            INTAKE,
            TRANSFER,
            NEUTRAL
        }
    }


    public final static class TurretSubsystem {
        public enum TurretPositionSelector {
            OFF,
            ON
        }
    }


    public final static class FlywheelSubsystem {
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
            SECOND,
            THIRD,
            FULL
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


    public enum LimeLightPipelines {
        RED_TEAM(0),
        BLUE_TEAM(1),
        POSE_ESTIMATOR(2),
        ARTIFACT_TRACKING(3);

        private LimeLightPipelines(int value) {}
    }








}
