package org.firstinspires.ftc.teamcode.Global;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Indexer;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.LED;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.LimeLight;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Turret;

import Util.Photon.PhotonCore;

public class Robot {

    public static Robot INSTANCE = new Robot();
    public static Robot getInstance() {
        return INSTANCE;
    }


    /*
     *  Hardware Goes Here
     */

//    public DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, turretMotor, flywheelTopMotor, flywheelBottomMotor, intakeMotor;
    public MotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, turretMotor, flywheelTopMotor, flywheelBottomMotor, intakeMotor;
    public Motor.Encoder turretEncoder, flywheelEncoder;
    public Servo indexerServo, intakeRightServo, intakeLeftServo, ledServo;
    public BNO055IMU imu;
    public AnalogInput indexerAnalog, intakeAnalog;
    public DigitalChannel breakBeam;
    public Limelight3A limelight3A;
    public SparkFunOTOS otos;

    public Intake intake;
    public Drive drive;
    public LimeLight limelight;
    public Indexer indexer;
    public Flywheel flywheel;
    public Turret turret;
    public LED led;

    public Follower follower;


    // Init hardware bellow
    public void init(HardwareMap hardwareMap) {
        follower = org.firstinspires.ftc.teamcode.pedroPathing.Constants.createFollower(hardwareMap);

        // Drive Motors
        frontLeftMotor = new MotorEx(hardwareMap, Constants.HardwareNames.DRIVE_MOTORS[0]);
        frontRightMotor = new MotorEx(hardwareMap, Constants.HardwareNames.DRIVE_MOTORS[1]);
        backLeftMotor = new MotorEx(hardwareMap, Constants.HardwareNames.DRIVE_MOTORS[2]);
        backRightMotor = new MotorEx(hardwareMap, Constants.HardwareNames.DRIVE_MOTORS[3]);

        frontLeftMotor.setInverted(true);
        backLeftMotor.setInverted(true);

        frontLeftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeftMotor.setRunMode(Motor.RunMode.RawPower);
        frontRightMotor.setRunMode(Motor.RunMode.RawPower);
        backLeftMotor.setRunMode(Motor.RunMode.RawPower);
        backRightMotor.setRunMode(Motor.RunMode.RawPower);


        // Turret Motor
        turretMotor = new MotorEx(hardwareMap, Constants.HardwareNames.TURRET);
        turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        turretMotor.setInverted(true);

        turretEncoder = turretMotor.encoder;

        //Flywheel Motors
        flywheelTopMotor = new MotorEx(hardwareMap, Constants.HardwareNames.FLYWHEEL_TOP);
        flywheelBottomMotor = new MotorEx(hardwareMap, Constants.HardwareNames.FLYWHEEL_BOTTOM);

        flywheelTopMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        flywheelBottomMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        flywheelTopMotor.setInverted(true);
        flywheelBottomMotor.setInverted(false);

        flywheelEncoder = flywheelTopMotor.encoder;


        //Intake Motor
        intakeMotor = new MotorEx(hardwareMap, Constants.HardwareNames.INTAKE);
        intakeMotor.setInverted(true);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        // Indexer Servo
        indexerServo = hardwareMap.get(Servo.class, Constants.HardwareNames.INDEXER_SERVO);

        // Intake Servos
        intakeRightServo = hardwareMap.get(Servo.class, Constants.HardwareNames.INTAKE_SERVOS[0]);
        intakeLeftServo = hardwareMap.get(Servo.class, Constants.HardwareNames.INTAKE_SERVOS[1]);
        intakeRightServo.setDirection(Servo.Direction.REVERSE);

        // IMU
        imu = hardwareMap.get(BNO055IMU.class, Constants.HardwareNames.IMU);
        imu.initialize(initializeIMUParameters());

        // LED
        ledServo = hardwareMap.get(Servo.class, Constants.HardwareNames.LED);

        // OTOS
        otos = hardwareMap.get(SparkFunOTOS.class, Constants.HardwareNames.OTOS);
        otos.setLinearUnit(DistanceUnit.INCH);
        otos.setAngularUnit(AngleUnit.RADIANS);
        otos.setLinearScalar(Constants.PedroPathingConstants.LINEAR_SCALAR);
        otos.setAngularScalar(Constants.PedroPathingConstants.ANGULAR_SCALAR);
        otos.setOffset(Constants.PedroPathingConstants.OTOS_OFFSET_POSE);
//        otos.setPosition(new SparkFunOTOS.Pose2D(
//                Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getX(DistanceUnit.INCH),
//                Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getY(DistanceUnit.INCH),
//                Constants.HardwareInitialization.INITIAL_ROBOT_POSE.getHeading(AngleUnit.RADIANS))
//        );



        // Analog Inputs
        indexerAnalog = hardwareMap.get(AnalogInput.class, Constants.HardwareNames.INDEXER_ANALOG);
        intakeAnalog = hardwareMap.get(AnalogInput.class, Constants.HardwareNames.INTAKE_ANALOG);

        // Digital Inputs
        breakBeam = hardwareMap.get(DigitalChannel.class, Constants.HardwareNames.BREAK_BEAM);

        // LimeLight
        limelight3A = hardwareMap.get(Limelight3A.class, Constants.HardwareNames.LIMELIGHT);

        drive = new Drive();
        intake = new Intake();
        indexer = new Indexer();
        flywheel = new Flywheel();
        turret = new Turret();

        led = new LED();

        limelight = new LimeLight();
        limelight.start();

//        follower = org.firstinspires.ftc.teamcode.pedroPathing.Constants.createFollower(hardwareMap);

        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        PhotonCore.EXPANSION_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
    }


    private BNO055IMU.Parameters initializeIMUParameters () {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = Constants.HardwareInitialization.IMUParametersJsonFileName;

        return parameters;
    }

}
