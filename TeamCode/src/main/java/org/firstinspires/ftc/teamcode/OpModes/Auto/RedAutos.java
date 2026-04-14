package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.telemetry.SelectScope;
import com.pedropathing.telemetry.SelectableOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.FollowPath;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.InitializeAutoCommand;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.LaunchCommand;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Paths;
import org.firstinspires.ftc.teamcode.Global.Poses;
import org.firstinspires.ftc.teamcode.Global.Robot;


import java.util.function.Consumer;
import java.util.function.Supplier;

// TODO: Finish adding in the needed thingamajigs for the selectable thingamajig (im so pro at naming)
@Autonomous(name = "RED", group = "RED")
public class RedAutos extends SelectableOpMode {
    public RedAutos() {
        super("Select an Opmode: ", main -> {
            main.folder("Front: ", sub -> {
                sub.add("Front Nine ", FrontNineBall::new);
            });
            main.folder("Back: ", sub -> {

            });
        });
    }

}



    class FrontNineBall extends CommandOpMode {
        Robot robot = Robot.getInstance();
        Paths paths = new Paths();

        @Override
        public void initialize() {
            org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.SELECTED_TEAM = org.firstinspires.ftc.teamcode.Global.Constants.AllianceSelection.RED_TEAM;
            Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.RADIANS, Math.toRadians(45));

            CommandScheduler.getInstance().reset();
            CommandScheduler.getInstance();

            robot.init(hardwareMap);
            robot.follower.setStartingPose(Poses.RED_FRONT_START_POSE);
            paths.generatePaths(robot.follower);

            schedule(
                    new RunCommand(() -> robot.follower.update()),
                    new InitializeAutoCommand(),
                    new SequentialCommandGroup(
                            new FollowPath(robot.follower, paths.pathMap.get(Constants.PathNames.RED_START_SHOOT)),
                            new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                            new ParallelDeadlineGroup(
                                    new FollowPath(robot.follower, paths.pathMap.get("RED_SHOOT_sPICKUP_ONE")),
                                    new InstantCommand(() -> robot.intake.setIntakeStateIntake()),
                                    new RunCommand(() -> robot.indexer.interruptForLaunch(false))),

                            new FollowPath(robot.follower, paths.pathMap.get("RED_sPICKUP_ePICKUP_ONE"), 0.5),

                            new FollowPath(robot.follower, paths.pathMap.get("RED_ePICKUP_SHOOT_ONE")),

                            new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                            new ParallelDeadlineGroup(
                                    new FollowPath(robot.follower, paths.pathMap.get("RED_SHOOT_sPICKUP_TWO")),
                                    new InstantCommand(() -> robot.intake.setIntakeStateIntake()),
                                    new RunCommand(() -> robot.indexer.interruptForLaunch(false))),

                            new FollowPath(robot.follower, paths.pathMap.get("RED_sPICKUP_ePICKUP_TWO")),

                            new FollowPath(robot.follower, paths.pathMap.get("RED_ePICKUP_SHOOT_TWO")),
                            new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand())


                    )
            );
        }

        @Override
        public void run() {
            CommandScheduler.getInstance().run();
        }

    }

