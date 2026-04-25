package org.firstinspires.ftc.teamcode.CommandBase.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Global.Robot;

public class OuttakeCommand extends CommandBase {
    Robot robot = Robot.getInstance();
    private boolean pullIntakeIn;

    public OuttakeCommand(boolean pullIntakeIn) {
        this.pullIntakeIn = pullIntakeIn;
    }

    @Override
    public void initialize() {
        robot.intake.setIntakeStateOuttake();
        robot.intake.outtakeIntakePosition(pullIntakeIn);
    }

    public boolean isFinished() {
        return true;
    }


}
