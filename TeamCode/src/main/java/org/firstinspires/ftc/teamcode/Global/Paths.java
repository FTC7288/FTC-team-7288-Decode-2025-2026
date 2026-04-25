package org.firstinspires.ftc.teamcode.Global;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;

import com.pedropathing.paths.PathChain;


public class Paths {

    public PathChain RED_START_SHOOT_FRONT, RED_SHOOT_sPICKUP_ONE_FRONT, RED_sPICKUP_ePICKUP_ONE_FRONT,RED_ePICKUP_SHOOT_ONE_FRONT,RED_SHOOT_sPICKUP_TWO_FRONT,RED_sPICKUP_ePICKUP_TWO_FRONT,RED_ePICKUP_SHOOT_TWO_FRONT,RED_DUMP_FRONT,RED_DUMP_SHOOT_FRONT,RED_MOVE_OFF_FRONT;
    public PathChain BLUE_START_SHOOT_FRONT, BLUE_SHOOT_sPICKUP_ONE_FRONT, BLUE_sPICKUP_ePICKUP_ONE_FRONT, BLUE_ePICKUP_SHOOT_ONE_FRONT, BLUE_SHOOT_sPICKUP_TWO_FRONT, BLUE_sPICKUP_ePICKUP_TWO_FRONT, BLUE_ePICKUP_SHOOT_TWO_FRONT,BLUE_DUMP_FRONT,BLUE_DUMP_SHOOT_FRONT,BLUE_MOVE_OFF_FRONT;

