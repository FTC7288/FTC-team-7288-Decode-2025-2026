package org.firstinspires.ftc.teamcode.OpModes.Auto;


import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.draw;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.CoordinateSystem;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Global.Paths;
import org.firstinspires.ftc.teamcode.Global.Poses;
import org.firstinspires.ftc.teamcode.Global.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class TestAuto extends OpMode {
    Robot robot = Robot.getInstance();
    private final Pose startPose = new Pose(72, 72, Math.toRadians(0));
    private final Pose interPose = new Pose(24 + 72, -24 + 72, Math.toRadians(90));
    private final Pose endPose = new Pose(24 + 72, 24 + 72, Math.toRadians(45));

    public Pose start = new Pose(22.07,121.15,Math.toRadians(-225));
    public Pose end = new Pose(30,121.15, Math.toRadians(-225));

    private PathChain triangle;

    /**
     * This runs the OpMode, updating the Follower as well as printing out the debug statements to
     * the Telemetry, as well as the Panels.
     */



    @Override
    public void loop() {
        follower.update();


        telemetry.addData("OTOS X: ", robot.otos.getPosition().x);
        telemetry.addData("OTOS Y: ", robot.otos.getPosition().y);

        telemetry.addData("Follower X: ", follower.getPose().getX());
        telemetry.addData("Follower Y: ", follower.getPose().getY());



    }

    @Override
    public void init() {
        org.firstinspires.ftc.teamcode.Global.Constants.HardwareInitialization.AUTO = true;
        org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.SELECTED_TEAM = org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.RED_TEAM;
        org.firstinspires.ftc.teamcode.Global.Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.RADIANS, Math.toRadians(45));
        org.firstinspires.ftc.teamcode.Global.Constants.HardwareInitialization.OFFSET_ANGLE = 0;

        robot.init(hardwareMap);

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(start);

    }


    public void init_loop() {
        follower.update();
    }

    public void start() {

        PathChain blue= follower.pathBuilder()
                .addPath(new BezierLine(start, end))
                .setLinearHeadingInterpolation(start.getHeading(), end.getHeading())
                .build();


        follower.followPath(blue, true);
    }


}
