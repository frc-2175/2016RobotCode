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
     *            Time between points in the profile. This must be a positive
     *            value.
     */
    public MotionProfile(int dTime) {
        if (dTime <= 0) {
            throw new IllegalArgumentException("dTime must be positive");
        }
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
        if (time % dTime != 0 || time < 0) {
            throw new IllegalArgumentException(
                    "Time signature must be a nonnegative multiple of dTime!");
        }
        if (time == 0) {
            point = profileAsList.get(0);
        } else if (time / dTime > profileAsList.size()
                - 1 /* We subtract 1 to account for the zero point */) {
            throw new IllegalArgumentException(
                    "Time signature represents a later point than the profile contains!");
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
        // Check to make sure the point's time signature is a multiple of dTime
        // that is not already used, throw an exception otherwise
        if (point.getTime() % dTime != 0 || point.getTime() < 0) {
            throw new IllegalArgumentException(
                    "Time signature must be a nonnegative multiple of dTime!");
        } else {
            for (MotionProfilePoint somePoint : profileAsList) {
                if (point.getTime() == somePoint.getTime()) {
                    throw new IllegalArgumentException(
                            "May not add duplicate point!");
                }
            }
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