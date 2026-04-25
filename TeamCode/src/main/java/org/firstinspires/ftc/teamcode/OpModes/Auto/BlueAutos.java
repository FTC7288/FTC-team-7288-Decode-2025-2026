package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.telemetry.SelectableOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.FollowPath;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.IntakingCommand;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.LaunchCommand;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.TurnOffRobotCommand;
import org.firstinspires.ftc.teamcode.CommandBase.Commands.UpdateFollower;
import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Paths;
import org.firstinspires.ftc.teamcode.Global.Poses;
import org.firstinspires.ftc.teamcode.Global.Robot;

import Util.SelectableCommandOpMode;

// TODO: Finish adding in the needed thingamajigs for the selectable thingamajig (im so pro at naming)
@Autonomous(name = "BLUE", group = "BLUE")
public class BlueAutos extends SelectableOpMode {
    public BlueAutos() {
        super("Select an Opmode: ", main -> {
            main.folder("Front: ", sub -> {
                sub.add("Front Six ", BlueFrontSixBall::new);
                sub.add("Front Nine No Open ", BlueFrontNineBall::new);
                sub.add("Front Nine One Open ", BlueFrontNineOneOpen::new);
            });
            main.folder("Back: ", sub -> {
                sub.add("Back Nine Corner Cycle ", BlueBackNineBallCycle::new);
                sub.add("Back Nine First Row ", BlueBackNineBallRow::new);
            });
        });
    }

}

    class BlueFrontSixBall extends SelectableCommandOpMode {
        Robot robot = Robot.getInstance();
        Paths paths = new Paths();
        @Override
        public void initialize() {
            Constants.HardwareInitialization.AUTO = true;
            Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.BLUE_TEAM;
            Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.RADIANS, Math.toRadians(45));
            Constants.HardwareInitialization.OFFSET_ANGLE = 0;


            super.reset();

            robot.init(hardwareMap);
            robot.setStartingPos(Poses.BLUE_FRONT_START_POSE);


            paths.generatePaths(robot.follower);

            schedule(
                    new UpdateFollower(),

                    new InstantCommand(() -> robot.intake.setIntakeStateNeutral(),robot.intake),
                    new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),

                    new SequentialCommandGroup(
                            new FollowPath(robot.follower, paths.BLUE_START_SHOOT_FRONT, false),

                            new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                            new IntakingCommand(),
                            new FollowPath(robot.follower, paths.BLUE_SHOOT_sPICKUP_ONE_FRONT),
                            new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_ONE_FRONT, 0.5, true),
                            new WaitCommand(200),

                            new OuttakeCommand(true),
                            new FollowPath(robot.follower, paths.BLUE_ePICKUP_SHOOT_ONE_FRONT),

                            new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                            new FollowPath(robot.follower, paths.BLUE_MOVE_OFF_FRONT),

                            new TurnOffRobotCommand()
                    )
            );

        }

        @Override
        public void run() {

            super.run();


            telemetry.addData("OTOS X: ", robot.otos.getPosition().x);
            telemetry.addData("OTOS Y: ", robot.otos.getPosition().y);

            telemetry.addData("Follower X: ", robot.follower.getPose().getX());
            telemetry.addData("Follower Y: ", robot.follower.getPose().getY());

            telemetry.update();


        }

        @Override
        public void end() {
            robot.limelight.stop();
            Constants.HardwareInitialization.END_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(),AngleUnit.RADIANS, robot.follower.getHeading());
        }
    }




    class BlueFrontNineBall extends SelectableCommandOpMode {
        Robot robot = Robot.getInstance();
        Paths paths = new Paths();

        @Override
        public void initialize() {
            Constants.HardwareInitialization.AUTO = true;
            Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.BLUE_TEAM;
            Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, Poses.BLUE_FRONT_START_POSE.getX(),Poses.BLUE_FRONT_START_POSE.getY(), AngleUnit.RADIANS, Math.toRadians(Poses.BLUE_FRONT_START_POSE.getHeading()));
            Constants.HardwareInitialization.OFFSET_ANGLE = 0;


            super.reset();

            robot.init(hardwareMap);
            robot.setStartingPos(Poses.BLUE_FRONT_START_POSE);


            paths.generatePaths(robot.follower);

            super.schedule(
                    new UpdateFollower(),

                    new InstantCommand(() -> robot.intake.setIntakeStateNeutral(),robot.intake),
                    new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),

                    new SequentialCommandGroup(
                            new FollowPath(robot.follower, paths.BLUE_START_SHOOT_FRONT),

                            new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                            new IntakingCommand(),
                            new FollowPath(robot.follower, paths.BLUE_SHOOT_sPICKUP_ONE_FRONT),
                            new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_ONE_FRONT, 0.5, true),
                            new WaitCommand(200),


                            new OuttakeCommand(true),
                            new FollowPath(robot.follower, paths.BLUE_ePICKUP_SHOOT_ONE_FRONT),


                            new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                            new IntakingCommand(),
                            new FollowPath(robot.follower, paths.BLUE_SHOOT_sPICKUP_TWO_FRONT),
                            new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_TWO_FRONT, 0.5, true),
                            new WaitCommand(200),


                            new OuttakeCommand(true),
                            new FollowPath(robot.follower, paths.BLUE_ePICKUP_SHOOT_TWO_FRONT),

                            new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                            new FollowPath(robot.follower, paths.BLUE_MOVE_OFF_FRONT),

                            new TurnOffRobotCommand()

                    )
            );
        }

        @Override
        public void run() {super.run();}

        @Override
        public void end() {
            robot.limelight.stop();
            Constants.HardwareInitialization.OFFSET_ANGLE = 0;
            Constants.HardwareInitialization.END_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(),AngleUnit.RADIANS, robot.follower.getHeading());
        }
    }




