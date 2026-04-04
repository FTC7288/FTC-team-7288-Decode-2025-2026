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
                .addPath(new BezierLine(Poses.RED_START_POSE_FRONT, Poses.shootPose))
                .setLinearHeadingInterpolation(Poses.RED_START_POSE_FRONT.getHeading(), Poses.shootPose.getHeading())
                .build();

        PathChain RED_SHOOT_bPICKUP_ONE_PATH = follower.pathBuilder()
                        .addPath(new BezierLine(Poses.shootPose, Poses.pickupPoseOnePre))
                        .setLinearHeadingInterpolation(Poses.shootPose.getHeading(), Poses.pickupPoseOnePre.getHeading())
                        .build();

        PathChain RED_bPICKUP_aPICKUP_ONE_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.pickupPoseOnePre, Poses.pickupPoseOnePost))
                .setLinearHeadingInterpolation(Poses.pickupPoseOnePre.getHeading(), Poses.pickupPoseOnePost.getHeading())
                .build();

        PathChain RED_aPICKUP_SHOOT_PATH = follower.pathBuilder()
                .addPath(new BezierLine(Poses.pickupPoseOnePost, Poses.shootPose))
                .setLinearHeadingInterpolation(Poses.pickupPoseOnePost.getHeading(), Poses.shootPose.getHeading())
                .build();


        pathMap.put(PathNames.RED_START_SHOOT, RED_START_SHOOT_PATH);
        pathMap.put("RED_SHOOT_bPICKUP", RED_SHOOT_bPICKUP_ONE_PATH);
        pathMap.put("RED_bPICKUP_aPICKUP", RED_bPICKUP_aPICKUP_ONE_PATH);
        pathMap.put("RED_aPICKUP_SHOOT", RED_aPICKUP_SHOOT_PATH);
    }









}
