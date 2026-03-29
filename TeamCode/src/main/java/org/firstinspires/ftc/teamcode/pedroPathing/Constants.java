package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Global.Constants.*;


public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(PedroPathingConstants.ROBOT_MASS_KG)
//            .useSecondaryHeadingPIDF(true)
            .headingPIDFCoefficients(PedroPathingConstants.HEADING_PIDF_COEFFICIENTS)
            //            .secondaryHeadingPIDFCoefficients(PedroPathingConstants.SECONDARY_HEADING_PIDF_COEFFICIENTS);
            .predictiveBrakingCoefficients(PedroPathingConstants.PREDICTIVE_BRAKING_COEFFICIENTS)
            .centripetalScaling(0);

    public static MecanumConstants mecanumConstants = new MecanumConstants()
            .maxPower(1)
            .leftFrontMotorName(HardwareNames.DRIVE_MOTORS[0])
            .rightFrontMotorName(HardwareNames.DRIVE_MOTORS[1])
            .leftRearMotorName(HardwareNames.DRIVE_MOTORS[2])
            .rightRearMotorName(HardwareNames.DRIVE_MOTORS[3])
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(PedroPathingConstants.FORWARD_VELOCITY)
            .yVelocity(PedroPathingConstants.LATERAL_VELOCITY);

    public static OTOSConstants localizerConstants = new OTOSConstants()
            .hardwareMapName(HardwareNames.OTOS)
            .linearUnit(DistanceUnit.INCH)
            .angleUnit(AngleUnit.RADIANS)
            .linearScalar(PedroPathingConstants.LINEAR_SCALAR)
            .angularScalar(PedroPathingConstants.ANGULAR_SCALAR)
            .offset(PedroPathingConstants.OTOS_OFFSET_POSE);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(mecanumConstants)
                .OTOSLocalizer(localizerConstants)
                .build();
    }
}