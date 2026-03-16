package org.firstinspires.ftc.teamcode.CommandBase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Global.Constants;
import org.firstinspires.ftc.teamcode.Global.Robot;

public class Flywheel extends SubsystemBase {
    Robot robot = Robot.getInstance();
    Constants.FlywheelSpeedSelector flywheelSpeedSelector = Constants.FlywheelSpeedSelector.OFF;

    // TODO: finish this please
    public void setFlywheelSpeed() {
        switch (flywheelSpeedSelector) {
            case ON:

            case OFF:

        }
    }



}
