package org.firstinspires.ftc.teamcode.OpModes.Auto;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Global.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class TestAuto extends OpMode {
    Robot robot = Robot.getInstance();
    Follower follower;

    Pose pose2 = new Pose(72, 72, Math.toRadians(45));
    Pose pose1 = new Pose(123.8, 122.7, Math.toRadians(45));

    Pose ctrl = new Pose(36.5, 71.5);

    PathChain testPath;

    private boolean thing = false;
    @Override
    public void init() {
        robot.init(hardwareMap);
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(pose1);

        testPath = follower.pathBuilder()
                .addPath(new BezierLine(pose1, pose2))
                .setLinearHeadingInterpolation(pose1.getHeading(), pose2.getHeading())
                .build();
    }

    @Override
    public void loop() {
        follower.update();
        if (thing) {
            follower.followPath(testPath,true);
        }
        thing = true;
    }
}
