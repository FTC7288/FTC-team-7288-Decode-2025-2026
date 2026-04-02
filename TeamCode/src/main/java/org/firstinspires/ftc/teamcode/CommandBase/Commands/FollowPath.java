package org.firstinspires.ftc.teamcode.CommandBase.Commands;


import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;



public class FollowPath extends CommandBase {
    Follower follower;
    PathChain desiredPath;
    double maxSpeed;
    boolean holdEnd;


    public FollowPath(Follower follower, PathChain desiredPath) {
        this.follower = follower;
        this.desiredPath = desiredPath;
        this.maxSpeed = 1;
        this.holdEnd = true;

    }

    public FollowPath(Follower follower, PathChain desiredPath, double maxSpeed) {
        this.follower = follower;
        this.desiredPath = desiredPath;
        this.maxSpeed = maxSpeed;
        this.holdEnd = true;
    }

    public FollowPath(Follower follower, PathChain desiredPath, double maxSpeed, boolean holdEnd) {
        this.follower = follower;
        this.desiredPath = desiredPath;
        this.maxSpeed = maxSpeed;
        this.holdEnd = holdEnd;
    }

    public FollowPath(Follower follower, PathChain desiredPath, boolean holdEnd) {
        this.follower = follower;
        this.desiredPath = desiredPath;
        this.maxSpeed = 1;
        this.holdEnd = holdEnd;
    }



    @Override
    public void initialize() {
        follower.followPath(desiredPath,maxSpeed,holdEnd);
    }

    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }

}
