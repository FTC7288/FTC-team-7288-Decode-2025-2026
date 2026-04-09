package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Global.Robot;
import org.firstinspires.ftc.teamcode.Global.Constants;
public class LimeLight extends SubsystemBase {
    Robot robot = Robot.getInstance();

    public void setTeam (int team) {
        robot.limelight3A.pipelineSwitch(team);
    }

    public void start() {
        robot.limelight3A.setPollRateHz(Constants.HardwareInitialization.LIMELIGHT_POLLING_HZ);
        robot.limelight3A.start();
    }

    public void stop() {
        robot.limelight3A.stop();
    }


    // TODO: Add translation matrix and this to be a Pose2D object rather than a Pose3D object
    public Pose3D getPose() {
        LLResult latestResult = robot.limelight3A.getLatestResult();
        if (latestResult.isValid()) {
            return latestResult.getBotpose();
        } else {
            return null;
        }
    }





}
