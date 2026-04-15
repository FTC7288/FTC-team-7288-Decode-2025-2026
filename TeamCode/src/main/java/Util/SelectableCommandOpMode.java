package Util;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class SelectableCommandOpMode extends OpMode {

    public void reset() {
        CommandScheduler.getInstance().reset();
    }

    /**
     * Runs the {@link CommandScheduler} instance
     */
    public void run() {
        CommandScheduler.getInstance().run();
    }

    /**
     * Schedules {@link Command} objects to the scheduler
     */
    public void schedule(Command... commands) {
        CommandScheduler.getInstance().schedule(commands);
    }

    /**
     * Registers {@link Subsystem} objects to the scheduler
     */
    public void register(Subsystem... subsystems) {
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    public abstract void initialize();

    /**
     * Runs at the end (when opMode is no longer active) of CommandOpMode
     */
    public void end() { }

    /**
     * Runs at the end (when opMode is no longer active) of CommandOpMode
     */
    public void startOnce() { }

    /**
     * Runs repeatedly after initialized, similarly to LinearOpMode's init_loop()
     **/
    public void initialize_loop() { }



    @Override
    public void init() {
        initialize();
    }

    @Override
    public void init_loop() {
        initialize_loop();
    }

    @Override
    public void start() {
        startOnce();
    }

    @Override
    public void loop() {
        run();
    }

    @Override
    public void stop() {
        end();
    }

}
