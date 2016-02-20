package org.usfirst.frc2175.controlloop;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class which runs a single task in a continuous loop. Can be used for
 * command scheduling or logging loops, for example.
 */
public abstract class ControlLoop {
    private final Timer loopTimer;

    public ControlLoop() {
        this.loopTimer = new Timer();
    }

    /**
     * @return The period, in milliseconds, of the control loop.
     */
    protected abstract long getPeriod();

    /**
     * Performs the action of the control loop.
     */
    protected abstract void runTask();

    public void start() {
        loopTimer.schedule(new ControlLoopTask(), 0, getPeriod());
    }

    private class ControlLoopTask extends TimerTask {
        @Override
        public void run() {
            runTask();
        }
    }
}
