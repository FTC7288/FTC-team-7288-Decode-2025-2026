package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;


import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.MecanumDrive;

public class Drive extends SubsystemBase {
    Robot robot = Robot.getInstance();
    MecanumDrive mecanumDrive;



    double botHeading = 0;
    double offsetAngle = 0;

    public Orientation imuAngles;

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
        botHeading = imuAngles.firstAngle;
        return -botHeading - offsetAngle;
    }

    public void updateBotHeading() {
        offsetAngle = -imuAngles.firstAngle;
    }

    public void updateIMUOrientation() {
        imuAngles = robot.imu.getAngularOrientation();
    }

    // TODO: FINISH PLEASE
    public Pose3D getRobotPose() {
        Pose3D limelightPose = robot.limelight.getPose(imuAngles.firstAngle);




        return null;
    }


}
