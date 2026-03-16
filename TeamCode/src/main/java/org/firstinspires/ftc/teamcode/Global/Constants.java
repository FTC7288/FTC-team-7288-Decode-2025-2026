package org.firstinspires.ftc.teamcode.Global;



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
    }


    public final static class TurretSubsystem {
        public enum TurretPositionSelector {
            OFF,
            ON
        }
    }


    public final static class FlywheelSubsystem {
        public enum FlywheelSpeedSelector {
            OFF,
            ON
        }
    }


    public final static class IndexerSubsystem {
        public final static double LAUNCH_POSITION = 0.23;
        public final static double FIRST_POSITION = 0.32;
        public final static double SECOND_POSITION = 0.52;
        public final static double THIRD_POSITION = 0.71;

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


    public enum LimeLightPipelines {
        RED_TEAM(0),
        BLUE_TEAM(1),
        POSE_ESTIMATOR(2),
        ARTIFACT_TRACKING(3);

        private LimeLightPipelines(int value) {}
    }








}