class BlueFrontNineOneOpen extends SelectableCommandOpMode {
    Robot robot = Robot.getInstance();
    Paths paths = new Paths();

    @Override
    public void initialize() {
        Constants.HardwareInitialization.AUTO = true;
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.BLUE_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, Poses.BLUE_FRONT_START_POSE.getX(),Poses.BLUE_FRONT_START_POSE.getY(), AngleUnit.RADIANS, Math.toRadians(Poses.BLUE_FRONT_START_POSE.getHeading()));
        Constants.HardwareInitialization.OFFSET_ANGLE = 0;


        super.reset();

        robot.init(hardwareMap);
        robot.setStartingPos(Poses.BLUE_FRONT_START_POSE);

        paths.generatePaths(robot.follower);

        super.schedule(
                new UpdateFollower(),

                new InstantCommand(() -> robot.intake.setIntakeStateNeutral(),robot.intake),
                new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),

                new SequentialCommandGroup(
                        new FollowPath(robot.follower, paths.BLUE_START_SHOOT_FRONT),

                        new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                        new IntakingCommand(),
                        new FollowPath(robot.follower, paths.BLUE_SHOOT_sPICKUP_ONE_FRONT),
                        new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_ONE_FRONT, 0.5),
                        new WaitCommand(200),

                        new InstantCommand(() -> robot.intake.setIntakeStateNeutral()),
                        new FollowPath(robot.follower, paths.BLUE_DUMP_FRONT, 0.5),

                        new OuttakeCommand(false),
                        new FollowPath(robot.follower, paths.BLUE_DUMP_SHOOT_FRONT),

                        new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                        new IntakingCommand(),
                        new FollowPath(robot.follower, paths.BLUE_SHOOT_sPICKUP_TWO_FRONT),
                        new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_TWO_FRONT, 0.5),
                        new WaitCommand(200),

                        new OuttakeCommand(true),
                        new FollowPath(robot.follower, paths.BLUE_ePICKUP_SHOOT_TWO_FRONT),

                        new ParallelDeadlineGroup(new WaitCommand(1500), new LaunchCommand()),

                        new FollowPath(robot.follower, paths.BLUE_MOVE_OFF_FRONT),

                        new TurnOffRobotCommand()

                )
        );
    }

    @Override
    public void run() {super.run();}

    @Override
    public void end() {
        robot.limelight.stop();
        Constants.HardwareInitialization.END_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(),AngleUnit.RADIANS, robot.follower.getHeading());
    }
}











class BlueBackNineBallCycle extends SelectableCommandOpMode {
    Robot robot = Robot.getInstance();
    Paths paths = new Paths();

    @Override
    public void initialize() {
        Constants.HardwareInitialization.AUTO = true;
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.BLUE_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, Poses.BLUE_BACK_START_POSE.getX(),Poses.BLUE_BACK_START_POSE.getY(), AngleUnit.RADIANS, Math.toRadians(Poses.BLUE_BACK_START_POSE.getHeading()));
        Constants.HardwareInitialization.OFFSET_ANGLE = 68;


        super.reset();

        robot.init(hardwareMap);
        robot.setStartingPos(Poses.BLUE_FRONT_START_POSE);

        paths.generatePaths(robot.follower);

        super.schedule(
                new UpdateFollower(),

                new InstantCommand(() -> robot.turret.turnTurretOn(), robot.turret),
                new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),

