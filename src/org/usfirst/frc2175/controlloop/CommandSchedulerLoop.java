package org.usfirst.frc2175.controlloop;

import edu.wpi.first.wpilibj.command.Scheduler;

public class CommandSchedulerLoop extends ControlLoop {
    @Override
    protected long getPeriod() {
        return 10;
    }

    @Override
    protected void runTask() {
        Scheduler.getInstance().run();
    }
}
