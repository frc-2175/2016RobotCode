package org.usfirst.frc2175.pid.motionprofiles;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc2175.pid.PIDControllerComplete;

public class MotionProfileControllerHandler {

    private Timer setpointSetterTimer;
    private MotionProfile profile;
    private PIDControllerComplete[] controllers;
    private boolean hasProfileStarted = false;
    private boolean isProfileFinished = false;

    public MotionProfileControllerHandler(MotionProfile profile,
            PIDControllerComplete... controllers) {
        this.profile = profile;
        this.controllers = controllers;
    }

    private void updateControllerSetpoints(int time) {
        for (PIDControllerComplete controller : controllers) {
            double setpoint = profile.getPointAtTime(time).getVelocity();
            controller.setSetpoint(setpoint);
        }
    }

    public void enable() {
        ControllerHandlerTimerTask controllerTask =
                new ControllerHandlerTimerTask(profile);
        setpointSetterTimer = new Timer();
        setpointSetterTimer.schedule(controllerTask, 0, profile.getDTime());
        enableControllers();
        hasProfileStarted = true;
    }

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

    public class ControllerHandlerTimerTask extends TimerTask {
        private boolean hasStarted = false;
        private boolean isFinished = false;
        private int currentTime = 0;

        private int dTime;

        public ControllerHandlerTimerTask(MotionProfile profile) {
            this.dTime = profile.getDTime();
        }

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

        @Override
        public void run() {
            updateControllerSetpoints(currentTime);
            indexTime();
            if (isFinished) {
                cancel();
                setpointSetterTimer.cancel();
            }
        }

    }

}
