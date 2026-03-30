package org.firstinspires.ftc.teamcode.CommandBase.Commands;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


public class DriveTo extends CommandBase {
    static Follower follower;
    private static Pose lastPose;
    private Pose currentPose;

    public static void initializeDriveToCommands(HardwareMap hardwareMap, Pose startingPose) {
        if (follower == null) {
            follower = Constants.createFollower(hardwareMap);
        }
        lastPose = startingPose;
    }

    public DriveTo(Pose pose) {
        currentPose = pose;
    }

    @Override
    public void initialize() {
        Path path = new Path(new BezierLine(lastPose, currentPose));
        path.setLinearHeadingInterpolation(lastPose.getHeading(), currentPose.getHeading());
        follower.followPath(path);
        lastPose = currentPose;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }

}
