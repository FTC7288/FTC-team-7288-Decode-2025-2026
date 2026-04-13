package org.firstinspires.ftc.teamcode.Global;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.Global.Constants.PathNames;
import java.util.ArrayList;
import java.util.HashMap;

public class Paths {
    public HashMap<String,PathChain> pathMap = new HashMap<>();

    /*
     *  PATH NAMING RULES:
     *
     *  Order:
     *      1. Team
     *      2. Start Pose
     *      3. End Pose
     *      4. Type Path at end
     *
     *  Punctuation:
     *      1. All caps
     *      2. Underscores for spaces
     *
     *  Ex: RED_START_SHOOT
     */
    public void generatePaths(Follower follower) {
        PathChain RED_START_SHOOT_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_START_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_START_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain RED_SHOOT_bPICKUP_ONE_PATH = follower.pathBuilder()
                        .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_FIRST_sPICKUP_POSE))
                        .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading())
                        .build();

        PathChain RED_sPICKUP_ePICKUP_ONE_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_sPICKUP_POSE, Poses.RED_FRONT_FIRST_ePICKUP_POSE))
                    .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        PathChain RED_ePICKUP_SHOOT_ONE_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_ePICKUP_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain RED_SHOOT_sPICKUP_TWO_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        PathChain RED_sPICKUP_ePICKUP_TWO_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_sPICKUP_POSE,Poses.RED_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        PathChain RED_ePICKUP_SHOOT_TWO_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_ePICKUP_POSE,Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        pathMap.put(PathNames.RED_START_SHOOT, RED_START_SHOOT_PATH);
        pathMap.put("RED_SHOOT_sPICKUP_ONE", RED_SHOOT_bPICKUP_ONE_PATH);
        pathMap.put("RED_sPICKUP_ePICKUP_ONE", RED_sPICKUP_ePICKUP_ONE_PATH);
        pathMap.put("RED_ePICKUP_SHOOT_ONE", RED_ePICKUP_SHOOT_ONE_PATH);
        pathMap.put("RED_SHOOT_sPICKUP_TWO", RED_SHOOT_sPICKUP_TWO_PATH);
        pathMap.put("RED_sPICKUP_ePICKUP_TWO", RED_sPICKUP_ePICKUP_TWO_PATH);
        pathMap.put("RED_ePICKUP_SHOOT_TWO",RED_ePICKUP_SHOOT_TWO_PATH);

    }









}
