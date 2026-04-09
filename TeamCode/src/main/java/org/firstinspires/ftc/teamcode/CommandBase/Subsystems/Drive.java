package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;



import com.arcrobotics.ftclib.command.SubsystemBase;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.MecanumDrive;

public class Drive extends SubsystemBase {
    Robot robot = Robot.getInstance();
    MecanumDrive mecanumDrive;



    private double offsetAngle = 0;

    private Orientation imuAngles;

    public Drive() {
        mecanumDrive = new MecanumDrive(
                robot.frontLeftMotor,
                robot.frontRightMotor,
                robot.backLeftMotor,
                robot.backRightMotor
        );
    }


    public void driveFieldCentric(double speedY, double speedX, double rotationX, double botHeading) {
        mecanumDrive.driveFieldCentric(
            speedY, speedX, rotationX, botHeading
        );
    }

    public double getBotHeading() {
        double botHeading = imuAngles.firstAngle;
        return -botHeading - offsetAngle;
    }

    public void updateBotHeading() {
        offsetAngle = -imuAngles.firstAngle;
    }

    public double getIMUOrientation() {
        return imuAngles.firstAngle;
    }

    public void updateIMUOrientation() {
        imuAngles = robot.imu.getAngularOrientation();
    }

    // TODO: Add Kalman filtering for MegaTag 2 pose and add on the velocity or acceleration feedforward to the pose for on the move shooting (not sure which one it needs to be tested)


    public Pose2D getRobotPose() {
        Pose3D limelightPose = robot.limelight.getPose();
        SparkFunOTOS.Pose2D otosPose = robot.otos.getPosition();
        SparkFunOTOS.Pose2D otosVelocity = robot.otos.getVelocity();

        return new Pose2D(DistanceUnit.INCH, otosPose.x, otosPose.y, AngleUnit.RADIANS, otosPose.h);
    }


}
