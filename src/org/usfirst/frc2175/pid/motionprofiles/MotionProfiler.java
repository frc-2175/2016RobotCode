package org.usfirst.frc2175.pid.motionprofiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

/**
 * The class MotionProfiler serves as a collection of methods to generate and
 * make {@link #MotionProfile MotionProfiles}. There are many different ways to
 * do this, but only trapezoidal velocity profiling is implemented now.
 * Eventually, there will be functionality to import a motion profile from a CSV
 * file.
 *
 * @author Max Haland
 *
 */
public class MotionProfiler {

    private static final String PROFILE_CSV_ROBOT_LOCATION =
            "/home/lvuser/profiles";

    /**
     * Generates a trapezoidal velocity motion profile with a specific dTime.
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
    /*
     * TODO make unit tests for this (or test it with a spreadsheet or
     * something). Currently, it probably doesn't work for distances where the
     * robot cannot hit max velocity.
     */
    public static MotionProfile generateDriveDistanceMotionProfile(
            double distance, double maxVelocity, double maxAcceleration,
            int dTime) {
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

    /**
     * Generates a trapezoidal velocity motion profile with default dTime.
     *
     * @param distance
     *            Distance to drive
     * @param maxVelocity
     *            Highest speed allowed
     * @param maxAcceleration
     *            Highest acceleration allowed
     *
     */
    /*
     * TODO make unit tests for this (or test it with a spreadsheet or
     * something). Currently, it probably doesn't work for distances where the
     * robot cannot hit max velocity.
     */
    public static MotionProfile generateDriveDistanceMotionProfile(
            double distance, double maxVelocity, double maxAcceleration) {
        MotionProfile profile = new MotionProfile();

        // Values for profile
        double t_accel = (maxVelocity / maxAcceleration);
        double t_constantSpeed = Math.sqrt(
                (distance / maxVelocity) - (maxVelocity / maxAcceleration));

        // Loop to add points to profile
        for (int time = 0; time <= 2 * t_accel + t_constantSpeed; time =
                time + MotionProfile.DEFAULT_D_TIME) {
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

    public static MotionProfile parseMotionProfileFromCSV(String name,
            int dTime) {
        String fileLocation = PROFILE_CSV_ROBOT_LOCATION + name;
        MotionProfile profile = new MotionProfile(dTime);
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(fileLocation));

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                int time = Integer.parseInt(nextLine[0]);
                double velocity = Double.parseDouble(nextLine[1]);
                profile.addMotionProfilePoint(
                        new MotionProfilePoint(time, velocity));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
