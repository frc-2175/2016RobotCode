package org.usfirst.frc2175.pid.motionprofiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class MotionProfilerTest extends TestBase {
    private final String testCSVName = "testProfile.csv";

    @Test
    public void testParseMotionProfileFromCSV_goodDTime() {
        MotionProfiler.setProfileLocationToUse(
                MotionProfiler.PROFILE_CSV_TEST_LOCATION);
        int dTime = 50;
        MotionProfile profile;
        profile = MotionProfiler.parseMotionProfileFromCSV(testCSVName, dTime);

        assertThat("Motion profile point a t=0 wrong",
                profile.getMotionProfilePointAtTime(0).getVelocity(),
                is((double) 0));
        assertThat("Motion profile point a t=" + dTime + " wrong",
                profile.getMotionProfilePointAtTime(dTime).getVelocity(),
                is(.1));
        assertThat("Motion profile point a t=" + 2 * dTime + " wrong",
                profile.getMotionProfilePointAtTime(2 * dTime).getVelocity(),
                is(.3));
        assertThat("Motion profile point a t=" + 3 * dTime + " wrong",
                profile.getMotionProfilePointAtTime(3 * dTime).getVelocity(),
                is((double) 1));
    }

}
