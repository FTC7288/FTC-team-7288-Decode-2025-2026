package org.firstinspires.ftc.teamcode.Global;

import com.arcrobotics.ftclib.kotlin.extensions.geometry.Pose2dExtKt;
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
     *      3. Start Pose
     *      4. End Pose
     *      5. Location
     *
     *  Punctuation:
     *      1. All caps
     *      2. Underscores for spaces
     *
     *  Ex: RED_START_SHOOT
     */
    public void generatePaths(Follower follower) {
        //Red Front
        PathChain RED_START_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_START_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_START_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain RED_SHOOT_sPICKUP_ONE_FRONT= follower.pathBuilder()
                        .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_FIRST_sPICKUP_POSE))
                        .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading())
                        .build();

        PathChain RED_sPICKUP_ePICKUP_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_sPICKUP_POSE, Poses.RED_FRONT_FIRST_ePICKUP_POSE))
                    .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        PathChain RED_ePICKUP_SHOOT_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_ePICKUP_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain RED_SHOOT_sPICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        PathChain RED_sPICKUP_ePICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_sPICKUP_POSE,Poses.RED_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        PathChain RED_ePICKUP_SHOOT_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_ePICKUP_POSE,Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain RED_MOVE_OFF_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_MOVE_OFF_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_MOVE_OFF_POSE.getHeading())
                .build();


        // follower.pathBuilder().addPath(new BezierLine()).setLinearHeadingInterpolation().build();

        //Red Back
        PathChain RED_START_SHOOT_ONE_BACK = follower.pathBuilder().addPath(new BezierLine(Poses.RED_BACK_START_POSE, Poses.RED_BACK_SHOOT_POSE)).setLinearHeadingInterpolation().build();

        // Blue Front
        //Red Front
        PathChain BLUE_START_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_START_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_START_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain BLUE_SHOOT_sPICKUP_ONE_FRONT= follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_FIRST_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading())
                .build();

        PathChain BLUE_sPICKUP_ePICKUP_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE, Poses.BLUE_FRONT_FIRST_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        PathChain BLUE_ePICKUP_SHOOT_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain BLUE_SHOOT_sPICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        PathChain BLUE_sPICKUP_ePICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE,Poses.BLUE_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        PathChain BLUE_ePICKUP_SHOOT_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE,Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        PathChain BLUE_MOVE_OFF_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_MOVEOFF_POSE.getHeading())
                .build();

        pathMap.put(PathNames.RED_START_SHOOT_FRONT, RED_START_SHOOT_FRONT);
        pathMap.put(PathNames.RED_SHOOT_sPICKUP_TWO_FRONT, RED_SHOOT_sPICKUP_ONE_FRONT);
        pathMap.put(PathNames.RED_SHOOT_sPICKUP_ONE_FRONT, RED_sPICKUP_ePICKUP_ONE_FRONT);
        pathMap.put(PathNames.RED_ePICKUP_SHOOT_ONE_FRONT, RED_ePICKUP_SHOOT_ONE_FRONT);
        pathMap.put(PathNames.RED_SHOOT_sPICKUP_TWO_FRONT, RED_SHOOT_sPICKUP_TWO_FRONT);
        pathMap.put(PathNames.RED_sPICKUP_ePICKUP_TWO_FRONT, RED_sPICKUP_ePICKUP_TWO_FRONT);
        pathMap.put(PathNames.RED_ePICKUP_SHOOT_TWO_FRONT,RED_ePICKUP_SHOOT_TWO_FRONT);
        pathMap.put(PathNames.RED_MOVE_OFF_FRONT, RED_MOVE_OFF_FRONT);

        pathMap.put(PathNames.BLUE_START_SHOOT_FRONT, BLUE_START_SHOOT_FRONT);
        pathMap.put(PathNames.BLUE_SHOOT_sPICKUP_TWO_FRONT, BLUE_SHOOT_sPICKUP_ONE_FRONT);
        pathMap.put(PathNames.BLUE_SHOOT_sPICKUP_ONE_FRONT, BLUE_sPICKUP_ePICKUP_ONE_FRONT);
        pathMap.put(PathNames.BLUE_ePICKUP_SHOOT_ONE_FRONT, BLUE_ePICKUP_SHOOT_ONE_FRONT);
        pathMap.put(PathNames.BLUE_SHOOT_sPICKUP_TWO_FRONT, BLUE_SHOOT_sPICKUP_TWO_FRONT);
        pathMap.put(PathNames.BLUE_sPICKUP_ePICKUP_TWO_FRONT, BLUE_sPICKUP_ePICKUP_TWO_FRONT);
        pathMap.put(PathNames.BLUE_ePICKUP_SHOOT_TWO_FRONT,BLUE_ePICKUP_SHOOT_TWO_FRONT);
        pathMap.put(PathNames.BLUE_MOVE_OFF_FRONT, BLUE_MOVE_OFF_FRONT);

    }









}
