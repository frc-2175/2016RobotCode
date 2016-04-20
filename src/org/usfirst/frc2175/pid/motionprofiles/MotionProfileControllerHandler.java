package org.usfirst.frc2175.pid.motionprofiles;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc2175.pid.PIDControllerComplete;

/**
 * The MotionProfileControllerHandler class is designed to execute
 * {@link #MotionProfile MotionProfiles}.
 *
 * @author Max Haland
 *
 */
public class MotionProfileControllerHandler {

    private Timer setpointSetterTimer;
    private MotionProfile profile;
    private PIDControllerComplete[] controllers;
    private boolean hasProfileStarted = false;
    private boolean isProfileFinished = false;

    /**
     * Constructor for a new {@link MotionProfileControllerHandler} with a
     * specified {@link MotionProfile} and an array of controllers
     * {@link #PIDControllerComplete PIDControllerComplete[]}.
     *
     * @param profile
     *            MotionProfile to execute
     * @param controllers
     *            Array of {@link #PIDControllerComplete PIDControllerCompletes}
     *            to execute with
     */
    public MotionProfileControllerHandler(MotionProfile profile,
            PIDControllerComplete... controllers) {
        this.profile = profile;
        this.controllers = controllers;
    }

    /**
     * Updates the setpoint of each {@link PIDControllerComplete} based on a
     * timestamp for a {@link MotionProfilePoint}. The point is retrieved from
     * the {@link MotionProfile} based on the specified timestamp.
     *
     * @param timestamp
     *            Timestamp for MotionProfilePoint. Must be a multiple of dTime
     *            for the controller.
     */
    private void updateControllerSetpoints(int timestamp) {
        if (timestamp % profile.getDTime() != 0) {
            throw new IllegalArgumentException(
                    "Timestamp must be a multiple of dTime!");
        } else {
            for (PIDControllerComplete controller : controllers) {
                double setpoint = profile.getMotionProfilePointAtTime(timestamp)
                        .getVelocity();
                controller.setSetpoint(setpoint);
            }
        }
    }

    /**
     * Starts the handler. A new {@link Timer} is created, and a new
     * {@link ControllerHandlerTimerTask} with the necessary
     * {@link MotionProfile} is scheduled on a loop. The frequency of the loop
     * is the same as dTime for the MotionProfile. Finally, the PID controllers
     * are enabled.
     */
    public void enable() {
        ControllerHandlerTimerTask controllerTask =
                new ControllerHandlerTimerTask(profile);
        setpointSetterTimer = new Timer();
        setpointSetterTimer.schedule(controllerTask, 0, profile.getDTime());
        enableControllers();
        hasProfileStarted = true;
    }

    /**
     * Stops the controllers and cancels the timer. This is called automatically
     * when the profile is finished.
     */
    public void disable() {
        setpointSetterTimer.cancel();
        disableControllers();
    }

    private void enableControllers() {
        for (PIDControllerComplete controller : controllers) {
            controller.enable();
        }
    }

    private void disableControllers() {
        for (PIDControllerComplete controller : controllers) {
            controller.disable();
        }
    }

    public boolean hasStarted() {
        return hasProfileStarted;
    }

    public boolean isFinished() {
        return isProfileFinished;
    }

    /**
     * Framework for running the profile update methods on a {@link Timer}. The
     * task runs exactly once for each {@link MotionProfilePoint} in the
     * {@link MotionProfile}, then terminates itself.
     *
     * @author Max Haland
     *
     *
     */
    public class ControllerHandlerTimerTask extends TimerTask {
        /**
         * Represents whether the task has started.
         */
        private boolean hasStarted = false;
        /**
         * Represents whether the task is finished.
         */
        private boolean isFinished = false;
        /**
         * Timestamp of point to run.
         */
        private int currentTime = 0;

        private int dTime;

        public ControllerHandlerTimerTask(MotionProfile profile) {
            this.dTime = profile.getDTime();
        }

        /**
         * Increases the value of {@link #currentTime} based on the dTime value
         * for a {@link MotionProfile}. Once currentTime is equal to the total
         * time to run the profile, {@link #isFinished} is set to true.
         */
        private void indexTime() {
            if (!hasStarted) {
                hasStarted = !hasStarted;
            } else {
                currentTime = currentTime + dTime;
                if (currentTime >= dTime * profile.getProfileRunTime()) {
                    isFinished = true;
                    isProfileFinished = true;
                }
            }
        }

        /**
         * Updates the controller setpoints with
         * {@link #MotionProfileControlHandler.updateControllerSetpoints()},
         * then calls {@link #indexTime()} to prepare for the next run. If after
         * the time is indexed, {@link #isFinished} is true, the task and its
         * timer are cancelled, and the controllers are disabled.
         */
        @Override
        public void run() {
            updateControllerSetpoints(currentTime);
            indexTime();
            if (isFinished) {
                cancel();
                disable();
            }
        }

    }

}
