package org.usfirst.frc2175.pid.motionprofiles;

import java.util.ArrayList;
import java.util.List;

public class MotionProfile {
    private List<MotionProfilePoint> profileAsList =
            new ArrayList<MotionProfilePoint>();
    private int dTime;

    public MotionProfile(int dTime) {
        this.dTime = dTime;
    }

    /**
     * Gets the MotionProfilePoint at the specified time.
     *
     * @param time
     *            Time value of point to grab. This must be a multiple of dTime
     *            for the profile
     * @return The Point at the specified time value
     */
    public MotionProfilePoint getPointAtTime(int time) {
        MotionProfilePoint point;
        // Check to make sure the point is a multiple of dTime, throw an
        // exception otherwise
        if (time % dTime != 0) {
            throw new IllegalArgumentException(
                    "Time signature must be a multiple of dTime!");
        }
        point = profileAsList.get(time / dTime);
        return point;
    }

    public void addMotionProfilePoint(MotionProfilePoint point) {
        profileAsList.add(point);
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