package org.firstinspires.ftc.teamcode.Global;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Indexer;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.LED;
import org.firstinspires.ftc.teamcode.CommandBase.Subsystems.LimeLight;

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
//    public Motor.Encoder turretEncoder, flywheelEncoder;


    public Servo indexerServo, intakeRightServo, intakeLeftServo, ledServo;
    public BNO055IMU imu;
    public AnalogInput indexerAnalog, intakeAnalog;
    public DigitalChannel breakBeam;
    public Limelight3A limelight3A;

    public Intake intake;
    public Drive drive;
    public LimeLight limelight;
    public Indexer indexer;
    public Flywheel flywheel;
    public LED led;


    // Init hardware bellow
    public void init(HardwareMap hardwareMap) {
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

//        turretEncoder = turretMotor.encoder;
//        turretEncoder.getCorrectedVelocity();

        //Flywheel Motors
        flywheelTopMotor = new MotorEx(hardwareMap, Constants.HardwareNames.FLYWHEEL_TOP);
        flywheelBottomMotor = new MotorEx(hardwareMap, Constants.HardwareNames.FLYWHEEL_BOTTOM);

        flywheelTopMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        flywheelBottomMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        flywheelTopMotor.setInverted(true);
        flywheelBottomMotor.setInverted(false);

//        flywheelEncoder = flywheelTopMotor.encoder;
//        flywheelEncoder.getCorrectedVelocity();


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


        // Analog Inputs
        indexerAnalog = hardwareMap.get(AnalogInput.class, "indexerAnalog");
        intakeAnalog = hardwareMap.get(AnalogInput.class, "intakeAnalog");

        // Digital Inputs
        breakBeam = hardwareMap.get(DigitalChannel.class, "breakBeam");

        // Digital Inputs

        // LimeLight
        limelight3A = hardwareMap.get(Limelight3A.class, Constants.HardwareNames.LIMELIGHT);

        drive = new Drive();
        intake = new Intake();
        indexer = new Indexer();
        flywheel = new Flywheel();
        led = new LED();

        limelight = new LimeLight();
        limelight.start();

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