                new SequentialCommandGroup(
                        new WaitCommand(2000),

                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                        new IntakingCommand(),
                        new FollowPath(robot.follower, paths.BLUE_SHOOT_PICKUP_CORNER_BACK),
                        new FollowPath(robot.follower, paths.BLUE_PICKUP_CORNER_SHOOT_BACK),


                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                        new IntakingCommand(),
                        new FollowPath(robot.follower, paths.BLUE_SHOOT_PICKUP_CORNER_BACK),
                        new FollowPath(robot.follower, paths.BLUE_PICKUP_CORNER_SHOOT_BACK),

                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                        new IntakingCommand(),
                        new FollowPath(robot.follower, paths.BLUE_SHOOT_PICKUP_CURVE_CORNER_BACK),
                        new FollowPath(robot.follower, paths.BLUE_PICKUP_CURVE_CORNER_SHOOT_BACK),


                        new ParallelDeadlineGroup(new WaitCommand(2000), new LaunchCommand()),

                        new FollowPath(robot.follower, paths.BLUE_MOVE_OFF_BACK),

                        new TurnOffRobotCommand()

                )
        );
    }

    @Override
    public void run() {super.run();}

    @Override
    public void end() {
        robot.limelight.stop();
        Constants.HardwareInitialization.END_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(),AngleUnit.RADIANS, robot.follower.getHeading());
    }
}









class BlueBackNineBallRow extends SelectableCommandOpMode {
    Robot robot = Robot.getInstance();
    Paths paths = new Paths();

    @Override
    public void initialize() {
        super.reset();

        Constants.HardwareInitialization.AUTO = true;
        Constants.AllianceSelection.SELECTED_TEAM = Constants.AllianceSelection.BLUE_TEAM;
        Constants.HardwareInitialization.INITIAL_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, Poses.BLUE_BACK_START_POSE.getX() ,Poses.BLUE_BACK_START_POSE.getY(), AngleUnit.DEGREES, Math.toRadians(Poses.BLUE_BACK_START_POSE.getHeading()));
        Constants.HardwareInitialization.OFFSET_ANGLE = 68;


        robot.init(hardwareMap);
        robot.setStartingPos(Poses.BLUE_BACK_START_POSE);

        robot.flywheelEncoder.reset();
        robot.turretEncoder.reset();

        paths.generatePaths(robot.follower);

        super.schedule(
                new UpdateFollower(),

                new InstantCommand(() -> robot.flywheel.turnFlywheelOn(), robot.flywheel),
                new InstantCommand(() -> robot.turret.turnTurretOn(), robot.turret),


                new SequentialCommandGroup(

                    new WaitCommand(5000),

                    new ParallelDeadlineGroup(
                            new WaitCommand(1500),
                            new LaunchCommand()
                    ),


                    new IntakingCommand(),

                    new FollowPath(robot.follower, paths.BLUE_START_LINE_PICKUP_ONE_BACK),
                    new FollowPath(robot.follower, paths.BLUE_sPICKUP_ePICKUP_LINE_ONE_BACK),
                    new FollowPath(robot.follower, paths.BLUE_ePICKUP_SHOOT_LINE_ONE_BACK),

                    new InstantCommand(() -> robot.intake.setIntakeStateOuttake()),

                    new ParallelDeadlineGroup(
                           new WaitCommand(2000),
                           new LaunchCommand()
                    ),

                    new IntakingCommand(),

                    new FollowPath(robot.follower, paths.BLUE_SHOOT_PICKUP_CORNER_BACK),
                    new FollowPath(robot.follower, paths.BLUE_PICKUP_CORNER_SHOOT_BACK),


                    new InstantCommand(() -> robot.intake.setIntakeStateOuttake()),



                    new ParallelDeadlineGroup(
                        new WaitCommand(1500),
                        new LaunchCommand()
                    ),

                    new IntakingCommand(),


                    new FollowPath(robot.follower, paths.BLUE_SHOOT_PICKUP_CURVE_CORNER_BACK, false),
                    new FollowPath(robot.follower, paths.BLUE_PICKUP_CURVE_CORNER_SHOOT_BACK),

                    new InstantCommand(() -> robot.intake.setIntakeStateOuttake()),

                    new ParallelDeadlineGroup(
                        new WaitCommand(1500),
                        new LaunchCommand()
                    ),

                    new FollowPath(robot.follower, paths.BLUE_MOVE_OFF_BACK),


                    new TurnOffRobotCommand()
                )
        );
    }


    @Override
    public void run() {
        super.run();
        telemetry.addData("Offset Angle: ", Constants.HardwareInitialization.OFFSET_ANGLE);
        telemetry.addData("Encoder: ", robot.turretEncoder.getPosition());
        telemetry.addData("Angle: ", robot.turret.wantedAngle + Constants.HardwareInitialization.OFFSET_ANGLE);
        telemetry.update();

    }

    @Override
    public void end() {
        robot.limelight.stop();
        Constants.HardwareInitialization.END_ROBOT_POSE = new Pose2D(DistanceUnit.INCH, robot.follower.getPose().getX(),robot.follower.getPose().getY(),AngleUnit.RADIANS, robot.follower.getHeading());
    }
}

