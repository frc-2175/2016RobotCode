package org.usfirst.frc2175.pid.motionprofiles;

/**
 * A MotionProfilePoint is one piece of data in a MotionProfile, consisting of
 * several components. Time is always required. Currently has only time and
 * velocity as components for each point, but could be extended to have
 * position, acceleration, or jerk.
 *
 * @author Max Haland
 *
 */
public class MotionProfilePoint {
    // Components in the point
    private int time;
    private double velocity;

    /**
     * Constructor for a MotionProfilePoint with a specific time and velocity.
     *
     * @param time
     *            Time of the point (in ms)
     * @param velocity
     *            Velocity of the point
     */
    public MotionProfilePoint(int time, double velocity) {
        this.time = time;
        this.velocity = velocity;
    }

    public int getTime() {
        return time;
    }

    public double getVelocity() {
        return velocity;
    }

}