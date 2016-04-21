package org.usfirst.frc2175.pid.motionprofiles;

import java.util.ArrayList;
import java.util.List;

/**
 * A MotionProfile is a set of distinct MotionProfilePoints, and the methods
 * used to access and modify them.
 *
 * @author Max Haland
 *
 */
public class MotionProfile {
    private List<MotionProfilePoint> profileAsList =
            new ArrayList<MotionProfilePoint>();
    // Default time between points in ms. This should be longer than the PID
    // loop cycle time.
    public static final int DEFAULT_D_TIME = 50;

    // Time between points in the profile
    private final int dTime;

    /**
     * Constructor for a MotionProfile with a specific time between points.
     *
     * @param dTime
     *            Time between points in the profile
     */
    public MotionProfile(int dTime) {
        this.dTime = dTime;
    }

    /**
     * Constructor for a MotionProfile with the default time between points.
     */
    public MotionProfile() {
        this(DEFAULT_D_TIME);
    }

    /**
     * Gets the MotionProfilePoint at the specified time.
     *
     * @param time
     *            Time value of point to grab. This must be a multiple of dTime
     *            for the profile
     * @return The Point at the specified time value
     */
    public MotionProfilePoint getMotionProfilePointAtTime(int time) {
        MotionProfilePoint point;
        // Check to make sure the point is a multiple of dTime, throw an
        // exception otherwise
        if (time % dTime != 0) {
            throw new IllegalArgumentException(
                    "Time signature must be a multiple of dTime!");
        }
        if (time == 0) {
            point = profileAsList.get(0);
        } else {
            point = profileAsList.get(time / dTime);
        }
        return point;
    }

    /**
     * Adds a MotionProfilePoint to the profile. Points should be ordered in
     * chronological order, and must have timestamps that are multiples of
     * dTime.
     *
     * @param point
     *            Point to add. The point time must be a multiple of dTime
     */
    public void addMotionProfilePoint(MotionProfilePoint point) {
        // Check to make sure the point's time signature is a multiple of dTime,
        // throw an exception otherwise
        if (point.getTime() % dTime != 0) {
            throw new IllegalArgumentException(
                    "Time signature must be a multiple of dTime!");
        } else {
            profileAsList.add(point);
        }
    }

    public List<MotionProfilePoint> getProfileAsList() {
        return profileAsList;
    }

    public int getDTime() {
        return dTime;
    }

    public int getProfileRunTime() {
        return dTime * profileAsList.size();
    }
}