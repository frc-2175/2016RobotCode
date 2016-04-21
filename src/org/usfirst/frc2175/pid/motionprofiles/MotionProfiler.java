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

    public static final String PROFILE_CSV_ROBOT_LOCATION =
            "/home/lvuser/profiles/";
    public static final String PROFILE_CSV_TEST_LOCATION =
            "src/properties/tests/";

    private static String profileLocationToUse = PROFILE_CSV_ROBOT_LOCATION;

    /**
     * Imports a motion profile from a CSV file. The CSV must be structured with
     * each row as "time, velocity" where time is a multiple of dTime. The first
     * entry should be "0,0", with the time value increasing by dTime each row.
     * Motion profile CSVs can be generated in several ways, including several
     * spreadsheets available on Chief Delphi.
     *
     * @param name
     *            Name of the CSV file ("*.csv")
     * @param dTime
     *            Time per step in the CSV file
     * @return A {@link MotionProfile} from the imported CSV file
     */
    public static MotionProfile parseMotionProfileFromCSV(String name,
            int dTime) {
        String fileLocation = profileLocationToUse + name;
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

    /**
     * Sets the profile location to use. This should only be used for testing.
     *
     * @param profileLocation
     *            Location to use
     */
    public static void setProfileLocationToUse(String profileLocation) {
        profileLocationToUse = profileLocation;
    }
}