    public PathChain RED_START_LINE_PICKUP_ONE_BACK, RED_sPICKUP_ePICKUP_LINE_ONE_BACK,RED_ePICKUP_SHOOT_LINE_ONE_BACK,RED_SHOOT_PICKUP_CORNER_BACK,RED_PICKUP_CORNER_SHOOT_BACK,RED_SHOOT_PICKUP_CURVE_CORNER_BACK,RED_PICKUP_CURVE_CORNER_SHOOT_BACK,RED_MOVE_OFF_BACK;
    public PathChain BLUE_START_LINE_PICKUP_ONE_BACK, BLUE_sPICKUP_ePICKUP_LINE_ONE_BACK,BLUE_ePICKUP_SHOOT_LINE_ONE_BACK,BLUE_SHOOT_PICKUP_CORNER_BACK,BLUE_PICKUP_CORNER_SHOOT_BACK,BLUE_SHOOT_PICKUP_CURVE_CORNER_BACK,BLUE_PICKUP_CURVE_CORNER_SHOOT_BACK,BLUE_MOVE_OFF_BACK;

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
        RED_START_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_START_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_START_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        RED_SHOOT_sPICKUP_ONE_FRONT= follower.pathBuilder()
                        .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_FIRST_sPICKUP_POSE))
                        .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading())
                        .build();

        RED_sPICKUP_ePICKUP_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_sPICKUP_POSE, Poses.RED_FRONT_FIRST_ePICKUP_POSE))
                    .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        RED_ePICKUP_SHOOT_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_ePICKUP_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        RED_SHOOT_sPICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        RED_sPICKUP_ePICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_sPICKUP_POSE,Poses.RED_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        RED_ePICKUP_SHOOT_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SECOND_ePICKUP_POSE,Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        RED_DUMP_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_FIRST_ePICKUP_POSE, Poses.RED_FRONT_DUMP_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.RED_FRONT_DUMP_POSE.getHeading())
                .build();

        RED_DUMP_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_DUMP_POSE, Poses.RED_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_DUMP_POSE.getHeading(), Poses.RED_FRONT_SHOOT_POSE.getHeading())
                .build();

        RED_MOVE_OFF_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_FRONT_SHOOT_POSE, Poses.RED_FRONT_MOVE_OFF_POSE))
                .setLinearHeadingInterpolation(Poses.RED_FRONT_SHOOT_POSE.getHeading(), Poses.RED_FRONT_MOVE_OFF_POSE.getHeading())
                .build();


        // follower.pathBuilder().addPath(new BezierLine()).setLinearHeadingInterpolation().build();

        //Red Back
        RED_START_LINE_PICKUP_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_START_POSE, Poses.RED_BACK_sPICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.RED_BACK_START_POSE.getHeading(), Poses.RED_BACK_sPICKUP_POSE_LINE.getHeading())
                .build();

        RED_sPICKUP_ePICKUP_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_sPICKUP_POSE_LINE, Poses.RED_BACK_ePICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.RED_BACK_sPICKUP_POSE_LINE.getHeading(), Poses.RED_BACK_ePICKUP_POSE_LINE.getHeading())
                .build();

        RED_ePICKUP_SHOOT_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_ePICKUP_POSE_LINE, Poses.RED_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.RED_BACK_ePICKUP_POSE_LINE.getHeading(),Poses.RED_BACK_START_POSE.getHeading())
                .build();

        RED_SHOOT_PICKUP_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_START_POSE,Poses.RED_BACK_sPICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        RED_PICKUP_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_sPICKUP_POSE_CORNER ,Poses.RED_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.RED_BACK_sPICKUP_POSE_CORNER.getHeading(),Poses.RED_BACK_START_POSE.getHeading())
                .build();

        RED_SHOOT_PICKUP_CURVE_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierCurve(Poses.RED_BACK_START_POSE,Poses.RED_BACK_CTRL_ONE_POINT,Poses.RED_BACK_CTRL_TWO_POINT,Poses.RED_BACK_CURVE_PICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        RED_PICKUP_CURVE_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_CURVE_PICKUP_POSE_CORNER,Poses.RED_BACK_START_POSE))
                .setLinearHeadingInterpolation(Math.toRadians(90),Poses.RED_BACK_START_POSE.getHeading())
                .build();

        RED_MOVE_OFF_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.RED_BACK_START_POSE, Poses.RED_BACK_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.RED_BACK_START_POSE.getHeading(), Poses.RED_BACK_MOVEOFF_POSE.getHeading())
                .build();


        // Blue Front
        BLUE_START_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_START_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_START_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_SHOOT_sPICKUP_ONE_FRONT= follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_FIRST_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE, Poses.BLUE_FRONT_FIRST_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_SHOOT_sPICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE,Poses.BLUE_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE,Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_DUMP_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE, Poses.BLUE_FRONT_DUMP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.BLUE_FRONT_DUMP_POSE.getHeading())
                .build();

        BLUE_DUMP_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_DUMP_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_DUMP_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_MOVE_OFF_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_MOVEOFF_POSE.getHeading())
                .build();



       //Blue Back
        BLUE_START_LINE_PICKUP_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE, Poses.BLUE_BACK_sPICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_START_POSE.getHeading(), Poses.BLUE_BACK_sPICKUP_POSE_LINE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_sPICKUP_POSE_LINE, Poses.BLUE_BACK_ePICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_sPICKUP_POSE_LINE.getHeading(), Poses.BLUE_BACK_ePICKUP_POSE_LINE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_ePICKUP_POSE_LINE, Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_ePICKUP_POSE_LINE.getHeading(),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_SHOOT_PICKUP_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE,Poses.BLUE_BACK_sPICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        BLUE_PICKUP_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_sPICKUP_POSE_CORNER ,Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_sPICKUP_POSE_CORNER.getHeading(),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_SHOOT_PICKUP_CURVE_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierCurve(Poses.BLUE_BACK_START_POSE,Poses.BLUE_BACK_CTRL_ONE_POINT,Poses.BLUE_BACK_CTRL_TWO_POINT,Poses.BLUE_BACK_CURVE_PICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        BLUE_PICKUP_CURVE_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_CURVE_PICKUP_POSE_CORNER,Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Math.toRadians(90),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_MOVE_OFF_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE, Poses.BLUE_BACK_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_START_POSE.getHeading(), Poses.BLUE_BACK_MOVEOFF_POSE.getHeading())
                .build();



    }


    public void generateBlue(Follower follower) {
        // Blue Front
        BLUE_START_SHOOT_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_START_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_START_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_SHOOT_sPICKUP_ONE_FRONT= follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_FIRST_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE, Poses.BLUE_FRONT_FIRST_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_sPICKUP_POSE.getHeading(), Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_ONE_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE, Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_SHOOT_sPICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_SECOND_sPICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE,Poses.BLUE_FRONT_SECOND_ePICKUP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_sPICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_TWO_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE,Poses.BLUE_FRONT_SHOOT_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SECOND_ePICKUP_POSE.getHeading(),Poses.BLUE_FRONT_SHOOT_POSE.getHeading())
                .build();

        BLUE_DUMP_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE, Poses.BLUE_FRONT_DUMP_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_FIRST_ePICKUP_POSE.getHeading(), Poses.BLUE_FRONT_DUMP_POSE.getHeading())
                .build();

        BLUE_MOVE_OFF_FRONT = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_FRONT_SHOOT_POSE, Poses.BLUE_FRONT_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_FRONT_SHOOT_POSE.getHeading(), Poses.BLUE_FRONT_MOVEOFF_POSE.getHeading())
                .build();



        //Blue Back
        BLUE_START_LINE_PICKUP_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE, Poses.BLUE_BACK_sPICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_START_POSE.getHeading(), Poses.BLUE_BACK_sPICKUP_POSE_LINE.getHeading())
                .build();

        BLUE_sPICKUP_ePICKUP_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_sPICKUP_POSE_LINE, Poses.BLUE_BACK_ePICKUP_POSE_LINE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_sPICKUP_POSE_LINE.getHeading(), Poses.BLUE_BACK_ePICKUP_POSE_LINE.getHeading())
                .build();

        BLUE_ePICKUP_SHOOT_LINE_ONE_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_ePICKUP_POSE_LINE, Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_ePICKUP_POSE_LINE.getHeading(),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_SHOOT_PICKUP_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE,Poses.BLUE_BACK_sPICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        BLUE_PICKUP_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_sPICKUP_POSE_CORNER ,Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_sPICKUP_POSE_CORNER.getHeading(),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_SHOOT_PICKUP_CURVE_CORNER_BACK = follower.pathBuilder()
                .addPath(new BezierCurve(Poses.BLUE_BACK_START_POSE,Poses.BLUE_BACK_CTRL_ONE_POINT,Poses.BLUE_BACK_CTRL_TWO_POINT,Poses.BLUE_BACK_CURVE_PICKUP_POSE_CORNER))
                .setTangentHeadingInterpolation()
                .build();

        BLUE_PICKUP_CURVE_CORNER_SHOOT_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_CURVE_PICKUP_POSE_CORNER,Poses.BLUE_BACK_START_POSE))
                .setLinearHeadingInterpolation(Math.toRadians(90),Poses.BLUE_BACK_START_POSE.getHeading())
                .build();

        BLUE_MOVE_OFF_BACK = follower.pathBuilder()
                .addPath(new BezierLine(Poses.BLUE_BACK_START_POSE, Poses.BLUE_BACK_MOVEOFF_POSE))
                .setLinearHeadingInterpolation(Poses.BLUE_BACK_START_POSE.getHeading(), Poses.BLUE_BACK_MOVEOFF_POSE.getHeading())
                .build();
    }









}
