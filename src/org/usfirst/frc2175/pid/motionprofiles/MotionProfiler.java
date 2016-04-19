package org.usfirst.frc2175.pid.motionprofiles;

import java.util.ArrayList;
import java.util.List;

public class MotionProfiler {

    public class MotionProfilePoint {
        private double time;
        private double velocity;

        public MotionProfilePoint(double time, double velocity) {
            this.time = time;
            this.velocity = velocity;
        }

        public double getTime() {
            return time;
        }

        public double getVelocity() {
            return velocity;
        }

        private double[] getPointAsArray() {
            double[] pointAsArray = { time, velocity };
            return pointAsArray;
        }
    }

    public class MotionProfile {
        private List<MotionProfilePoint> profileAsList =
                new ArrayList<MotionProfilePoint>();
        private int dTime;

        public MotionProfile(int dTime) {
            this.dTime = dTime;
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

    /**
     * Generates a trapezoidal velocity motion profile.
     *
     * @param distance
     *            Distance to drive
     * @param maxVelocity
     *            Highest speed allowed
     * @param maxAcceleration
     *            Highest acceleration allowed
     * @param dTime
     *            Time per profile step (in ms)
     */
    public MotionProfile generateDriveDistanceMotionProfile(double distance,
            double maxVelocity, double maxAcceleration, int dTime) {
        MotionProfile profile = new MotionProfile(dTime);

        // Values for profile
        double t_accel = (maxVelocity / maxAcceleration);
        double t_constantSpeed = Math.sqrt(
                (distance / maxVelocity) - (maxVelocity / maxAcceleration));

        // Loop to add points to profile
        for (int time = 0; time <= 2 * t_accel + t_constantSpeed; time =
                time + dTime) {
            MotionProfilePoint point;
            // Handle each case separately: acceleration, constant speed,
            // deceleration
            if (time < t_accel) {
                double velocityValue = maxAcceleration * time;
                point = new MotionProfilePoint(time, velocityValue);
            } else if (t_accel <= time && time < t_accel + t_constantSpeed) {
                point = new MotionProfilePoint(time, maxVelocity);
            } else {
                double velocityValue =
                        maxAcceleration * (time - t_accel - t_constantSpeed);
                point = new MotionProfilePoint(time, velocityValue);
            }
            // Add the point to the profile
            profile.addMotionProfilePoint(point);
        }
        return profile;
    }
}
