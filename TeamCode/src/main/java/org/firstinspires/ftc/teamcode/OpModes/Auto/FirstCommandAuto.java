package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.FollowPath;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.LaunchCommand;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Paths;
import org.firstinspires.ftc.teamcode.Global.Poses;
import org.firstinspires.ftc.teamcode.Global.Robot;

@Autonomous
public class FirstCommandAuto extends CommandOpMode {
    Robot robot = Robot.getInstance();
    Paths paths = new Paths();



    @Override
    public void initialize() {
        super.reset();
        CommandScheduler.getInstance();

        org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.SELECTED_TEAM = org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.RED_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH,1,1, AngleUnit.RADIANS,0);

        robot.init(hardwareMap);
        robot.follower.setStartingPose(Poses.RED_START_POSE_FRONT);
        paths.generatePaths(robot.follower);


        schedule(
                new RunCommand(() -> robot.follower.update()),
                new ParallelCommandGroup(
                        new InstantCommand(() -> robot.flywheel.turnFlywheelOn()),
                        new InstantCommand(() -> robot.indexer.indexerPositionSelector = Constants.IndexerSubsystem.IndexerPositionSelector.THIRD),
                        new InstantCommand(() -> robot.intake.setIntakeStateNeutral())),

                new SequentialCommandGroup(
                        new FollowPath(robot.follower, paths.pathMap.get(Constants.PathNames.RED_START_SHOOT)),

                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                        new ParallelDeadlineGroup(
                                new FollowPath(robot.follower, paths.pathMap.get("RED_SHOOT_bPICKUP")),
                                new InstantCommand(() -> robot.intake.setIntakeStateIntake()),
                                new RunCommand(() -> robot.indexer.interruptForLaunch(false))),

                        new FollowPath(robot.follower, paths.pathMap.get("RED_bPICKUP_aPICKUP")),

                        new FollowPath(robot.follower, paths.pathMap.get("RED_aPICKUP_SHOOT")),

                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand())
                )

        );
    }



    @Override
    public void run() {
        super.run();
        telemetry.addData("Follower is busy: ", robot.follower.isBusy());
        telemetry.update();
    }
}
